package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    void getItemId_whenExpected1() {
        assertEquals(1L, bookingDtoIn.getItemId());
    }

    @Test
    void getStart_whenExpectedStartDate() {
        assertEquals(start, bookingDtoIn.getStart());
    }

    @Test
    void getEnd_whenExpectedEndDate() {
        assertEquals(end, bookingDtoIn.getEnd());
    }

    @Test
    void setItemId_whenExpected2() {
        bookingDtoIn.setItemId(2L);
        assertEquals(2L, bookingDtoIn.getItemId());
    }

    @Test
    void setStart_whenExpectedStartPlus2Hours() {
        bookingDtoIn.setStart(start.plusHours(1));
        assertEquals(start.plusHours(1), bookingDtoIn.getStart());
    }

    @Test
    void setEnd_whenExpectedEndPlus2Hours() {
        bookingDtoIn.setEnd(end.plusHours(2));
        assertEquals(end.plusHours(2), bookingDtoIn.getEnd());
    }

    @Test
    void testEquals_whenExpectedObjectsToBeEqual() {
        BookingDtoIn newBookingDtoIn = new BookingDtoIn(1L, start, end);
        assertEquals(newBookingDtoIn, bookingDtoIn);
    }

    @Test
    void testHashCode_whenExpectedSameHashCode() {
        BookingDtoIn newBookingDtoIn = new BookingDtoIn(1L, start, end);
        assertEquals(bookingDtoIn.hashCode(), newBookingDtoIn.hashCode());
    }

    @Test
    void testToString_whenExpectedTargetString() {
        String targetString = "BookingDtoIn(itemId=1, start=" + start + ", end=" + end + ")";
        assertEquals(targetString, bookingDtoIn.toString());
    }

    @Test
    void testBuilder_whenExpectedCorrectFields() {
        BookingDtoIn newBookingDtoIn = BookingDtoIn.builder()
                .start(start)
                .end(end)
                .itemId(1L)
                .build();
        assertEquals(newBookingDtoIn, bookingDtoIn);
    }

    @Test
    void testNoArgsConstructor_whenExpectedNullFields() {
        BookingDtoIn newBookingDtoIn = new BookingDtoIn();
        assertNull(newBookingDtoIn.getItemId());
        assertNull(newBookingDtoIn.getStart());
        assertNull(newBookingDtoIn.getEnd());
    }

}