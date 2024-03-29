package ru.practicum.shareit.exception.booking;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.Booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingTimeExceptionTest {

    @Test
    void getEntity_whenExpectedNewBooking() {
        Booking booking = new Booking();
        BookingTimeException exception = new BookingTimeException(booking);
        assertEquals(booking, exception.getEntity());
    }

}