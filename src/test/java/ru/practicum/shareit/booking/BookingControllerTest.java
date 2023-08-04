package ru.practicum.shareit.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingDtoOut;
import ru.practicum.shareit.booking.model.BookingMapper;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;
    @Mock
    private BookingService bookingService;
    private User user;
    private Item item;
    private LocalDateTime start;
    private LocalDateTime end;

    @BeforeEach
    void setUp() {
        user = new User();
        item = new Item();
        user.setId(1L);
        item.setId(1L);
        start = LocalDateTime.of(2020, 1, 1, 12, 0);
        end = LocalDateTime.of(2020, 1, 2, 12, 0);
    }

    @Test
    void create() {
        BookingDtoOut bookingDtoOutToCreate = new BookingDtoOut();
        bookingDtoOutToCreate.setStart(start);
        bookingDtoOutToCreate.setEnd(end);
        bookingDtoOutToCreate.setBooker(user);
        bookingDtoOutToCreate.setItem(item);
        Booking createdBooking = Booking.builder()
                .id(1L)
                .booker(user)
                .item(item)
                .start(start)
                .end(end)
                .status(Status.WAITING)
                .build();
        BookingDtoOut createdBookingDtoOut = BookingMapper.toBookingDtoOut(createdBooking);
        when(bookingService.create(any())).thenReturn(createdBooking);
        assertEquals(bookingController.create(bookingDtoOutToCreate, 1L), createdBookingDtoOut);
    }

    @Test
    void approved() {

    }


}