package ru.practicum.shareit.exception;

public class BookingTimeException extends EntityException {

    public BookingTimeException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}