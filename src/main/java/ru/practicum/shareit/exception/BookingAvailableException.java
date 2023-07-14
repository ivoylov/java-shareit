package ru.practicum.shareit.exception;

public class BookingAvailableException extends EntityException {

    public BookingAvailableException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}