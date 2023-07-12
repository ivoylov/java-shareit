package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingDtoTest {

    private BookingDto bookingDto;
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
        bookingDto = new BookingDto(1L, start, end, Status.WAITING, user, item);
    }

    @Test
    void getId() {
        assertEquals(1L, bookingDto.getId());
    }

    @Test
    void getStart() {
        assertEquals(start, bookingDto.getStart());
    }

    @Test
    void getEnd() {
        assertEquals(end, bookingDto.getEnd());
    }

    @Test
    void getStatus() {
        assertEquals(Status.WAITING, bookingDto.getStatus());
    }

    @Test
    void getBooker() {
        assertEquals(user, bookingDto.getBooker());
    }

    @Test
    void getItem() {
        assertEquals(item, bookingDto.getItem());
    }

    @Test
    void setId() {
        bookingDto.setId(2L);
        assertEquals(2L, bookingDto.getId());
    }

    @Test
    void setStart() {
        bookingDto.setStart(bookingDto.getStart().minusHours(1));
        assertEquals(start.minusHours(1), bookingDto.getStart());
    }

    @Test
    void setEnd() {
        bookingDto.setEnd(bookingDto.getEnd().plusHours(1));
        assertEquals(end.plusHours(1), bookingDto.getEnd());
    }

    @Test
    void setStatus() {
        bookingDto.setStatus(Status.APPROVED);
        assertEquals(Status.APPROVED, bookingDto.getStatus());
    }

    @Test
    void setBooker() {
        User newUser = new User(2L, "name", "email", new ArrayList<>(), new ArrayList<>());
        bookingDto.setBooker(newUser);
        assertEquals(newUser, bookingDto.getBooker());
    }

    @Test
    void setItem() {
        Item newItem = new Item(2L, "name", "description", true, user, new ArrayList<>());
        bookingDto.setItem(newItem);
        assertEquals(newItem, bookingDto.getItem());
    }

    @Test
    void testEquals() {
        BookingDto newBooking = new BookingDto(1L, start, end, Status.WAITING, user, item);
        assertEquals(bookingDto, newBooking);
    }

    @Test
    void testHashCode() {
        BookingDto newBooking = new BookingDto(1L, start, end, Status.WAITING, user, item);
        assertEquals(bookingDto.hashCode(), newBooking.hashCode());
    }

    @Test
    void testToString() {
        String string = "BookingDto(id=1, start=2000-01-01T12:00, end=2000-01-05T12:00, status=WAITING, booker=User(id=null, name=null, email=null, items=null, bookings=null), item=Item(id=null, name=null, description=null, available=null, owner=null, bookings=null))";
        assertEquals(string, bookingDto.toString());
    }

    @Test
    void builder() {
        BookingDto booking1 = new BookingDto(3L, start, end, Status.REJECTED, new User(), new Item());
        BookingDto booking2 = BookingDto.builder()
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