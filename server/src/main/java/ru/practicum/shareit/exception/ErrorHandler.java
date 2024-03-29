package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.exception.booking.BookingAlreadyApprovedException;
import ru.practicum.shareit.exception.booking.BookingAvailableException;
import ru.practicum.shareit.exception.booking.BookingTimeException;
import ru.practicum.shareit.exception.entity.EntityNotFoundException;
import ru.practicum.shareit.exception.item.ItemAvailableException;
import ru.practicum.shareit.exception.item.UnsupportedItemStatusException;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerUserAlreadyExistException(final UserAlreadyExistException e) {
        log.info("Пользователь {} уже существует", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerEntityNotFoundException(final EntityNotFoundException e) {
        log.info("Объект {} не найден", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerItemAvailableException(final ItemAvailableException e) {
        log.info("Объект {} недоступен", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerBookingTimeException(final BookingTimeException e) {
        log.info("Некорректное время бронирования {}", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerBookingAvailableException(final BookingAvailableException e) {
        log.info("Недоступно на указанные даты бронирование {}", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerRequestValidationException(final RequestValidationException e) {
        log.info("Некорректный запрос {}", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerUnsupportedStatusException(final UnsupportedItemStatusException e) {
        log.info("{}", e.getEntity());
        return new ErrorResponse("Unknown state: UNSUPPORTED_STATUS");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerBookingAlreadyApprovedException(final BookingAlreadyApprovedException e) {
        log.info("Некорректный статус {}", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

}
