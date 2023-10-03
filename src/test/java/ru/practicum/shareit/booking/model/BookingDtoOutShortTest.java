package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingDtoOutShortTest {

    private BookingDtoOutShort bookingDtoOutShort;

    @BeforeEach
    void setUp() {
        bookingDtoOutShort = new BookingDtoOutShort(1L, 2L);
    }

    @Test
    void getId() {
        assertEquals(1L, bookingDtoOutShort.getId());
    }

    @Test
    void getBookerId() {
        assertEquals(2L, bookingDtoOutShort.getBookerId());
    }

    @Test
    void setId() {
        bookingDtoOutShort.setId(3L);
        assertEquals(3L, bookingDtoOutShort.getId());
    }

    @Test
    void setBookerId() {
        bookingDtoOutShort.setBookerId(4L);
        assertEquals(4L, bookingDtoOutShort.getBookerId());
    }

    @Test
    void testEquals() {
        BookingDtoOutShort newBooking = new BookingDtoOutShort(1L, 2L);
        assertEquals(newBooking, bookingDtoOutShort);
    }

    @Test
    void testHashCode() {
        BookingDtoOutShort newBooking = new BookingDtoOutShort(1L, 2L);
        assertEquals(newBooking.hashCode(), bookingDtoOutShort.hashCode());
    }

    @Test
    void testToString() {
        String targetString = "BookingDtoOutShort(id=1, bookerId=2)";
        assertEquals(targetString, bookingDtoOutShort.toString());
    }

    @Test
    void builder() {
        BookingDtoOutShort newBooking = BookingDtoOutShort.builder()
                .id(1L)
                .bookerId(2L)
                .build();
        assertEquals(newBooking, bookingDtoOutShort);
    }

    @Test
    void noArgsConstructor() {
        BookingDtoOutShort newBookingDtoOutShort = new BookingDtoOutShort();
        assertNull(newBookingDtoOutShort.getBookerId());
        assertNull(newBookingDtoOutShort.getId());
    }

}