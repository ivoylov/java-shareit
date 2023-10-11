package ru.practicum.shareit.exception.item;

import ru.practicum.shareit.exception.entity.EntityException;

public class ItemAvailableException extends EntityException {

    public ItemAvailableException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
