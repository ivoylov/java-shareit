package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingDtoMapperTest {

    User user;
    Item item;
    LocalDateTime start;
    LocalDateTime end;
    Booking booking;
    BookingDto bookingDto;

    @BeforeEach
    void setUp() {
        user = new User();
        item = new Item();
        start = LocalDateTime.of(2000,1,1,12,0);
        end = LocalDateTime.of(2000,1,5,12,0);
        booking = new Booking(1L, start, end, Status.WAITING, user, item);
        bookingDto = new BookingDto(1L, start, end, Status.WAITING, user, item);
    }

    @Test
    void toBooking() {
        assertEquals(booking, BookingDtoMapper.toBooking(bookingDto));
    }

    @Test
    void toBookingDto() {
        assertEquals(bookingDto, BookingDtoMapper.toBookingDto(booking));
    }
}