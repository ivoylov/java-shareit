package ru.practicum.shareit.booking.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.storage.InDbBookingStorage;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService implements CrudOperations<Booking> {

    private final InDbBookingStorage bookingStorage;

    @Override
    public Booking create(Booking booking) {
        return bookingStorage.create(booking);
    }

    @Override
    public Booking update(Booking booking) {
        return null;
    }

    @Override
    public Boolean isExist(Long id) {
        return null;
    }

    @Override
    public Boolean isExist(Booking booking) {
        return null;
    }

    @Override
    public Booking get(Long id) {
        return null;
    }

    @Override
    public List<Booking> getAll() {
        return null;
    }

    @Override
    public Booking delete(Long id) {
        return null;
    }
}
