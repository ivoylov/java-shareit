package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoInTest {

    private UserDtoIn userDtoIn;

    @BeforeEach
    void setUp() {
        userDtoIn = new UserDtoIn("name", "email@mail");
    }

    @Test
    void getName() {
        assertEquals("name", userDtoIn.getName());
    }

    @Test
    void getEmail() {
        assertEquals("email@mail", userDtoIn.getEmail());
    }

    @Test
    void setName() {
        userDtoIn.setName("newName");
        assertEquals("newName", userDtoIn.getName());
    }

    @Test
    void setEmail() {
        userDtoIn.setEmail("newEmail@mail.ru");
        assertEquals("newEmail@mail.ru", userDtoIn.getEmail());
    }

    @Test
    void testEquals() {
        UserDtoIn newUserDtoIn = new UserDtoIn("name", "email@mail");
        assertEquals(newUserDtoIn, userDtoIn);
    }

    @Test
    void testHashCode() {
        UserDtoIn newUserDtoIn = new UserDtoIn("name", "email@mail");
        assertEquals(newUserDtoIn.hashCode(), userDtoIn.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("UserDtoIn(name=name, email=email@mail)", userDtoIn.toString());
    }

    @Test
    void builder() {
        UserDtoIn newUserDtoIn = UserDtoIn.builder()
                .email("email@mail")
                .name("name")
                .build();
        assertEquals(userDtoIn, newUserDtoIn);
    }

}