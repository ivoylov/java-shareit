package ru.practicum.shareit.exception;

public class ItemNotAvailableException extends EntityException {

    public ItemNotAvailableException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
