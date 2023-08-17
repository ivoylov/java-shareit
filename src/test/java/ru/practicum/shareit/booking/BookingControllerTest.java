package ru.practicum.shareit.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.model.*;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemDtoOutShort;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.model.UserDtoOutShort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

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
    private UserDtoOutShort userDtoOutShort;
    private ItemDtoOutShort itemDtoOutShort;
    private LocalDateTime start;
    private LocalDateTime end;
    private User booker;
    private Item item;
    private final List<Booking> bookings = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    @BeforeEach
    void setUp() {
        start = LocalDateTime.now().plusHours(1);
        end = start.plusHours(2);
        booker = new User(1L, "name", "email@mail", items, bookings);
        item = new Item(2L, "name", "description", true, booker, bookings, comments);
        bookingToCreate = new Booking(null, start, end, null, null, null);
        createdBooking = new Booking(1L, start, end, Status.WAITING, booker, item);
        bookingDtoIn = new BookingDtoIn(2L, start, end);
        userDtoOutShort = new UserDtoOutShort(1L);
        itemDtoOutShort = new ItemDtoOutShort(2L, "name");
        bookingDtoOut = new BookingDtoOut(1L, start, end, Status.WAITING, userDtoOutShort, itemDtoOutShort);
    }

    @Test
    void create() {
        Mockito.when(bookingService.create(bookingToCreate, 1L, 2L)).thenReturn(createdBooking);
        assertEquals(bookingController.create(bookingDtoIn, 1L), bookingDtoOut);
    }

    @Test
    void approved() {
    }

    @Test
    void get() {
    }

    @Test
    void getAllForBooker() {
    }

    @Test
    void getAllForOwner() {
    }
}