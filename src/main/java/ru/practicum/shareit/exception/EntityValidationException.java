package ru.practicum.shareit.exception;

public class EntityValidationException extends RuntimeException {
    Object o;
    public EntityValidationException(Object o) {
        this.o = o;
    }

    public Object getEntity() {
        return o;
    }

}
