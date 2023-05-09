package ru.practicum.shareit.exception;

public class EntityNotFoundException extends EntityException {

    Object o;

    public EntityNotFoundException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
