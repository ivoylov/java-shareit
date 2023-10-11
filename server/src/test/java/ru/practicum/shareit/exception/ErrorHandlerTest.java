package ru.practicum.shareit.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.exception.booking.BookingAlreadyApprovedException;
import ru.practicum.shareit.exception.booking.BookingAvailableException;
import ru.practicum.shareit.exception.booking.BookingTimeException;
import ru.practicum.shareit.exception.entity.EntityNotFoundException;
import ru.practicum.shareit.exception.item.ItemAvailableException;
import ru.practicum.shareit.exception.item.UnsupportedItemStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class ErrorHandlerTest {

    @Test
    void handlerUserAlreadyExistException_whenThrowUserAlreadyExistException() {
        ErrorHandler handler = new ErrorHandler();
        assertNull(handler.handlerUserAlreadyExistException(new UserAlreadyExistException("")).getError());
    }

    @Test
    void handlerBookingAlreadyApprovedException_thenThrowBookingAlreadyApprovedException() {
        ErrorHandler handler = new ErrorHandler();
        handler.handlerBookingAlreadyApprovedException(new BookingAlreadyApprovedException(""));
        assertNull(handler.handlerBookingAlreadyApprovedException(new BookingAlreadyApprovedException("")).getError());
    }

    @Test
    void handlerBookingAvailableException_whenThrowBookingAvailableException() {
        ErrorHandler handler = new ErrorHandler();
        assertNull(handler.handlerBookingAvailableException(new BookingAvailableException("")).getError());
    }

    @Test
    void handlerBookingTimeException_whenThrowBookingTimeException() {
        ErrorHandler handler = new ErrorHandler();
        assertNull(handler.handlerBookingTimeException(new BookingTimeException("")).getError());
    }

    @Test
    void handlerItemAvailableException_thenThrowItemAvailableException() {
        ErrorHandler handler = new ErrorHandler();
        assertNull(handler.handlerItemAvailableException(new ItemAvailableException("")).getError());
    }

    @Test
    void handlerRequestValidationException_thenThrowRequestValidationException() {
        ErrorHandler handler = new ErrorHandler();
        assertNull(handler.handlerRequestValidationException(new RequestValidationException("")).getError());
    }

    @Test
    void handlerEntityNotFoundException_thenThrowEntityNotFoundException() {
        ErrorHandler handler = new ErrorHandler();
        assertNull(handler.handlerEntityNotFoundException(new EntityNotFoundException("")).getError());
    }

    @Test
    void handlerUnsupportedStatusException_whenExpectedUnsupportedStatus() {
        ErrorHandler handler = new ErrorHandler();
        assertEquals("Unknown state: UNSUPPORTED_STATUS", handler.handlerUnsupportedStatusException(new UnsupportedItemStatusException("")).getError());
    }

}