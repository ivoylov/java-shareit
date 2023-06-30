package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoTest {

    private UserDto userDto;
    private UserDto testUserDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(1L, "name", "user@email.ru");
        testUserDto = new UserDto(1L, "name", "user@email.ru");
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
        String string = "UserDto(id=1, name=name, email=user@email.ru)";
        assertEquals(string, userDto.toString());
    }

    @Test
    void builder() {
        UserDto newUser = UserDto.builder()
                .id(1L)
                .name("name")
                .email("user@email.ru")
                .build();
        assertEquals(newUser, userDto);
    }

}