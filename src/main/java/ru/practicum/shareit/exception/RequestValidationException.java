package ru.practicum.shareit.exception;

public class RequestValidationException extends EntityException {

    public RequestValidationException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
