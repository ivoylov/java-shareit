package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookingDtoOutShortTest {

    private BookingDtoOutShort bookingDtoOutShort;

    @BeforeEach
    void setUp() {
        bookingDtoOutShort = new BookingDtoOutShort(1L, 2L);
    }

    @Test
    void getId_whenExpected1() {
        assertEquals(1L, bookingDtoOutShort.getId());
    }

    @Test
    void getBookerId_whenExpected2() {
        assertEquals(2L, bookingDtoOutShort.getBookerId());
    }

    @Test
    void setId_whenExpected3() {
        bookingDtoOutShort.setId(3L);
        assertEquals(3L, bookingDtoOutShort.getId());
    }

    @Test
    void setBookerId_whenExpected4() {
        bookingDtoOutShort.setBookerId(4L);
        assertEquals(4L, bookingDtoOutShort.getBookerId());
    }

    @Test
    void testEquals_whenExpectedObjectsToBeEquals() {
        BookingDtoOutShort newBooking = new BookingDtoOutShort(1L, 2L);
        assertEquals(newBooking, bookingDtoOutShort);
    }

    @Test
    void testHashCode_whenExpectedSameHashCode() {
        BookingDtoOutShort newBooking = new BookingDtoOutShort(1L, 2L);
        assertEquals(newBooking.hashCode(), bookingDtoOutShort.hashCode());
    }

    @Test
    void testToString_whenExpectedTargetString() {
        String targetString = "BookingDtoOutShort(id=1, bookerId=2)";
        assertEquals(targetString, bookingDtoOutShort.toString());
    }

    @Test
    void testBuilder_whenExpectedCorrectFields() {
        BookingDtoOutShort newBooking = BookingDtoOutShort.builder()
                .id(1L)
                .bookerId(2L)
                .build();
        assertEquals(newBooking, bookingDtoOutShort);
    }

    @Test
    void testNoArgsConstructor_whenExpectedNullFields() {
        BookingDtoOutShort newBookingDtoOutShort = new BookingDtoOutShort();
        assertNull(newBookingDtoOutShort.getBookerId());
        assertNull(newBookingDtoOutShort.getId());
    }

}