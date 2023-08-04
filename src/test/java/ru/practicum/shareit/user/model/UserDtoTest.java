package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoTest {

    private UserDtoIn userDto;
    private UserDtoIn testUserDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDtoIn(1L, "name", "user@email.ru", new ArrayList<>(), new ArrayList<>());
        testUserDto = new UserDtoIn(1L, "name", "user@email.ru", new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void getId() {
        assertEquals(1L, userDto.getId());
    }

    @Test
    void getName() {
        assertEquals("name", userDto.getName());
    }

    @Test
    void getEmail() {
        assertEquals("user@email.ru", userDto.getEmail());
    }

    @Test
    void setId() {
        userDto.setId(2L);
        assertEquals(2L, userDto.getId());
    }

    @Test
    void setName() {
        userDto.setName("newName");
        assertEquals("newName", userDto.getName());
    }

    @Test
    void setEmail() {
        userDto.setEmail("newUser@email.ru");
        assertEquals("newUser@email.ru", userDto.getEmail());
    }

    @Test
    void testEquals() {
        assertEquals(testUserDto, userDto);
    }

    @Test
    void testHashCode() {
        assertEquals(testUserDto.hashCode(), userDto.hashCode());
    }

    @Test
    void testToString() {
        String string = "UserDto(id=1, name=name, email=user@email.ru, items=[], bookings=[])";
        assertEquals(string, userDto.toString());
    }

    @Test
    void builder() {
        UserDtoIn newUser = UserDtoIn.builder()
                .id(1L)
                .name("name")
                .email("user@email.ru")
                .items(new ArrayList<>())
                .bookings(new ArrayList<>())
                .build();
        assertEquals(newUser, userDto);
    }

}