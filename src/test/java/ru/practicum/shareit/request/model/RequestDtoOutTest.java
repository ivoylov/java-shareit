package ru.practicum.shareit.request.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class RequestDtoOutTest {

    private RequestDtoOut requestDtoOut;
    LocalDateTime created;

    @BeforeEach
    void setUp() {
        created = LocalDateTime.now();
        requestDtoOut = new RequestDtoOut(1L, "description", created, Collections.emptyList());
    }

    @Test
    void getId() {
        assertEquals(1L, requestDtoOut.getId());
    }

    @Test
    void getDescription() {
        assertEquals("description", requestDtoOut.getDescription());
    }

    @Test
    void getCreated() {
        assertEquals(created, requestDtoOut.getCreated());
    }

    @Test
    void getItems() {
        assertEquals(Collections.emptyList(), requestDtoOut.getItems());
    }

    @Test
    void noArgsConstructor() {
        RequestDtoOut newRequestDtoOut = new RequestDtoOut();
        assertNull(newRequestDtoOut.getId());
        assertNull(newRequestDtoOut.getItems());
        assertNull(newRequestDtoOut.getDescription());
        assertNull(newRequestDtoOut.getCreated());
    }

/*    @Test
    void setId() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void setCreated() {
    }

    @Test
    void setItems() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }

    @Test
    void builder() {
    }*/

}