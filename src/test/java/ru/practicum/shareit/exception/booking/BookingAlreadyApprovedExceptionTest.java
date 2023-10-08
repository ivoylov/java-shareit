package ru.practicum.shareit.exception.booking;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.Booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingAlreadyApprovedExceptionTest {

    @Test
    void getEntity_whenExpectedNewBooking() {
        Booking booking = new Booking();
        BookingAlreadyApprovedException bookingException = new BookingAlreadyApprovedException(booking);
        assertEquals(booking, bookingException.getEntity());
    }

}