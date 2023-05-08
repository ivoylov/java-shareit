package ru.practicum.shareit.user;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.InMemoryUserStorage;
import ru.practicum.shareit.user.storage.UserStorage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStorageTest {

    private final UserStorage userStorage = new InMemoryUserStorage();

    @Test
    void shouldCreateUserWithCorrectName() {
        User user = new User("user1", "user1@mail.ru");
        User createdUser = userStorage.create(user);
        assertEquals(user.getName(), createdUser.getName());
    }

    @Test
    void shouldCreateUserWithCorrectEmail() {
        User user = new User("user2", "user2@mail.ru");
        User createdUser = userStorage.create(user);
        assertEquals(user.getEmail(), createdUser.getEmail());
    }

    @Test
    void shouldCreateUserWithCorrectId() {
        User user = new User("user3", "user3@mail.ru");
        User createdUser = userStorage.create(user);
        assertEquals(user.getId(), createdUser.getId());
    }

    @Test
    void shouldNotCreateSameUser() {
        User user1 = new User("user1", "user1@mail.ru");
        userStorage.create(user1);
        User user2 = userStorage.create(user1);
        assertEquals(user1.getId(), user2.getId());
    }

}
