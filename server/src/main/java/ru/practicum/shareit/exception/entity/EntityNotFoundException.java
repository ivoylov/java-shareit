package ru.practicum.shareit.exception.entity;

public class EntityNotFoundException extends EntityException {

    public EntityNotFoundException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
