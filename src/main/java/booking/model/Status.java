package booking.model;

public enum Status {
    WAITING(1),
    APPROVED(2),
    REJECTED(3),
    CANCELED(0);
    private int id;
    Status(int id) {
        this.id = id;
    }
}
