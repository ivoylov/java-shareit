package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusTest {

    private final Status canceled = Status.CANCELED;
    private final Status approved = Status.APPROVED;
    private final Status rejected = Status.REJECTED;
    private final Status waiting = Status.WAITING;

    @Test
    void getId_whenExpectedCorrectIdForAllStatus() {
        assertEquals(0, canceled.getId());
        assertEquals(1, waiting.getId());
        assertEquals(2, approved.getId());
        assertEquals(3, rejected.getId());
    }

    @Test
    void values_whenExpectedEqualsArrays() {
        Status[] values = {canceled, waiting, approved, rejected};
        assertEquals(0, Arrays.compare(values, Status.values()));
    }

    @Test
    void valueOf_WhenExpectedEqualsStatus() {
        assertEquals(Status.CANCELED, Status.valueOf("CANCELED"));
        assertEquals(Status.WAITING, Status.valueOf("WAITING"));
        assertEquals(Status.APPROVED, Status.valueOf("APPROVED"));
        assertEquals(Status.REJECTED, Status.valueOf("REJECTED"));
    }

}