package ru.practicum.shareit.exception;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserAlreadyExistExceptionTest {

    @Test
    void getEntity() {
        User user = new User(1L, "name", "user@mail.ru", new ArrayList<>(), new ArrayList<>());
        UserAlreadyExistException userAlreadyExistException = new UserAlreadyExistException(user);
        assertEquals(user, userAlreadyExistException.getEntity());
    }

}