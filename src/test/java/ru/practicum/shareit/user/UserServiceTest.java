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

import static org.junit.jupiter.api.Assertions.assertEquals;
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