package ru.practicum.shareit.exception;

public class EntityValidationException extends EntityException {

    public EntityValidationException(Object o) {
        super(o);
    }

    public EntityValidationException(Object o, String message) {
        super(o,message);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
