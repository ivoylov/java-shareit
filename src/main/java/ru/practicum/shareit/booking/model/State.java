package ru.practicum.shareit.booking.model;

public enum State {

    UNSUPPORTED_STATUS(0),
    WAITING(1),
    APPROVED(2),
    REJECTED(3),
    CURRENT(4),
    PAST(5),
    FUTURE(6),
    ALL(7);

    private int id;

    State(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
