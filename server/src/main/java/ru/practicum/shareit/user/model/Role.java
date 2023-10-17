package ru.practicum.shareit.user.model;

public enum Role {
    BOOKER(0),
    OWNER(1);
    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
