package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "name", "email", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void toShortUserDtoOut_thenExpectedEqualsUserWithShortUserDtoOut() {
        UserDtoOutShort shortUserDtoOut = new UserDtoOutShort(1L);
        assertEquals(shortUserDtoOut, UserMapper.toShortUserDtoOut(user));
    }

    @Test
    void toUserDtoOut_thenExpectedEqualsUserWithUserDtoOut() {
        UserDtoOut userDtoOut = new UserDtoOut(1L, "name", "email");
        assertEquals(userDtoOut, UserMapper.toUserDtoOut(user));
    }

    @Test
    void toUser_thenExpectedEqualsFieldsWithUserFieldsAndUserDtoInFields() {
        user.setId(null);
        UserDtoIn userDtoIn = new UserDtoIn("name", "email");
        assertEquals(user.getId(), UserMapper.toUser(userDtoIn).getId());
        assertEquals(user.getName(), UserMapper.toUser(userDtoIn).getName());
        assertEquals(user.getEmail(), UserMapper.toUser(userDtoIn).getEmail());
    }

    @Test
    void toUserDtoOutList_thenExpectedEqualsWithListOfUserDtoOut() {
        UserDtoOut userDtoOut = new UserDtoOut(1L, "name", "email");
        assertEquals(List.of(userDtoOut), UserMapper.toUserDtoOutList(List.of(user)));
    }

}