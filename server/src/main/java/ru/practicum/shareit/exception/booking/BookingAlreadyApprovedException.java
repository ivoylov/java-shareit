package ru.practicum.shareit.exception.booking;

import ru.practicum.shareit.exception.entity.EntityException;

public class BookingAlreadyApprovedException extends EntityException {

    public BookingAlreadyApprovedException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
