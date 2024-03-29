package ru.practicum.shareit.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.exception.booking.BookingAlreadyApprovedException;
import ru.practicum.shareit.exception.entity.EntityNotFoundException;
import ru.practicum.shareit.exception.item.UnsupportedItemStatusException;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.Role;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private UserService userService;
    @Mock
    private ItemService itemService;
    @InjectMocks
    private BookingService bookingService;
    private Booking bookingToCreate;
    private Booking createdBooking;
    private LocalDateTime start;
    private LocalDateTime end;
    private User booker;
    private Item item;

    @BeforeEach
    void setUp() {
        List<Booking> bookings = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        start = LocalDateTime.now().plusHours(1);
        end = start.plusHours(2);
        booker = new User(1L, "name", "email@mail.ru", items, bookings, null);
        User owner = new User(2L, "owner", "mailOwner@email.ru", items, bookings, null);
        item = new Item(1L, "name", "description", true, owner, bookings, comments, null);
        bookingToCreate = new Booking(null, start, end, null, null, null);
        createdBooking = new Booking(1L, start, end, Status.WAITING, booker, item);
    }

    @Test
    void create_whenExpectedCreatedBookingVariable() {
        Mockito.when(userService.get(1L)).thenReturn(booker);
        Mockito.when(itemService.get(1L, 1L)).thenReturn(item);
        Mockito.when(bookingRepository.save(Mockito.any())).thenReturn(createdBooking);
        assertEquals(bookingService.create(bookingToCreate, 1L, 1L), createdBooking);
    }

    @Test
    void approved_whenExpectedCreatedBookingWithApprovedTrue() {
        createdBooking.setStatus(Status.APPROVED);
        Booking findBooking = new Booking(1L, start, end, Status.WAITING, booker, item);
        Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(findBooking));
        assertEquals(bookingService.approved(2L, 1L, true), createdBooking);
    }

    @Test
    void approved_thenThrowEntityNotFoundException() {
        createdBooking.setStatus(Status.APPROVED);
        Booking findBooking = new Booking(1L, start, end, Status.WAITING, booker, item);
        Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(findBooking));
        assertThrows(EntityNotFoundException.class, () -> bookingService.approved(1L, 1L, true));
    }

    @Test
    void approved_thenThrowBookingAlreadyApprovedException() {
        createdBooking.setStatus(Status.APPROVED);
        Booking findBooking = new Booking(1L, start, end, Status.APPROVED, booker, item);
        Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(findBooking));
        assertThrows(BookingAlreadyApprovedException.class, () -> bookingService.approved(2L, 1L, true));
    }

    @Test
    void get_whenExpectedCreatedBookingVariable() {
        Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(createdBooking));
        assertEquals(bookingService.get(1L, 1L), createdBooking);
    }

    @Test
    void getAllOwnerItems_whenExpectedListOfCreatedBooking() {
        createdBooking.setStatus(Status.APPROVED);
        Mockito.when(bookingRepository.findAllByItem(item, PageRequest.of(0,1).withSort(Sort.Direction.DESC, "id"))).thenReturn(List.of(createdBooking));
        Mockito.when(itemService.getOwnerItems(1L, PageRequest.of(0,1))).thenReturn(List.of(item));
        List<Booking> findBookings = bookingService.getAll("FUTURE", 1L, Role.OWNER, 0, 1);
        assertEquals(findBookings, List.of(createdBooking));
    }

    @Test
    void getAll_thenFuture() {
        createdBooking.setStatus(Status.APPROVED);
        Mockito.when(bookingRepository.findAllByBookerId(1L, PageRequest.of(0,1).withSort(Sort.Direction.DESC, "id"))).thenReturn(List.of(createdBooking));
        List<Booking> findBookings = bookingService.getAll("FUTURE", 1L, Role.BOOKER, 0, 1);
        assertEquals(findBookings, List.of(createdBooking));
    }

    @Test
    void getAll_thenThrowUnsupportedItemStatusException() {
        assertThrows(UnsupportedItemStatusException.class, () -> bookingService.getAll("UNSUPPORTED_STATUS", 1L, Role.BOOKER, 0, 1));
    }

    @Test
    void getAll_thenCurrent() {
        createdBooking.setStatus(Status.APPROVED);
        createdBooking.setEnd(LocalDateTime.now().plusHours(2));
        createdBooking.setStart(LocalDateTime.now().minusHours(1));
        Mockito.when(bookingRepository.findAllByBookerId(1L, PageRequest.of(0,1).withSort(Sort.Direction.DESC, "id"))).thenReturn(List.of(createdBooking));
        List<Booking> findBookings = bookingService.getAll("CURRENT", 1L, Role.BOOKER, 0, 1);
        assertEquals(findBookings, List.of(createdBooking));
    }

    @Test
    void getAll_thenPast() {
        createdBooking.setStatus(Status.APPROVED);
        createdBooking.setStart(LocalDateTime.now().minusHours(2));
        createdBooking.setEnd(LocalDateTime.now().minusHours(1));
        Mockito.when(bookingRepository.findAllByBookerId(1L, PageRequest.of(0,1).withSort(Sort.Direction.DESC, "id"))).thenReturn(List.of(createdBooking));
        List<Booking> findBookings = bookingService.getAll("PAST", 1L, Role.BOOKER, 0, 1);
        assertEquals(findBookings, List.of(createdBooking));
    }

    @Test
    void getAll_thenRejected() {
        createdBooking.setStatus(Status.REJECTED);
        Mockito.when(bookingRepository.findAllByBookerId(1L, PageRequest.of(0,1).withSort(Sort.Direction.DESC, "id"))).thenReturn(List.of(createdBooking));
        List<Booking> findBookings = bookingService.getAll("REJECTED", 1L, Role.BOOKER, 0, 1);
        assertEquals(findBookings, List.of(createdBooking));
    }

    @Test
    void getAll_thenWaiting() {
        createdBooking.setStatus(Status.WAITING);
        Mockito.when(bookingRepository.findAllByBookerId(1L, PageRequest.of(0,1).withSort(Sort.Direction.DESC, "id"))).thenReturn(List.of(createdBooking));
        List<Booking> findBookings = bookingService.getAll("WAITING", 1L, Role.BOOKER, 0, 1);
        assertEquals(findBookings, List.of(createdBooking));
    }

}