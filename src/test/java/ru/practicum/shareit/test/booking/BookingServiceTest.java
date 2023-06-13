package ru.practicum.shareit.test.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.service.BookingPageableService;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.booking.storage.InDbBookingStorage;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.item.storage.CommentRepository;
import ru.practicum.shareit.item.storage.InDbCommentStorage;
import ru.practicum.shareit.item.storage.InDbItemStorage;
import ru.practicum.shareit.item.storage.ItemRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.user.storage.InDbUserStorage;
import ru.practicum.shareit.user.storage.UserRepository;

import java.time.Clock;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private UserService userService;
    @Mock
    private ItemService itemService;
    @Mock
    private InDbBookingStorage bookingStorage;
    private Item item;
    private ItemDto itemDto;
    private User user;
    private Long id;
    private Long ownerId;
    private String name;
    private String description;
    private Booking booking;
    private BookingDto bookingDto;
    private String email;
    private LocalDateTime start;
    private LocalDateTime end;

    BookingServiceTest() {
    }

    @BeforeEach
    void setUp() {
        start = LocalDateTime.now(Clock.systemDefaultZone()).plusHours(1);
        end = start.plusHours(1);
        id = 1L;
        ownerId = id + 1;
        name = "name";
        description = "description";
        email = "user@email.ru";
        item = Item.builder()
                .id(id)
                .name(name)
                .description(description)
                .available(true)
                .ownerId(ownerId)
                .requestId(id)
                .build();
        user = User.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();
        itemDto = ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .ownerId(item.getOwnerId())
                .requestId(item.getRequestId())
                .build();
        booking = Booking.builder()
                .id(id)
                .itemId(item.getId())
                .status(Status.APPROVED)
                .end(end)
                .start(start)
                .ownerId(ownerId)
                .bookerId(id)
                .build();
        bookingDto = BookingDto.builder()
                .id(booking.getId())
                .start(start)
                .end(end)
                .itemId(item.getId())
                .item(item)
                .ownerId(ownerId)
                .owner(user)
                .booker(user)
                .bookerId(user.getId())
                .status(Status.APPROVED)
                .build();
        Mockito.when(userService.get(anyLong())).thenReturn(user);
        Mockito.when(userService.isExist(anyLong())).thenReturn(true);
        Mockito.when(itemService.get(anyLong())).thenReturn(itemDto);
    }

    @Test
    void create() {
        Mockito.when(bookingStorage.create(any())).thenReturn(booking);
        assertEquals(bookingService.create(bookingDto), bookingDto);
    }

    @Test
    void update() {
    }

    @Test
    void isExist() {
    }

    @Test
    void testIsExist() {
    }

    @Test
    void get() {
    }

    @Test
    void testGet() {
    }

    @Test
    void getAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAllForBooker() {
    }

    @Test
    void getAllForOwner() {
    }

}