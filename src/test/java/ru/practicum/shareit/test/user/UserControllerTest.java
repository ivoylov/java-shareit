package ru.practicum.shareit.test.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.user.controller.UserController;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;
    @Mock
    UserService userService;
    private Long id;
    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        id = 1L;
        userDto = UserDto.builder()
               .id(id)
               .name("name")
               .email("user@email.ru")
               .build();
        user = User.builder()
                .id(id)
                .name("name")
                .email("user@email.ru")
                .build();
    }

    @Test
    void create() {
        Mockito.when(userService.create(user)).thenReturn(user);
        assertEquals(userDto, userController.create(userDto));
    }

    @Test
    void update() {
        Mockito.when(userService.update(user)).thenReturn(user);
        assertEquals(userController.update(userDto, id), userDto);
    }

    @Test
    void delete() {
        Mockito.when(userService.delete(id)).thenReturn(user);
        assertEquals(userController.delete(id), user);
    }

    @Test
    void get() {
        Mockito.when(userService.get(id)).thenReturn(user);
        assertEquals(userController.get(id), user);
    }

    @Test
    void getAll() {
        Mockito.when(userService.getAll()).thenReturn(new ArrayList<>(List.of(user)));
        assertEquals(userController.getAll(), new ArrayList<>(List.of(user)));
    }

}