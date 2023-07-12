package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingTest {

    private Booking booking;
    User user;
    Item item;
    LocalDateTime start;
    LocalDateTime end;

    @BeforeEach
    void setUp() {
        user = new User();
        item = new Item();
        start = LocalDateTime.of(2000,1,1,12,00);
        end = LocalDateTime.of(2000,1,5,12,00);
        booking = new Booking(1L, start, end, Status.WAITING, user, item);
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
        assertEquals(Status.WAITING, booking.getStatus());
    }

    @Test
    void getBooker() {
        assertEquals(user, booking.getBooker());
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
        booking.setStart(booking.getStart().minusHours(1));
        assertEquals(start.minusHours(1), booking.getStart());
    }

    @Test
    void setEnd() {
        booking.setEnd(booking.getEnd().plusHours(1));
        assertEquals(end.plusHours(1), booking.getEnd());
    }

    @Test
    void setStatus() {
        booking.setStatus(Status.APPROVED);
        assertEquals(Status.APPROVED, booking.getStatus());
    }

    @Test
    void setBooker() {
        User newUser = new User(2L, "name", "email", new ArrayList<>(), new ArrayList<>());
        booking.setBooker(newUser);
        assertEquals(newUser, booking.getBooker());
    }

    @Test
    void setItem() {
        Item newItem = new Item(2L, "name", "description", true, user, new ArrayList<>());
        booking.setItem(newItem);
        assertEquals(newItem, booking.getItem());
    }

    @Test
    void testEquals() {
        Booking newBooking = new Booking(1L, start, end, Status.WAITING, user, item);
        assertEquals(booking, newBooking);
    }

    @Test
    void testHashCode() {
        Booking newBooking = new Booking(1L, start, end, Status.WAITING, user, item);
        assertEquals(booking.hashCode(), newBooking.hashCode());
    }

    @Test
    void testToString() {
        String string = "Booking(id=1, start=2000-01-01T12:00, end=2000-01-05T12:00, status=WAITING, booker=User(id=null, name=null, email=null, items=null, bookings=null), item=Item(id=null, name=null, description=null, available=null, owner=null, bookings=null))";
        assertEquals(string, booking.toString());
    }

    @Test
    void builder() {
        Booking booking1 = new Booking(3L, start, end, Status.REJECTED, new User(), new Item());
        Booking booking2 = Booking.builder()
                .id(3L)
                .start(start)
                .end(end)
                .status(Status.REJECTED)
                .booker(new User())
                .item(new Item())
                .build();
        assertEquals(booking1, booking2);
    }

}