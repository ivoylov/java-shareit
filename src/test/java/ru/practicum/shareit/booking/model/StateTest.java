package ru.practicum.shareit.booking.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StateTest {

    private final State all = State.ALL;
    private final State current = State.CURRENT;
    private final State past = State.PAST;
    private final State future = State.FUTURE;
    private final State waiting = State.WAITING;
    private final State rejected = State.REJECTED;
    private final State unsupportedStatus = State.UNSUPPORTED_STATUS;

    @Test
    void getId() {
        assertEquals(0, all.getId());
        assertEquals(1, current.getId());
        assertEquals(2, past.getId());
        assertEquals(3, future.getId());
        assertEquals(4, waiting.getId());
        assertEquals(5, rejected.getId());
        assertEquals(6, unsupportedStatus.getId());
    }

    @Test
    void values() {
        State[] values = {all, current, past, future, waiting, rejected, unsupportedStatus};
        assertEquals(0, Arrays.compare(values, State.values()));
    }

    @Test
    void valueOf() {
        assertEquals(State.ALL, State.valueOf("ALL"));
        assertEquals(State.CURRENT, State.valueOf("CURRENT"));
        assertEquals(State.PAST, State.valueOf("PAST"));
        assertEquals(State.FUTURE, State.valueOf("FUTURE"));
        assertEquals(State.WAITING, State.valueOf("WAITING"));
        assertEquals(State.REJECTED, State.valueOf("REJECTED"));
        assertEquals(State.UNSUPPORTED_STATUS, State.valueOf("UNSUPPORTED_STATUS"));
    }

}