package ru.practicum.shareit.test.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingTest {

    private Booking booking;

    @BeforeEach
    void setUo() {
        booking = Booking.builder()
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusHours(1))
                .build();
    }

    @Test
    void isBookingTimeValid() {
        assertTrue(booking.isBookingTimeValid());
    }

}