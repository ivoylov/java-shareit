package ru.practicum.shareit.exception.booking;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.Booking;

import static org.junit.jupiter.api.Assertions.*;

class BookingAvailableExceptionTest {

    @Test
    void getEntity() {
        Booking booking = new Booking();
        BookingAvailableException exception = new BookingAvailableException(booking);
        assertEquals(booking, exception.getEntity());
    }
}