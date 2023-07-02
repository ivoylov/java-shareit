package ru.practicum.shareit.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.item.storage.InMemoryItemStorage;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.InMemoryUserStorage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private InMemoryUserStorage userStorage;
    private User userToCreate;
    private User createdUser;

    @BeforeEach
    void setUp() {
        userToCreate = User.builder()
                .email("user@email.ru")
                .name("name")
                .build();
        createdUser = User.builder()
                .id(1L)
                .email("user@email.ru")
                .name("name")
                .build();
    }

    @Test
    void create() {
        when(userStorage.create(userToCreate)).thenReturn(createdUser);
        assertEquals(createdUser, userService.create(userToCreate));
    }

    @Test
    void update() {
        createdUser.setName("newName");
        createdUser.setEmail("newUser@email.ru");
        when(userStorage.update(userToCreate)).thenReturn(createdUser);
        assertEquals(createdUser, userService.update(userToCreate));
    }

    @Test
    void isExist() {
        when(userStorage.isExist(1L)).thenReturn(true);
        assertTrue(userService.isExist(1L));
    }

    @Test
    void get() {
        when(userStorage.get(1L)).thenReturn(createdUser);
        assertEquals(createdUser, userService.get(1L));
    }

    @Test
    void getAll() {
        ArrayList<User> userList = new ArrayList<>(List.of(createdUser));
        when(userStorage.getAll()).thenReturn(userList);
        assertEquals(userService.getAll(), userList);
    }

    @Test
    void delete() {
        when(userStorage.delete(1L)).thenReturn(createdUser);
        assertEquals(createdUser, userService.delete(1L));
    }

}