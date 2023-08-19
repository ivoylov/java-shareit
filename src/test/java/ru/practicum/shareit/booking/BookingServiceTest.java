package ru.practicum.shareit.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
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

import static org.junit.jupiter.api.Assertions.*;

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
        booker = new User(1L, "name", "email@mail.ru", items, bookings);
        User owner = new User(2L, "owner", "mailOwner@email.ru", items, bookings);
        item = new Item(1L, "name", "description", true, owner, bookings, comments);
        bookingToCreate = new Booking(null, start, end, null, null, null);
        createdBooking = new Booking(1L, start, end, Status.WAITING, booker, item);
    }

    @Test
    void create() {
        Mockito.when(userService.get(1L)).thenReturn(booker);
        Mockito.when(itemService.get(1L, 1L)).thenReturn(item);
        Mockito.when(bookingRepository.save(Mockito.any())).thenReturn(createdBooking);
        assertEquals(bookingService.create(bookingToCreate, 1L, 1L), createdBooking);
    }

    @Test
    void approved() {
        createdBooking.setStatus(Status.APPROVED);
        Booking findBooking = new Booking(1L, start, end, Status.WAITING, booker, item);
        Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(findBooking));
        assertEquals(bookingService.approved(2L, 1L, true), createdBooking);
    }

    @Test
    void get() {
        Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(createdBooking));
        assertEquals(bookingService.get(1L, 1L), createdBooking);
    }

    @Test
    void getAll() {
        createdBooking.setStatus(Status.APPROVED);
        Mockito.when(bookingRepository.findAllByBookerId(1L)).thenReturn(List.of(createdBooking));
        List<Booking> findBookings = bookingService.getAll("FUTURE", 1L, Role.BOOKER);
        assertEquals(findBookings, List.of(createdBooking));
    }

}