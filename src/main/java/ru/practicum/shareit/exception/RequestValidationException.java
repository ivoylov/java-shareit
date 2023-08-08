package ru.practicum.shareit.exception;

import ru.practicum.shareit.exception.entity.EntityException;

public class RequestValidationException extends EntityException {

    public RequestValidationException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
