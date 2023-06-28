package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private User testUser;

    @BeforeEach
    void setUp() {
        user = new User(1L, "name", "user@email.ru");
        testUser = new User(1L, "name", "user@email.ru");
    }

    @Test
    void getId() {
        assertEquals(1L, user.getId());
    }

    @Test
    void getName() {
        assertEquals("name", user.getName());
    }

    @Test
    void getEmail() {
        assertEquals("user@email.ru", user.getEmail());
    }

    @Test
    void setId() {
        user.setId(2L);
        assertEquals(2L, user.getId());
    }

    @Test
    void setName() {
        user.setName("newName");
        assertEquals("newName", user.getName());
    }

    @Test
    void setEmail() {
        user.setEmail("newUser@email.ru");
        assertEquals("newUser@email.ru", user.getEmail());
    }

    @Test
    void testEquals() {
        assertEquals(testUser, user);
    }

    @Test
    void testHashCode() {
        assertEquals(testUser.hashCode(), user.hashCode());
    }

    @Test
    void testToString() {
        String string = "User(id=1, name=name, email=user@email.ru)";
        assertEquals(string, user.toString());
    }

    @Test
    void builder() {
        User newUser = User.builder()
                .id(1L)
                .name("name")
                .email("user@email.ru")
                .build();
        assertEquals(newUser, user);
    }

}