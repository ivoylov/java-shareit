package ru.practicum.shareit.user.model;

public enum Role {
    BOOKER(0, "booker_id"),
    OWNER(1, "owner_id");
    private final int id;
    private final String columnNameInDb;
    Role(int id, String columnNameInDb) {
        this.id = id;
        this.columnNameInDb = columnNameInDb;
    }
    public int getId() {
        return id;
    }
    public String getColumnNameInDb() {
        return columnNameInDb;
    }
}
