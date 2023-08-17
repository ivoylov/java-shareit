package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    private final Status canceled = Status.CANCELED;
    private final Status approved = Status.APPROVED;
    private final Status rejected = Status.REJECTED;
    private final Status waiting = Status.WAITING;

    @Test
    void getId() {
        assertEquals(0, canceled.getId());
        assertEquals(1, waiting.getId());
        assertEquals(2, approved.getId());
        assertEquals(3, rejected.getId());
    }

    @Test
    void values() {
        Status[] values = {canceled, waiting, approved, rejected};
        assertEquals(0, Arrays.compare(values, Status.values()));
    }

    @Test
    void valueOf() {
        assertEquals(Status.CANCELED, Status.valueOf("CANCELED"));
        assertEquals(Status.WAITING, Status.valueOf("WAITING"));
        assertEquals(Status.APPROVED, Status.valueOf("APPROVED"));
        assertEquals(Status.REJECTED, Status.valueOf("REJECTED"));
    }

}