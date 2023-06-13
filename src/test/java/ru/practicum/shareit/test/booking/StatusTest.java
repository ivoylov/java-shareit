package ru.practicum.shareit.test.booking;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.Status;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void getId() {
        assertEquals(0, Status.CANCELED.getId());
        assertEquals(1, Status.WAITING.getId());
        assertEquals(2, Status.APPROVED.getId());
        assertEquals(3, Status.REJECTED.getId());
    }

}