package ru.practicum.shareit.booking.storage;

import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;

import java.util.List;

public abstract class BookingStorage implements CrudOperations<Booking> {

    public abstract List<Booking> getAllBookingsForUser(Long userId);
    public abstract List<Booking> getAllCurrentBookingsForUser(Long userId);

    public abstract List<Booking> getAllPastBookingsForUser(Long userId);

    public abstract List<Booking> getAllFutureBookingsForUser(Long userId);

    public abstract List<Booking> getAllWaitingBookingsForUser(Long userId);

    public abstract List<Booking> getAllRejectedBookingsForUser(Long userId);

    public abstract List<Booking> getAllBookingsForOwner(Long userId);

    public abstract List<Booking> getAllCurrentBookingsForOwner(Long userId);

    public abstract List<Booking> getAllPastBookingsForOwner(Long userId);

    public abstract List<Booking> getAllFutureBookingsForOwner(Long userId);

    public abstract List<Booking> getAllWaitingBookingsForOwner(Long userId);

    public abstract List<Booking> getAllRejectedBookingsForOwner(Long userId);

}
