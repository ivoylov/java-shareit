package ru.practicum.shareit.exception;

import ru.practicum.shareit.exception.entity.EntityException;

public class UserAlreadyExistException extends EntityException {

    public UserAlreadyExistException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
