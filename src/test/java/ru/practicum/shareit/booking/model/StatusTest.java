package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatusTest {

    @Test
    void getId() {
        assertEquals(1, Status.WAITING.getId());
        assertEquals(2, Status.APPROVED.getId());
        assertEquals(3, Status.REJECTED.getId());
        assertEquals(0, Status.CANCELED.getId());
    }

    @Test
    void values() {
        Status[] statuses = {Status.WAITING, Status.APPROVED, Status.REJECTED, Status.CANCELED};
        assertTrue(Arrays.equals(statuses, Status.values()));
    }

    @Test
    void valueOf() {
        assertEquals(Status.WAITING, Status.valueOf("WAITING"));
        assertEquals(Status.APPROVED, Status.valueOf("APPROVED"));
        assertEquals(Status.REJECTED, Status.valueOf("REJECTED"));
        assertEquals(Status.CANCELED, Status.valueOf("CANCELED"));
    }

}