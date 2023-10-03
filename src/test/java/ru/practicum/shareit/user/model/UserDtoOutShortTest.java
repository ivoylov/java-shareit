package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoOutShortTest {

    private UserDtoOutShort userDtoOutShort;

    @Test
    void noArgsConstructor() {
        userDtoOutShort = new UserDtoOutShort();
        assertNull(userDtoOutShort.getId());
    }

}