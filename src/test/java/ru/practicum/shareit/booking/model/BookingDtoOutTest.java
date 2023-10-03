package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.ItemDtoOutShort;
import ru.practicum.shareit.user.model.UserDtoOutShort;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingDtoOutTest {

    private BookingDtoOut bookingDtoOut;
    private UserDtoOutShort userDtoOutShort;
    private ItemDtoOutShort itemDtoOutShort;
    private LocalDateTime start;
    private LocalDateTime end;

    @BeforeEach
    void setUp() {
        start = LocalDateTime.now().plusHours(1);
        end = start.plusHours(2);
        userDtoOutShort = new UserDtoOutShort(1L);
        itemDtoOutShort = new ItemDtoOutShort(1L, "itemName", 1L, "description", true);
        bookingDtoOut = new BookingDtoOut(1L, start, end, Status.WAITING, userDtoOutShort, itemDtoOutShort);
    }

    @Test
    void getId() {
        assertEquals(1L, bookingDtoOut.getId());
    }

    @Test
    void getStart() {
        assertEquals(start, bookingDtoOut.getStart());
    }

    @Test
    void getEnd() {
        assertEquals(end, bookingDtoOut.getEnd());
    }

    @Test
    void getStatus() {
        assertEquals(Status.WAITING, bookingDtoOut.getStatus());
    }

    @Test
    void getBooker() {
        assertEquals(userDtoOutShort, bookingDtoOut.getBooker());
    }

    @Test
    void getItem() {
        assertEquals(itemDtoOutShort, bookingDtoOut.getItem());
    }

    @Test
    void setId() {
        bookingDtoOut.setId(2L);
        assertEquals(2L, bookingDtoOut.getId());
    }

    @Test
    void setStart() {
        bookingDtoOut.setStart(start.plusHours(5));
        assertEquals(start.plusHours(5), bookingDtoOut.getStart());
    }

    @Test
    void setEnd() {
        bookingDtoOut.setEnd(end.plusHours(5));
        assertEquals(end.plusHours(5), bookingDtoOut.getEnd());
    }

    @Test
    void setStatus() {
        bookingDtoOut.setStatus(Status.APPROVED);
        assertEquals(Status.APPROVED, bookingDtoOut.getStatus());
    }

    @Test
    void setBooker() {
        UserDtoOutShort newUserDtoOutShort = new UserDtoOutShort(2L);
        bookingDtoOut.setBooker(newUserDtoOutShort);
        assertEquals(newUserDtoOutShort, bookingDtoOut.getBooker());
    }

    @Test
    void setItem() {
        ItemDtoOutShort newItemDtoOutShort = new ItemDtoOutShort(2L, "newItemName", 1L, "description", true);
        bookingDtoOut.setItem(newItemDtoOutShort);
        assertEquals(newItemDtoOutShort, bookingDtoOut.getItem());
    }

    @Test
    void testEquals() {
        BookingDtoOut newBooking = new BookingDtoOut(1L, start, end, Status.WAITING, userDtoOutShort, itemDtoOutShort);
        assertEquals(newBooking, bookingDtoOut);
    }

    @Test
    void testHashCode() {
        BookingDtoOut newBooking = new BookingDtoOut(1L, start, end, Status.WAITING, userDtoOutShort, itemDtoOutShort);
        assertEquals(newBooking.hashCode(), bookingDtoOut.hashCode());
    }

    @Test
    void testToString() {
        String targetString = "BookingDtoOut(id=1, start=" + start + ", end=" + end + ", status=WAITING, " +
                "booker=UserDtoOutShort(id=1), " +
                "item=ItemDtoOutShort(id=1, name=itemName, requestId=1, description=description, available=true))";
        assertEquals(targetString, bookingDtoOut.toString());
    }

    @Test
    void builder() {
        BookingDtoOut newBookingDtoOut = BookingDtoOut.builder()
                .id(1L)
                .status(Status.WAITING)
                .start(start)
                .end(end)
                .booker(userDtoOutShort)
                .item(itemDtoOutShort)
                .build();
        assertEquals(newBookingDtoOut, bookingDtoOut);
    }

    @Test
    void noArgsConstructor() {
        BookingDtoOut newBookingDtoOut = new BookingDtoOut();
        assertNull(newBookingDtoOut.getItem());
        assertNull(newBookingDtoOut.getStatus());
        assertNull(newBookingDtoOut.getStart());
        assertNull(newBookingDtoOut.getEnd());
        assertNull(newBookingDtoOut.getId());
        assertNull(newBookingDtoOut.getBooker());
    }

}