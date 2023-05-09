package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        log.info("Пользователь уже существует", e);
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
    public ErrorResponse handlerEntityValidationException(final EntityValidationException e) {
        log.info("Объект {} не прошёл валидацию", e.getEntity());
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
