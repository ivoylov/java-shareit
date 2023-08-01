package ru.practicum.shareit.exception;

public class UnsupportedStatusException extends EntityException {

    public UnsupportedStatusException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}