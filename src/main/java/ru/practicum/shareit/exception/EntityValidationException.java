package ru.practicum.shareit.exception;

public class EntityValidationException extends EntityException {

    public EntityValidationException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
