package ru.practicum.shareit.booking.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.storage.InDbBookingStorage;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.EntityValidationException;
import ru.practicum.shareit.exception.ItemAvailableException;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService implements CrudOperations<Booking> {

    private final InDbBookingStorage bookingStorage;
    private final ItemService itemService;
    private final UserService userService;

    @Override
    public Booking create(Booking booking) {
        if (!itemService.get(booking.getItem().getId()).getAvailable()) throw new ItemAvailableException(booking);
        if (!userService.isExist(booking.getBookerId())) throw new EntityNotFoundException(userService.get(booking.getBookerId()));
        if (!booking.isBookingTimeValid()) throw new EntityValidationException(booking);
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
