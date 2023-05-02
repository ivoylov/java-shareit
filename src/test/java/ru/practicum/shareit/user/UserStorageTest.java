package ru.practicum.shareit.user;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.user.storage.UserStorage;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.InMemoryUserStorage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStorageTest {

    private final UserStorage userStorage = new InMemoryUserStorage();

    @Test
    void shouldCreateUser() {
        User user = new User( "userName", "user@mail.ru");
        User createdUser = userStorage.create(user);
        assertEquals("userName", createdUser.getName());
        assertEquals("user@mail.ru", createdUser.getEmail());
        assertEquals(1, user.getId());
    }

    @Test
    void shouldNotCreateSameUser() {
        User user = new User( "userName", "user@mail.ru");
        userStorage.create(user);
        User createdUser = userStorage.create(user);
        assertEquals(1, createdUser.getId());
    }

}
