package ru.practicum.shareit.exception;

public class BookingAlreadyApprovedException extends EntityException {

    public BookingAlreadyApprovedException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
