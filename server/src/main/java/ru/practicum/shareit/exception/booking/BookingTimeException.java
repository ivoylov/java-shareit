package ru.practicum.shareit.exception.booking;

import ru.practicum.shareit.exception.entity.EntityException;

public class BookingTimeException extends EntityException {

    public BookingTimeException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}