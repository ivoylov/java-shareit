package ru.practicum.shareit.user;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.exception.userException.UserValidationException;
import ru.practicum.shareit.user.controller.UserController;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.user.storage.InMemoryUserStorage;
import ru.practicum.shareit.user.storage.UserStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserControllerTest {

    private final UserStorage userStorage = new InMemoryUserStorage();
    private final UserService userService = new UserService(userStorage);
    private final UserController userController = new UserController(userService);

    @Test
    void shouldCreateUserWithCorrectName() {
        User user = new User( "user1", "user1@mail.ru");
        User createdUser = userController.create(user);
        assertEquals(user.getName(), createdUser.getName());
    }

    @Test
    void shouldCreateUserWithCorrectEmail() {
        User user = new User( "user2", "user2@mail.ru");
        User createdUser = userController.create(user);
        assertEquals(user.getEmail(), createdUser.getEmail());
    }

    @Test
    void shouldCreateUserWithCorrectId() {
        User user = new User( "user3", "user3@mail.ru");
        User createdUser = userController.create(user);
        assertEquals(user.getId(), createdUser.getId());
    }

    @Test
    void shouldNotCreateSameUser() {
        User user1 = new User( "user1", "user1@mail.ru");
        userController.create(user1);
        User user2 = userController.create(user1);
        assertEquals(user1.getId(), user2.getId());
    }

    @Test
    void shouldNotCreateUserWithIncorrectEmail() {
        User user = new User( "correctName", "usermail.ru");
        assertThrows(UserValidationException.class, () -> userController.create(user));
    }

    @Test
    void shouldNotCreateUserWithEmailWithoutDot() {
        User user = new User( "correctName", "user@mailru");
        assertThrows(UserValidationException.class, () -> userController.create(user));
    }

    @Test
    void shouldNotCreateUserWithNullEmail() {
        User user = new User( "correctName", null);
        assertThrows(UserValidationException.class, () -> userController.create(user));
    }

    @Test
    void shouldNotCreateUserWithEmptyName() {
        User user = new User( "", "user@mail.ru");
        assertThrows(UserValidationException.class, () -> userController.create(user));
    }

    @Test
    void shouldNotCreateUserWithBlankName() {
        User user = new User( " ", "user@mail.ru");
        assertThrows(UserValidationException.class, () -> userController.create(user));
    }

    @Test
    void shouldNotCreateUserWithNullName() {
        User user = new User( null, "user@mail.ru");
        assertThrows(UserValidationException.class, () -> userController.create(user));
    }

}
