package ru.practicum.shareit.exception.item;

import ru.practicum.shareit.exception.entity.EntityException;

public class UnsupportedItemStatusException extends EntityException {

    public UnsupportedItemStatusException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}