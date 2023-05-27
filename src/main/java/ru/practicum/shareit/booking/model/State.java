package ru.practicum.shareit.booking.model;

public enum State {

    UNSUPPORTED_STATUS(0),
    ALL(1),
    CURRENT(2),
    PAST(3),
    FUTURE(4);

    private int id;

    State(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
