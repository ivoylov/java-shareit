package ru.practicum.shareit.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.user.UserRepository;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ErrorHandlerTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    void handlerUserAlreadyExistException() {
        User user1 = new User(1l, "name", "user@email.ru", new ArrayList<>(), new ArrayList<>());
        User user2 = new User(2l, "name", "user@email.ru", new ArrayList<>(), new ArrayList<>());
        when(userRepository.findAll()).thenReturn(new ArrayList<>(List.of(user1)));
        assertThrows(UserAlreadyExistException.class, () -> userService.create(user2));
    }

    @Test
    void handlerEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> userService.get(1L));
    }

}