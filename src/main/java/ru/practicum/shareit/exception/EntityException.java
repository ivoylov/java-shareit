package ru.practicum.shareit.exception;

public class EntityException extends RuntimeException {

    private Object o;
    private String message;

    public EntityException(Object o) {
        this.o = o;
    }

    public EntityException(Object o, String message) {
        this.o = o;
        this.message = message;
    }

    public Object getEntity() {
        return o;
    }

    public String getMessage() {
        return message;
    }

}
