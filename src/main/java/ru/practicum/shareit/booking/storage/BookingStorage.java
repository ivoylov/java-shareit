package ru.practicum.shareit.booking.storage;

import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;

import java.util.List;

public interface BookingStorage extends CrudOperations<Booking> {

    List<Booking> getAllBookingsForBooker(Long userId);

    List<Booking> getAllCurrentBookingsForBooker(Long userId);

    List<Booking> getAllPastBookingsForBooker(Long userId);

    List<Booking> getAllFutureBookingsForBooker(Long userId);

    List<Booking> getAllWaitingBookingsForBooker(Long userId);

    List<Booking> getAllRejectedBookingsForBooker(Long userId);

    List<Booking> getAllBookingsForOwner(Long userId);

    List<Booking> getAllCurrentBookingsForOwner(Long userId);

    List<Booking> getAllPastBookingsForOwner(Long userId);

    List<Booking> getAllFutureBookingsForOwner(Long userId);

    List<Booking> getAllWaitingBookingsForOwner(Long userId);

    List<Booking> getAllRejectedBookingsForOwner(Long userId);

    List<Booking> getAllFutureBookingsForItem(Long itemId);

    Booking getLastBookingForItem(Long itemId);

    Booking getNextBookingForItem(Long itemId);

}
