package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingDtoOutTest {

    private BookingDtoOut bookingDtoOut;
    User user;
    Item item;
    LocalDateTime start;
    LocalDateTime end;

    @BeforeEach
    void setUp() {
        user = new User();
        item = new Item();
        start = LocalDateTime.of(2000,1,1,12,0);
        end = LocalDateTime.of(2000,1,5,12,0);
        bookingDtoOut = new BookingDtoOut(1L, start, end, Status.WAITING, user, item);
    }

    @Test
    void getId() {
        assertEquals(1L, bookingDtoOut.getId());
    }

    @Test
    void getStart() {
        assertEquals(start, bookingDtoOut.getStart());
    }

    @Test
    void getEnd() {
        assertEquals(end, bookingDtoOut.getEnd());
    }

    @Test
    void getStatus() {
        assertEquals(Status.WAITING, bookingDtoOut.getStatus());
    }

    @Test
    void getBooker() {
        assertEquals(user, bookingDtoOut.getBooker());
    }

    @Test
    void getItem() {
        assertEquals(item, bookingDtoOut.getItem());
    }

    @Test
    void setId() {
        bookingDtoOut.setId(2L);
        assertEquals(2L, bookingDtoOut.getId());
    }

    @Test
    void setStart() {
        bookingDtoOut.setStart(bookingDtoOut.getStart().minusHours(1));
        assertEquals(start.minusHours(1), bookingDtoOut.getStart());
    }

    @Test
    void setEnd() {
        bookingDtoOut.setEnd(bookingDtoOut.getEnd().plusHours(1));
        assertEquals(end.plusHours(1), bookingDtoOut.getEnd());
    }

    @Test
    void setStatus() {
        bookingDtoOut.setStatus(Status.APPROVED);
        assertEquals(Status.APPROVED, bookingDtoOut.getStatus());
    }

    @Test
    void setBooker() {
        User newUser = new User(2L, "name", "email", new ArrayList<>(), new ArrayList<>());
        bookingDtoOut.setBooker(newUser);
        assertEquals(newUser, bookingDtoOut.getBooker());
    }

    @Test
    void setItem() {
        Item newItem = new Item(2L, "name", "description", true, user, new ArrayList<>());
        bookingDtoOut.setItem(newItem);
        assertEquals(newItem, bookingDtoOut.getItem());
    }

    @Test
    void testEquals() {
        BookingDtoOut newBooking = new BookingDtoOut(1L, start, end, Status.WAITING, user, item);
        assertEquals(bookingDtoOut, newBooking);
    }

    @Test
    void testHashCode() {
        BookingDtoOut newBooking = new BookingDtoOut(1L, start, end, Status.WAITING, user, item);
        assertEquals(bookingDtoOut.hashCode(), newBooking.hashCode());
    }

    @Test
    void testToString() {
        String string = "BookingDto(id=1, start=2000-01-01T12:00, end=2000-01-05T12:00, status=WAITING, booker=User(id=null, name=null, email=null, items=null, bookings=null), item=Item(id=null, name=null, description=null, available=null, owner=null, bookings=null))";
        assertEquals(string, bookingDtoOut.toString());
    }

    @Test
    void builder() {
        BookingDtoOut booking1 = new BookingDtoOut(3L, start, end, Status.REJECTED, new User(), new Item());
        BookingDtoOut booking2 = BookingDtoOut.builder()
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