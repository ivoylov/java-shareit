package ru.practicum.shareit.user;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.storage.UserStorage;
import ru.practicum.shareit.user.controller.UserController;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.user.storage.InMemoryUserStorage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserControllerTest {

    private final UserStorage userStorage = new InMemoryUserStorage();
    private final UserService userService = new UserService(userStorage);
    private final UserController userController = new UserController(userService);

    @Test
    void shouldCreateUser() {
        User user = new User( "userName", "user@mail.ru");
        User createdUser = userController.create(user);
        assertEquals("userName", createdUser.getName());
        assertEquals("user@mail.ru", createdUser.getEmail());
        assertEquals(1, user.getId());
    }

    @Test
    void shouldNotCreateSameUser() {
        User user = new User( "userName", "user@mail.ru");
        userController.create(user);
        User createdUser = userController.create(user);
        assertEquals(1, createdUser.getId());
    }

    @Test
    void shouldNotCreateUserWithIncorrectEmail() {
        User user = new User( "", "usermail.ru");
        assertNull(userController.create(user));
    }

}
