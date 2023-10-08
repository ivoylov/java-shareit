package ru.practicum.shareit.request.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    void testToString_thenExpectedEqualsWithTargetString() {
        Request request = new Request(1L, "description", null, LocalDateTime.now(), null);
        String targetString = "id=1, description=description, user=null, createdDate=" + request.getCreatedDate() + ", items=null";
        assertEquals(targetString, request.toString());
    }

}