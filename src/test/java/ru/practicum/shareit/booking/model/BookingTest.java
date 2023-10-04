package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.exception.booking.BookingAvailableException;
import ru.practicum.shareit.exception.booking.BookingTimeException;
import ru.practicum.shareit.exception.entity.EntityNotFoundException;
import ru.practicum.shareit.exception.item.ItemAvailableException;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    private Booking booking;
    private LocalDateTime start;
    private LocalDateTime end;
    private User booker;
    private Item item;
    private List<Item> items;
    private List<Booking> bookings;
    private List<Comment> comments;

    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        bookings = new ArrayList<>();
        comments = new ArrayList<>();
        start = LocalDateTime.now().plusHours(1);
        end = LocalDateTime.now().plusHours(2);
        booker = new User(1L, "name", "email@mail.ru", items, bookings, null);
        item = new Item(1L, "itemName", "description", true, booker, bookings, comments, null);
        booking = new Booking(1L, start, end, Status.APPROVED, booker, item);
    }

    @Test
    void isBookingTimeValid() {
        assertTrue(booking.isBookingTimeValid());
    }

    @Test
    void getState() {
        assertEquals(State.FUTURE, booking.getState());
    }

    @Test
    void getCurrentState() {
        booking.setStart(LocalDateTime.now().minusHours(3));
        assertEquals(State.CURRENT, booking.getState());
    }

    @Test
    void compareTo() {
        Booking comparedBooking = new Booking(2L, start, end, Status.APPROVED, booker, item);
        assertEquals(1, booking.compareTo(comparedBooking));
    }

    @Test
    void testToString() {
        String targetString = "id=1, start=" + start + ", end=" + end + ", status=APPROVED, " +
                "booker=id=1, name=name, email=email@mail.ru, " +
                "item=id=1, name=itemName, description=description, available=true, " +
                "owner=id=1, name=name, email=email@mail.ru";
        assertEquals(targetString, booking.toString());
    }

    @Test
    void getId() {
        assertEquals(1L, booking.getId());
    }

    @Test
    void getStart() {
        assertEquals(start, booking.getStart());
    }

    @Test
    void getEnd() {
        assertEquals(end, booking.getEnd());
    }

    @Test
    void getStatus() {
        assertEquals(Status.APPROVED, booking.getStatus());
    }

    @Test
    void getBooker() {
        assertEquals(booker, booking.getBooker());
    }

    @Test
    void getItem() {
        assertEquals(item, booking.getItem());
    }

    @Test
    void setId() {
        booking.setId(2L);
        assertEquals(2L, booking.getId());
    }

    @Test
    void setStart() {
        booking.setStart(start.plusHours(1));
        assertEquals(start.plusHours(1), booking.getStart());
    }

    @Test
    void setEnd() {
        booking.setEnd(end.plusHours(3));
        assertEquals(end.plusHours(3), booking.getEnd());
    }

    @Test
    void setStatus() {
        booking.setStatus(Status.REJECTED);
        assertEquals(Status.REJECTED, booking.getStatus());
    }

    @Test
    void setBooker() {
        User newUser = new User();
        booking.setBooker(newUser);
        assertEquals(newUser, booking.getBooker());
    }

    @Test
    void setItem() {
        Item newItem = new Item();
        booking.setItem(newItem);
        assertEquals(newItem, booking.getItem());
    }

    @Test
    void testEquals() {
        Booking newBooking = new Booking(1L, start, end, Status.APPROVED, booker, item);
        assertEquals(booking, newBooking);
    }

    @Test
    void testHashCode() {
        Booking newBooking = new Booking(1L, start, end, Status.APPROVED, booker, item);
        assertEquals(newBooking.hashCode(), booking.hashCode());
    }

    @Test
    void builder() {
        Booking newBooking = Booking.builder()
                .id(1L)
                .status(Status.APPROVED)
                .start(start)
                .end(end)
                .item(item)
                .booker(booker)
                .build();
        assertEquals(booking, newBooking);
    }

    @Test
    void check_thenThrowItemAvailableException() {
        booking.getItem().setAvailable(false);
        assertThrows(ItemAvailableException.class, () -> booking.check());
    }

    @Test
    void check_thenThrowTimeException() {
        booking.setStart(booking.getEnd().plusHours(1));
        assertThrows(BookingTimeException.class, () -> booking.check());
    }

    @Test
    void check_thenThrowBookingAvailableException() {
        booking.setStart(LocalDateTime.now());
        booking.setEnd(LocalDateTime.now().plusHours(1));
        bookings.add(booking);
        Booking newBooking = new Booking(2L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), Status.WAITING, booker, item);
        booking.setStart(LocalDateTime.now());
        booking.setEnd(LocalDateTime.now().plusHours(1));
        assertThrows(BookingAvailableException.class, newBooking::check);
    }

    @Test
    void check_thenThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> booking.check());
    }

}