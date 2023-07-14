package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingDtoOutMapperTest {

    User user;
    Item item;
    LocalDateTime start;
    LocalDateTime end;
    Booking booking;
    BookingDtoOut bookingDtoOut;
    BookingDtoIn bookingDtoIn;

    @BeforeEach
    void setUp() {
        user = new User();
        item = new Item();
        start = LocalDateTime.of(2000,1,1,12,0);
        end = LocalDateTime.of(2000,1,5,12,0);
        booking = new Booking(1L, start, end, Status.WAITING, user, item);
        bookingDtoOut = new BookingDtoOut(1L, start, end, Status.WAITING, user, item);
        bookingDtoIn = new BookingDtoIn(1L, start, end);
    }

    @Test
    void toBooking() {
        assertEquals(booking, BookingMapper.toBooking(bookingDtoIn));
    }

    @Test
    void toBookingDto() {
        assertEquals(bookingDtoOut, BookingMapper.toBookingDtoOut(booking));
    }
}