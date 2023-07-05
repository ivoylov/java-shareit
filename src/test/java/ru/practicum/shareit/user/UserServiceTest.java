package ru.practicum.shareit.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userStorage;
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
        when(userStorage.save(userToCreate)).thenReturn(createdUser);
        assertEquals(createdUser, userService.create(userToCreate));
    }

    @Test
    void update() {
        when(userStorage.findById(1L)).thenReturn(Optional.of(createdUser));
        createdUser.setName("newName");
        createdUser.setEmail("newUser@email.ru");
        assertEquals(createdUser, userService.update(userToCreate));
    }

    @Test
    void isExist() {
        when(userStorage.findById(1L).isPresent()).thenReturn(true);
        assertTrue(userService.isExist(1L));
    }

    @Test
    void get() {
        when(userStorage.getReferenceById(1L)).thenReturn(createdUser);
        assertEquals(createdUser, userService.get(1L));
    }

    @Test
    void getAll() {
        ArrayList<User> userList = new ArrayList<>(List.of(createdUser));
        when(userStorage.findAll()).thenReturn(userList);
        assertEquals(userService.getAll(), userList);
    }

    @Test
    void delete() {
        assertEquals(createdUser, userService.delete(1L));
    }

}