package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemDtoOutShort;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.model.UserDtoOutShort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingMapperTest {

    private LocalDateTime start;
    private LocalDateTime end;
    private User booker;
    private Item item;
    private BookingDtoOut bookingDtoOut;
    private Booking booking;
    UserDtoOutShort shortBooker = new UserDtoOutShort(1L);
    ItemDtoOutShort shortItem = new ItemDtoOutShort(1L, "itemName", null, "description", true);

    @BeforeEach
    void setUp() {
        booker = new User(1L, "name", "email@mail.ru", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        item = new Item(1L, "itemName", "description", true, booker, new ArrayList<>(), new ArrayList<>(), null);
        start = LocalDateTime.now().plusHours(1);
        end = LocalDateTime.now().plusHours(2);
        shortBooker = new UserDtoOutShort(1L);
        shortItem = new ItemDtoOutShort(1L, "itemName", null, "description", true);
        bookingDtoOut = new BookingDtoOut(1L, start, end, Status.APPROVED, shortBooker, shortItem);
        booking = Booking.builder()
                .id(1L)
                .booker(booker)
                .item(item)
                .start(start)
                .end(end)
                .status(Status.APPROVED)
                .build();
    }

    @Test
    void toShortBookingDtoOut() {
        BookingDtoOutShort shortBooking = new BookingDtoOutShort(1L, 1L);
        Booking booking = new Booking(1L, LocalDateTime.now(), LocalDateTime.now(), Status.WAITING, booker, new Item());
        assertEquals(shortBooking, BookingMapper.toBookingDtoOutShort(booking));
    }

    @Test
    void toBooking() {
        BookingDtoIn bookingDtoIn = new BookingDtoIn(1L, start, end);
        Booking bookingToTranslate = new Booking(null, start, end, null, null, null);
        assertEquals(bookingToTranslate, BookingMapper.toBooking(bookingDtoIn));
    }

    @Test
    void toBookingDtoOut() {
        assertEquals(bookingDtoOut, BookingMapper.toBookingDtoOut(booking));
    }

    @Test
    void toBookingDtoOutList() {
        assertEquals(new ArrayList<>(List.of(bookingDtoOut)), BookingMapper.toBookingDtoOutList(List.of(booking)));
    }

}