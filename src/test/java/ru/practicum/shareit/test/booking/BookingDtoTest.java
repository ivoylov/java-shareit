package ru.practicum.shareit.test.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingDtoTest {

    private BookingDto bookingDto;

    @BeforeEach
    void setUo() {
        bookingDto = BookingDto.builder()
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusHours(1))
                .build();
    }

    @Test
    void isBookingTimeValid() {
        assertTrue(bookingDto.isBookingTimeValid());
    }

}