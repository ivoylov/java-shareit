package ru.practicum.shareit.exception;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.request.model.Request;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestValidationExceptionTest {

    @Test
    void getEntity() {
        Request request = new Request();
        RequestValidationException exception = new RequestValidationException(request);
        assertEquals(request, exception.getEntity());
    }

}