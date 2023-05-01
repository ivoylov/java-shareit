package ru.practicum.shareit.user;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.storage.UserStorage;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.user.storage.InMemoryUserStorage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    private final UserStorage userStorage = new InMemoryUserStorage();
    private final UserService userService = new UserService(userStorage);

    @Test
    void shouldCreateUser() {
        User user = new User( "userName", "user@mail.ru");
        User createdUser = userService.create(user);
        assertEquals("userName", createdUser.getName());
        assertEquals("user@mail.ru", createdUser.getEmail());
        assertEquals(1, user.getId());
    }

    @Test
    void shouldNotCreateSameUser() {
        User user = new User( "userName", "user@mail.ru");
        userService.create(user);
        User createdUser = userService.create(user);
        assertEquals(1, createdUser.getId());
    }

}
