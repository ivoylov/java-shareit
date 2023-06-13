package ru.practicum.shareit.test.booking;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.dto.ItemDtoMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingDtoMapperTest {

    private Booking booking;
    private BookingDto bookingDto;
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Item item;
    private User user;
    @Mock
    private ItemService itemService;
    @Mock
    private UserService userService;
    private String description;
    private String name;
    private String email;

    @BeforeEach
    void setUp() {
        description = "description";
        name = "name";
        email = "email@mail.ru";
        item = Item.builder()
                .id(id)
                .ownerId(id)
                .description(description)
                .available(true)
                .requestId(id)
                .name(name)
                .build();
        user = User.builder()
                .id(id)
                .email(email)
                .name(name)
                .build();
        start = LocalDateTime.now();
        end = start.plusHours(1);
        id = 1L;
        booking = Booking.builder()
                .id(id)
                .ownerId(id)
                .start(start)
                .end(end)
                .status(Status.APPROVED)
                .itemId(id)
                .build();
        bookingDto = BookingDto.builder()
                .id(id)
                .ownerId(id)
                .start(start)
                .end(end)
                .status(Status.APPROVED)
                .itemId(id)
                .booker(user)
                .item(item)
                .build();
    }

    @Test
    void toBookingDto() {
        assertEquals(bookingDto, BookingDtoMapper.toBookingDto(booking, item, user));
    }

    @Test
    void toBooking() {
        assertEquals(booking, BookingDtoMapper.toBooking(bookingDto));
    }

    /*
    @Test
    void toBookingDtoList() {
        List<BookingDto> bookingDtoList = new ArrayList<>(List.of(bookingDto));
        List<Booking> bookingList = new ArrayList<>(List.of(booking));
        assertEquals(bookingDtoList, BookingDtoMapper.toBookingDtoList(bookingList, itemService, userService));
    }
    */

}