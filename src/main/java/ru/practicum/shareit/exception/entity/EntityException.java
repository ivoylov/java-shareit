package ru.practicum.shareit.exception.entity;

public class EntityException extends RuntimeException {

    private Object o;
    private String message;

    public EntityException(Object o) {
        this.o = o;
    }

    public Object getEntity() {
        return o;
    }

    public String getMessage() {
        return message;
    }

}
