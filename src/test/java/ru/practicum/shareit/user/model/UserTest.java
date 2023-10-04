package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void updateUser() {
        User user = new User(1L, "name", "email@mail.ru", null, null, null);
        User newUser = new User(1L, "newName", "newEmail@mail.ru", null, null, null);
        user.updateUser(newUser);
        assertEquals(user.getName(), "newName");
        assertEquals(user.getEmail(), "newEmail@mail.ru");
    }

    @Test
    void builder() {
        User user = User.builder()
                .id(1L)
                .email("email@mail.ru")
                .name("name")
                .bookings(null)
                .requests(null)
                .items(null)
                .build();
        assertEquals(user.getName(), "name");
        assertEquals(user.getEmail(), "email@mail.ru");
    }

}
