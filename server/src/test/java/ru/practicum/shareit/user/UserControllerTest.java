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
import ru.practicum.shareit.user.model.UserDtoIn;
import ru.practicum.shareit.user.model.UserDtoOut;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    private User user;
    private UserDtoOut userDtoOut;
    private UserDtoIn userDtoIn;

    @BeforeEach
    void setUp() {
        List<Booking> bookings = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        List<Request> requests = new ArrayList<>();
        userDtoOut = UserDtoOut.builder()
                .id(1L)
                .name("name")
                .email("email")
                .build();
        user = User.builder()
                .id(1L)
                .name("name")
                .email("email")
                .bookings(bookings)
                .items(items)
                .requests(requests)
                .build();
        userDtoIn = UserDtoIn.builder()
                .name("name")
                .email("email")
                .build();
    }

    @Test
    void create_thenExpectedEqualsWithUserDtoOutVariable() {
        Mockito.when(userService.create(any())).thenReturn(user);
        assertEquals(userDtoOut, userController.create(userDtoIn));
    }

    @Test
    void update_thenExpectedEqualsWithUserDtoOutVariable() {
        user.setName("newName");
        user.setEmail("newEmail");
        Mockito.when(userService.update(any(), any())).thenReturn(user);
        userDtoOut.setEmail("newEmail");
        userDtoOut.setName("newName");
        assertEquals(userDtoOut, userController.update(userDtoIn, 1L));
    }

    @Test
    void delete_thenExpectedEqualsWithUserDtoOutVariable() {
        Mockito.when(userService.delete(1L)).thenReturn(user);
        assertEquals(userDtoOut, userController.delete(1L));
    }

    @Test
    void get_thenExpectedEqualsWithUserDtoOutVariable() {
        Mockito.when(userService.get(1L)).thenReturn(user);
        assertEquals(userDtoOut, userController.get(1L));
    }

    @Test
    void getAll_thenExpectedEqualsWithUserDtoOutVariable() {
        Mockito.when(userService.getAll()).thenReturn(List.of(user));
        assertEquals(List.of(userDtoOut), userController.getAll());
    }

}