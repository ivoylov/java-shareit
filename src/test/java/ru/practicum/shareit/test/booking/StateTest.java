package ru.practicum.shareit.test.booking;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.State;

import static org.junit.jupiter.api.Assertions.*;
import static ru.practicum.shareit.booking.model.State.*;

class StateTest {

    @Test
    void getId() {
        assertEquals(0, UNSUPPORTED_STATUS.getId());
        assertEquals(1, WAITING.getId());
        assertEquals(2, APPROVED.getId());
        assertEquals(3, REJECTED.getId());
        assertEquals(4, CURRENT.getId());
        assertEquals(5, PAST.getId());
        assertEquals(6, FUTURE.getId());
        assertEquals(7, ALL.getId());
    }

}