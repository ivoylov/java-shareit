package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class UserDtoOutShortTest {

    private UserDtoOutShort userDtoOutShort;

    @Test
    void noArgsConstructor_thenExpectedNull() {
        userDtoOutShort = new UserDtoOutShort();
        assertNull(userDtoOutShort.getId());
    }

}