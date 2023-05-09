package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.exception.requestException.RequestValidationException;
import ru.practicum.shareit.exception.userException.UserAlreadyExistException;
import ru.practicum.shareit.exception.userException.UserNotFoundException;
import ru.practicum.shareit.exception.userException.UserValidationException;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    // 409
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerUserAlreadyExistException(final UserAlreadyExistException e) {
        log.info("Пользователь уже существует", e);
        return new ErrorResponse(e.getMessage());
    }

    // 404
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerUserNotFoundException(final UserNotFoundException e) {
        log.info("Пользователь не найден", e);
        return new ErrorResponse(e.getMessage());
    }

    // 400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerUserValidationException(final UserValidationException e) {
        log.info("Ошибка валидации пользователя", e);
        return new ErrorResponse(e.getMessage());
    }

    // 400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerItemValidationException(final ItemValidationException e) {
        log.info("Ошибка валидации вещи", e);
        return new ErrorResponse(e.getMessage());
    }

    //404
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerEntityNotFoundException(final EntityNotFoundException e) {
        log.info("Объект {} не найден", e.getEntity());
        return new ErrorResponse(e.getMessage());
    }

    // 400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerRequestValidationException(final RequestValidationException e) {
        log.info("Ошибка валидации запроса", e);
        return new ErrorResponse(e.getMessage());
    }

    //400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.info("В запрос передан невалидный аргумент", e);
        return new ErrorResponse(e.getMessage());
    }

    //500
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerUnknownErrorException(final Throwable e) {
        log.info("Неизвестная ошибка", e);
        return new ErrorResponse(e.getMessage());
    }

}
