package ru.practicum.shareit.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.model.UserDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("name")
                .email("user@email.ru")
                .build();
        userDto = UserDto.builder()
                .id(1L)
                .name("name")
                .email("user@email.ru")
                .build();
    }

    @Test
    void create() {
        when(userService.create(any())).thenReturn(user);
        assertEquals(userController.create(userDto), userDto);
    }

    @Test
    void update() {
        userDto.setName("newName");
        user.setName("newName");
        when(userService.update(user)).thenReturn(user);
        assertEquals(userController.update(userDto, 1L), userDto);
    }

    @Test
    void delete() {
        when(userService.delete(1L)).thenReturn(user);
        assertEquals(userController.delete(1L), userDto);
    }

    @Test
    void get() {
        when(userService.get(1L)).thenReturn(user);
        assertEquals(userDto, userController.get(1L));
    }

    @Test
    void getAll() {
        ArrayList<UserDto> userDtoList = new ArrayList<>(List.of(userDto));
        when(userService.getAll()).thenReturn(new ArrayList<>(List.of(user)));
        assertEquals(userDtoList, userController.getAll());
    }

}