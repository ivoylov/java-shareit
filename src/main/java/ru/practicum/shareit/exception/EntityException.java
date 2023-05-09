package ru.practicum.shareit.exception;

public class EntityException extends RuntimeException {

    Object o;

    public EntityException(Object o) {
        this.o = o;
    }

    public Object getEntity() {
        return o;
    }

}
