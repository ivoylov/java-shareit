package ru.practicum.shareit.exception;

public class EntityValidationException extends EntityException {

    Object o;

    public EntityValidationException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
