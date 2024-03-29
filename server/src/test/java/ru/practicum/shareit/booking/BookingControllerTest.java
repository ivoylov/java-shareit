package ru.practicum.shareit.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingDtoIn;
import ru.practicum.shareit.booking.model.BookingDtoOut;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemDtoOutShort;
import ru.practicum.shareit.user.model.Role;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.model.UserDtoOutShort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private BookingService bookingService;
    @InjectMocks
    private BookingController bookingController;
    private Booking bookingToCreate;
    private Booking createdBooking;
    private BookingDtoIn bookingDtoIn;
    private BookingDtoOut bookingDtoOut;
    private final List<Booking> bookings = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    @BeforeEach
    void setUp() {
        LocalDateTime start = LocalDateTime.now().plusHours(1);
        LocalDateTime end = start.plusHours(2);
        User booker = new User(1L, "name", "email@mail", items, bookings, null);
        Item item = new Item(2L, "name", "description", true, booker, bookings, comments, null);
        bookingToCreate = new Booking(null, start, end, null, null, null);
        createdBooking = new Booking(1L, start, end, Status.WAITING, booker, item);
        bookingDtoIn = new BookingDtoIn(2L, start, end);
        UserDtoOutShort userDtoOutShort = new UserDtoOutShort(1L);
        ItemDtoOutShort itemDtoOutShort = new ItemDtoOutShort(2L, "name", null, "description", true);
        bookingDtoOut = new BookingDtoOut(1L, start, end, Status.WAITING, userDtoOutShort, itemDtoOutShort);
    }

    @Test
    void create_whenExpectedBookingDtoInVariable() {
        Mockito.when(bookingService.create(bookingToCreate, 1L, 2L)).thenReturn(createdBooking);
        assertEquals(bookingController.create(bookingDtoIn, 1L), bookingDtoOut);
    }

    @Test
    void approved_whenExpectedBookingDtoOutWithTrueApproved() {
        bookingDtoOut.setStatus(Status.APPROVED);
        createdBooking.setStatus(Status.APPROVED);
        Mockito.when(bookingService.approved(1L, 1L, true)).thenReturn(createdBooking);
        assertEquals(bookingController.approved(1L, true, 1L), bookingDtoOut);
    }

    @Test
    void get_whenExpectedBookingDtoOut() {
        Mockito.when(bookingService.get(1L, 1L)).thenReturn(createdBooking);
        assertEquals(bookingController.get(1L, 1L), bookingDtoOut);
    }

    @Test
    void getAllForBooker_whenExpectedListOdBookingDtoOut() {
        Mockito.when(bookingService.getAll("WAITING", 1L, Role.BOOKER, 0, 1)).thenReturn(List.of(createdBooking));
        assertEquals(bookingController.getAllForBooker("WAITING", 1L, 0, 1), List.of(bookingDtoOut));
    }

    @Test
    void getAllForOwner_whenExpectedListOfBookingDtoOut() {
        Mockito.when(bookingService.getAll("WAITING", 1L, Role.OWNER, 0, 1)).thenReturn(List.of(createdBooking));
        assertEquals(bookingController.getAllForOwner("WAITING", 1L, 0, 1), List.of(bookingDtoOut));
    }

}