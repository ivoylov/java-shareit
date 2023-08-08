package ru.practicum.shareit.exception.entity;

public class EntityException extends RuntimeException {

    private Object o;

    public EntityException(Object o) {
        this.o = o;
    }

    public Object getEntity() {
        return o;
    }

}
