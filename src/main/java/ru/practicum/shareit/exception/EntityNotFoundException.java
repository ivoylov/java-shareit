package ru.practicum.shareit.exception;

public class EntityNotFoundException extends RuntimeException {

    Object o;
    public EntityNotFoundException(Object o) {
        this.o = o;
    }

    public Object getEntity() {
        return o;
    }

}
