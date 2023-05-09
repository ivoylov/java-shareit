package ru.practicum.shareit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.exception.itemException.ItemNotFoundException;
import ru.practicum.shareit.exception.itemException.ItemValidationException;
import ru.practicum.shareit.exception.requestException.RequestValidationException;
import ru.practicum.shareit.exception.userException.UserAlreadyExistException;
import ru.practicum.shareit.exception.userException.UserNotFoundException;
import ru.practicum.shareit.exception.userException.UserValidationException;

@RestControllerAdvice
public class ErrorHandler {

    // 409
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerUserAlreadyExistException(final UserAlreadyExistException e) {
        return new ErrorResponse(e.getMessage());
    }

    // 404
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerUserNotFoundException(final UserNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    // 400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerUserValidationException(final UserValidationException e) {
        return new ErrorResponse(e.getMessage());
    }

    // 400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerItemValidationException(final ItemValidationException e) {
        return new ErrorResponse(e.getMessage());
    }

    // 404
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerItemNotFoundException(final ItemNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    // 400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerRequestValidationException(final RequestValidationException e) {
        return new ErrorResponse(e.getMessage());
    }

    //400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerArgumentNotValidException(final MethodArgumentNotValidException e) {
        return new ErrorResponse(e.getMessage());
    }

    //500
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerUnknownErrorException(final Throwable e) {
        return new ErrorResponse(e.getMessage());
    }

}
