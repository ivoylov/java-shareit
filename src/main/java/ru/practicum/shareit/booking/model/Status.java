package ru.practicum.shareit.booking.model;

public enum Status {
    WAITING(1),
    APPROVED(2),
    REJECTED(3),
    CANCELED(0);
    private final int id;
    Status(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
}
