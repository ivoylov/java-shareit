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
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
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
        when(userRepository.save(userToCreate)).thenReturn(createdUser);
        assertEquals(createdUser, userService.create(userToCreate));
    }

    @Test
    void update() {
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.getReferenceById(1L)).thenReturn(createdUser);
        createdUser.setName("newName");
        createdUser.setEmail("newUser@email.ru");
        assertEquals(createdUser, userService.update(createdUser));
    }

    @Test
    void isExist() {
        when(userRepository.existsById(1L)).thenReturn(true);
        assertTrue(userService.isExist(1L));
    }

    @Test
    void get() {
        when(userRepository.getReferenceById(1L)).thenReturn(createdUser);
        when(userRepository.existsById(1L)).thenReturn(true);
        assertEquals(createdUser, userService.get(1L));
    }

    @Test
    void getAll() {
        ArrayList<User> userList = new ArrayList<>(List.of(createdUser));
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userService.getAll(), userList);
    }

    @Test
    void delete() {
        when(userRepository.getReferenceById(1L)).thenReturn(createdUser);
        when(userRepository.existsById(1L)).thenReturn(true);
        assertEquals(createdUser, userService.delete(1L));
    }

}