package ru.practicum.shareit.booking.storage;

import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;

import java.util.List;

public abstract class BookingStorage implements CrudOperations<Booking> {

    public abstract void updateBooking(Long bookingId, Integer status);
    public abstract List<Booking> getAllForBooker(Long bookersId, String state);
    public abstract List<Booking> getAllForOwner(Long ownerId, String state);
    public abstract Booking getForBooker(Long bookingId, Long bookerId);
    public abstract Booking getByItemId(Long itemId);

}
