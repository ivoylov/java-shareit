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
    void getName_thenExpectedName() {
        assertEquals("name", userDtoIn.getName());
    }

    @Test
    void getEmail_thenExpectedEmailAsString() {
        assertEquals("email@mail", userDtoIn.getEmail());
    }

    @Test
    void setName_thenExpectedNewNameAsString() {
        userDtoIn.setName("newName");
        assertEquals("newName", userDtoIn.getName());
    }

    @Test
    void setEmail_thenExpectedNewEmailAsString() {
        userDtoIn.setEmail("newEmail@mail.ru");
        assertEquals("newEmail@mail.ru", userDtoIn.getEmail());
    }

    @Test
    void testEquals_thenExpectedEqualsWithNewUserDtoIn() {
        UserDtoIn newUserDtoIn = new UserDtoIn("name", "email@mail");
        assertEquals(newUserDtoIn, userDtoIn);
    }

    @Test
    void testHashCode_thenExpectedSameHashCodeWithNewUserDtoIn() {
        UserDtoIn newUserDtoIn = new UserDtoIn("name", "email@mail");
        assertEquals(newUserDtoIn.hashCode(), userDtoIn.hashCode());
    }

    @Test
    void testToString_thenExpectedTargetString() {
        String targetString = "UserDtoIn(name=name, email=email@mail)";
        assertEquals(targetString, userDtoIn.toString());
    }

    @Test
    void testBuilder_thenExpectedEqualsWithNewUserDtoIn() {
        UserDtoIn newUserDtoIn = UserDtoIn.builder()
                .email("email@mail")
                .name("name")
                .build();
        assertEquals(userDtoIn, newUserDtoIn);
    }

}