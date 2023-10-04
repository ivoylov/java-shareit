package ru.practicum.shareit.exception.booking;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.exception.entity.EntityException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import static org.junit.jupiter.api.Assertions.*;

class BookingAlreadyApprovedExceptionTest {

    @Test
    void getEntity() {
        Booking booking = new Booking();
        BookingAlreadyApprovedException bookingException = new BookingAlreadyApprovedException(booking);
        assertEquals(booking, bookingException.getEntity());
    }

}