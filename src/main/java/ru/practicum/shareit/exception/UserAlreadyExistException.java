package ru.practicum.shareit.exception;

public class UserAlreadyExistException extends EntityException {

    public UserAlreadyExistException(Object o) {
        super(o);
    }

    public Object getEntity() {
        return super.getEntity();
    }

}
