package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    // 409
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerUserAlreadyExistException(final UserAlreadyExistException e) {
        log.info("Пользователь {} уже существует", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

    //404
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerEntityNotFoundException(final EntityNotFoundException e) {
        log.info("Объект {} не найден", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

    //400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerItemAvailableException(final ItemAvailableException e) {
        log.info("Объект {} недоступен", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

    //400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerBookingTimeException(final BookingTimeException e) {
        log.info("Некорректное время бронирования {}", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

    // 409
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerBookingAvailableException(final BookingAvailableException e) {
        log.info("Недоступно на указанные даты бронирование {}", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

}
