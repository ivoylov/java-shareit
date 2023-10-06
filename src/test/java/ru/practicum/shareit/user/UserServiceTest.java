package ru.practicum.shareit.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        List<Item> items = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();
        List<Request> requests = new ArrayList<>();
        user = new User(1L, "name", "email", items, bookings, requests);
    }

    @Test
    void create() {
        Mockito.when(userRepository.save(any())).thenReturn(user);
        assertEquals(userService.create(user), user);
    }

    @Test
    void update() {
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
        assertEquals(userService.update(user, 1L), user);
    }

    @Test
    void get() {
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
        assertEquals(userService.get(1L), user);
    }

    @Test
    void getAll() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        assertEquals(List.of(user), userService.getAll());
    }

    @Test
    void delete() {
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
        assertEquals(userService.delete(1L), user);
    }

}