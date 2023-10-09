package ru.practicum.shareit.booking.model;

public enum State {
    ALL(0),
    CURRENT(1),
    PAST(2),
    FUTURE(3),
    WAITING(4),
    REJECTED(5),
    UNSUPPORTED_STATUS(6);
    final int id;

    State(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
