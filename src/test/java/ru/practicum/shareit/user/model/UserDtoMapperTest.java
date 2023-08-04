package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoMapperTest {

    private User user;
    private UserDtoIn userDto;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("name")
                .email("user@email.ru")
                .build();
        userDto = UserDtoIn.builder()
                .id(1L)
                .name("name")
                .email("user@email.ru")
                .build();
    }

    @Test
    void toUserDto() {
        assertEquals(userDto, UserMapper.toUserDto(user));
    }

    @Test
    void toUser() {
        assertEquals(user, UserMapper.toUser(userDto));
    }

}