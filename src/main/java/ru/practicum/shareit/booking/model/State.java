package ru.practicum.shareit.booking.model;

public enum State {

    ALL(0),
    CURRENT(1),
    PAST(2),
    FUTURE(3),
    WAITING(4),
    REJECTED(5),
    CANCELED(6);

    private int id;

    State(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
