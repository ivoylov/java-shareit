package ru.practicum.shareit.exception.booking;

import ru.practicum.shareit.exception.entity.EntityException;

public class BookingAvailableException extends EntityException {

    public BookingAvailableException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}