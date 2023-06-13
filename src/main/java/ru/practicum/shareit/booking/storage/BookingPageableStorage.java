package ru.practicum.shareit.booking.storage;

import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;

import java.util.List;

public interface BookingPageableStorage extends CrudOperations<Booking> {

    List<Booking> getAllBookingsForBooker(Long userId, int page, int size);

    List<Booking> getAllCurrentBookingsForBooker(Long userId, int page, int size);

    List<Booking> getAllPastBookingsForBooker(Long userId, int page, int size);

    List<Booking> getAllFutureBookingsForBooker(Long userId, int page, int size);

    List<Booking> getAllWaitingBookingsForBooker(Long userId, int page, int size);

    List<Booking> getAllRejectedBookingsForBooker(Long userId, int page, int size);

    List<Booking> getAllBookingsForOwner(Long userId, int page, int size);

    List<Booking> getAllCurrentBookingsForOwner(Long userId, int page, int size);

    List<Booking> getAllPastBookingsForOwner(Long userId, int page, int size);

    List<Booking> getAllFutureBookingsForOwner(Long userId, int page, int size);

    List<Booking> getAllWaitingBookingsForOwner(Long userId, int page, int size);

    List<Booking> getAllRejectedBookingsForOwner(Long userId, int page, int size);

    List<Booking> getAllFutureBookingsForItem(Long itemId, int page, int size);

    Booking getLastBookingForItem(Long itemId, int page, int size);

    Booking getNextBookingForItem(Long itemId, int page, int size);

    Booking getLastBookingForItemByOwner(Long id, Long userId, int page, int size);

    Booking getNextBookingForItemByOwner(Long id, Long userId, int page, int size);

}
