package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoOutTest {

    private UserDtoOut userDtoOut;

    @BeforeEach
    void setUp() {
        userDtoOut = new UserDtoOut(1L, "name", "email");
    }

    @Test
    void getId() {
        assertEquals(1L, userDtoOut.getId());
    }

    @Test
    void getName() {
        assertEquals("name", userDtoOut.getName());
    }

    @Test
    void getEmail() {
        assertEquals("email", userDtoOut.getEmail());
    }

    @Test
    void setId() {
        userDtoOut.setId(2L);
        assertEquals(2L, userDtoOut.getId());
    }

    @Test
    void setName() {
        userDtoOut.setName("newName");
        assertEquals("newName", userDtoOut.getName());
    }

    @Test
    void setEmail() {
        userDtoOut.setEmail("newEmail");
        assertEquals("newEmail", userDtoOut.getEmail());
    }

    @Test
    void testEquals() {
        UserDtoOut newUserDtoOut = new UserDtoOut(1L, "name", "email");
        assertEquals(newUserDtoOut, userDtoOut);
    }

    @Test
    void testHashCode() {
        UserDtoOut newUserDtoOut = new UserDtoOut(1L, "name", "email");
        assertEquals(newUserDtoOut.hashCode(), userDtoOut.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("UserDtoOut(id=1, name=name, email=email)", userDtoOut.toString());
    }

    @Test
    void builder() {
        UserDtoOut newUserDtoOut = UserDtoOut.builder().id(1L).name("name").email("email").build();
        assertEquals(newUserDtoOut, userDtoOut);
    }

}