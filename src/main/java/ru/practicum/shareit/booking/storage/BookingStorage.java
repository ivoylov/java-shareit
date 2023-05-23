package ru.practicum.shareit.booking.storage;

import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;

import java.util.List;

public abstract class BookingStorage implements CrudOperations<Booking> {

    public abstract void updateBooking(Long bookingId, Integer status);
    public abstract List<Booking> getAllForBookers(Long bookersId, String state);

}
