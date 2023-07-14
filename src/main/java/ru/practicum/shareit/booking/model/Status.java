package ru.practicum.shareit.booking.model;

public enum Status {
    CANCELED(0),
    WAITING(1),
    APPROVED(2),
    REJECTED(3);
    private final int id;
    Status(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
}
