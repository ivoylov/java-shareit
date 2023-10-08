package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoOutTest {

    private UserDtoOut userDtoOut;

    @BeforeEach
    void setUp() {
        userDtoOut = new UserDtoOut(1L, "name", "email");
    }

    @Test
    void getId_thenExpected1() {
        assertEquals(1L, userDtoOut.getId());
    }

    @Test
    void getName_thenExpectedNameAsString() {
        assertEquals("name", userDtoOut.getName());
    }

    @Test
    void getEmail_thenExpectedEmailAsString() {
        assertEquals("email", userDtoOut.getEmail());
    }

    @Test
    void setId_whenExpected2() {
        userDtoOut.setId(2L);
        assertEquals(2L, userDtoOut.getId());
    }

    @Test
    void setName_thenExpectedNewNameAsString() {
        userDtoOut.setName("newName");
        assertEquals("newName", userDtoOut.getName());
    }

    @Test
    void setEmail_thenExpectedNewEmailAsString() {
        userDtoOut.setEmail("newEmail");
        assertEquals("newEmail", userDtoOut.getEmail());
    }

    @Test
    void testEquals_thenExpectedEqualsWithNewUserDotOut() {
        UserDtoOut newUserDtoOut = new UserDtoOut(1L, "name", "email");
        assertEquals(newUserDtoOut, userDtoOut);
    }

    @Test
    void testHashCode_thenExpectedSameHashCodeWithNewUserDtoOut() {
        UserDtoOut newUserDtoOut = new UserDtoOut(1L, "name", "email");
        assertEquals(newUserDtoOut.hashCode(), userDtoOut.hashCode());
    }

    @Test
    void testToString_thenExpectedEqualsWithTargetString() {
        String targetString = "UserDtoOut(id=1, name=name, email=email)";
        assertEquals(targetString, userDtoOut.toString());
    }

    @Test
    void testBuilder_thenExpectedEqualsWithNewUserDtoOut() {
        UserDtoOut newUserDtoOut = UserDtoOut.builder().id(1L).name("name").email("email").build();
        assertEquals(newUserDtoOut, userDtoOut);
    }

}