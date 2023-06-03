package ru.practicum.shareit.exception;

public class ItemAvailableException extends EntityException {

    public ItemAvailableException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
