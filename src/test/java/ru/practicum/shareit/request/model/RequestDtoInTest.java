package ru.practicum.shareit.request.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RequestDtoInTest {

    private RequestDtoIn requestDtoIn;

    @BeforeEach
    void setUp() {
        requestDtoIn = new RequestDtoIn("description");
    }

    @Test
    void getDescription() {
        assertEquals("description", requestDtoIn.getDescription());
    }

    @Test
    void setDescription() {
        requestDtoIn.setDescription("newDescription");
        assertEquals("newDescription", requestDtoIn.getDescription());
    }

    @Test
    void testEquals() {
        RequestDtoIn newRequestDtoIn = new RequestDtoIn("description");
        assertEquals(newRequestDtoIn,  requestDtoIn);
    }

    @Test
    void testHashCode() {
        RequestDtoIn newRequestDtoIn = new RequestDtoIn("description");
        assertEquals(newRequestDtoIn.hashCode(), requestDtoIn.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("RequestDtoIn(description=description)", requestDtoIn.toString());
    }

    @Test
    void builder() {
        RequestDtoIn newRequestDtoIn = RequestDtoIn.builder().description("description").build();
        assertEquals(newRequestDtoIn, requestDtoIn);
    }

    @Test
    void noArgsConstructor() {
        RequestDtoIn newRequestDtoIn = new RequestDtoIn();
        assertNull(newRequestDtoIn.getDescription());
    }

}