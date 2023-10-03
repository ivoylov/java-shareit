package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingDtoInTest {

    private LocalDateTime start;
    private LocalDateTime end;
    private BookingDtoIn bookingDtoIn;

    @BeforeEach
    void setUp() {
        start = LocalDateTime.now().plusHours(1);
        end = LocalDateTime.now().plusHours(2);
        bookingDtoIn = new BookingDtoIn(1L, start, end);
    }

    @Test
    void getItemId() {
        assertEquals(1L, bookingDtoIn.getItemId());
    }

    @Test
    void getStart() {
        assertEquals(start, bookingDtoIn.getStart());
    }

    @Test
    void getEnd() {
        assertEquals(end, bookingDtoIn.getEnd());
    }

    @Test
    void setItemId() {
        bookingDtoIn.setItemId(2L);
        assertEquals(2L, bookingDtoIn.getItemId());
    }

    @Test
    void setStart() {
        bookingDtoIn.setStart(start.plusHours(1));
        assertEquals(start.plusHours(1), bookingDtoIn.getStart());
    }

    @Test
    void setEnd() {
        bookingDtoIn.setEnd(end.plusHours(2));
        assertEquals(end.plusHours(2), bookingDtoIn.getEnd());
    }

    @Test
    void testEquals() {
        BookingDtoIn newBookingDtoIn = new BookingDtoIn(1L, start, end);
        assertEquals(newBookingDtoIn, bookingDtoIn);
    }

    @Test
    void testHashCode() {
        BookingDtoIn newBookingDtoIn = new BookingDtoIn(1L, start, end);
        assertEquals(bookingDtoIn.hashCode(), newBookingDtoIn.hashCode());
    }

    @Test
    void testToString() {
        String targetString = "BookingDtoIn(itemId=1, start=" + start + ", end=" + end + ")";
        assertEquals(targetString, bookingDtoIn.toString());
    }

    @Test
    void builder() {
        BookingDtoIn newBookingDtoIn = BookingDtoIn.builder()
                .start(start)
                .end(end)
                .itemId(1L)
                .build();
        assertEquals(newBookingDtoIn, bookingDtoIn);
    }

    @Test
    void noArgsConstructor() {
        BookingDtoIn newBookingDtoIn = new BookingDtoIn();
        assertNull(newBookingDtoIn.getItemId());
        assertNull(newBookingDtoIn.getStart());
        assertNull(newBookingDtoIn.getEnd());
    }

}