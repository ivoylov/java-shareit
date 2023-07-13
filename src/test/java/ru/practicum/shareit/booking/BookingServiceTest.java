package ru.practicum.shareit.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @InjectMocks
    BookingService bookingService;
    @Mock
    BookingRepository bookingRepository;

    private LocalDateTime start;
    private LocalDateTime end;
    private User user;
    private Item item;

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
        Booking bookingToCreate = Booking.builder()
                .booker(user)
                .item(item)
                .build() ;
        Booking createdBooking = Booking.builder()
                .id(1L)
                .status(Status.WAITING)
                .start(start)
                .end(end)
                .booker(user)
                .item(item)
                .build();
        when(bookingRepository.save(bookingToCreate)).thenReturn(createdBooking);
        assertEquals(bookingService.create(bookingToCreate), createdBooking);
    }

    @Test
    void update() {
    }

    @Test
    void isExist() {
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
    }

    @Test
    void delete() {
    }

}