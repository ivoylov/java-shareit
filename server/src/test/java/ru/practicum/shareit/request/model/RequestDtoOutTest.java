package ru.practicum.shareit.request.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RequestDtoOutTest {

    private RequestDtoOut requestDtoOut;
    LocalDateTime created;

    @BeforeEach
    void setUp() {
        created = LocalDateTime.now();
        requestDtoOut = new RequestDtoOut(1L, "description", created, Collections.emptyList());
    }

    @Test
    void getId_thenExpected1() {
        assertEquals(1L, requestDtoOut.getId());
    }

    @Test
    void getDescription_thenExpectedDescriptionAsString() {
        assertEquals("description", requestDtoOut.getDescription());
    }

    @Test
    void getCreated_thenExpectedEqualsWithCreatedVariable() {
        assertEquals(created, requestDtoOut.getCreated());
    }

    @Test
    void getItems_thenExpectedEmptyList() {
        assertEquals(Collections.emptyList(), requestDtoOut.getItems());
    }

    @Test
    void noArgsConstructor_thenExpectedNullFields() {
        RequestDtoOut newRequestDtoOut = new RequestDtoOut();
        assertNull(newRequestDtoOut.getId());
        assertNull(newRequestDtoOut.getItems());
        assertNull(newRequestDtoOut.getDescription());
        assertNull(newRequestDtoOut.getCreated());
    }

}