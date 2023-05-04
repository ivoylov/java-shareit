package ru.practicum.shareit.exception.userException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserValidationException extends RuntimeException {
    public UserValidationException(String message) {
        super(message);
        log.info(message);
    }
}
