package ru.practicum.shareit.booking.storage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class InDbBookingStorage extends BookingStorage {

    private final BookingRepository bookingRepository;

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
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
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> getAll() {
        return null;
    }

    @Override
    public Booking delete(Long id) {
        return null;
    }

    @Override
    public void updateBooking(Long bookingId, Integer status) {
        bookingRepository.update(bookingId, status);
    }

    @Override
    public List<Booking> getAllForBooker(Long bookersId, String state) {
        if (state.equals("ALL")) {
            return bookingRepository.findBookingsByBookerIdOrderByIdDesc(bookersId);
        } else {
            Status status = Status.valueOf(state);
            if (status == Status.FUTURE) {
                return bookingRepository.findBookingsByStartAfter(LocalDateTime.now());
            }
            else {
                return bookingRepository.findBookingsByBookerIdAndStatusOrderByBookerIdDesc(bookersId, status);
            }
        }
    }

    @Override
    public Booking getForBooker(Long bookingId, Long bookerId) {
        return bookingRepository.findBookingByIdAndAndBookerId(bookingId,bookerId);
    }

    @Override
    public List<Booking> getAllForOwner(Long ownerId, String state) {
        if (state.equals("ALL")) {
            return bookingRepository.findBookingsByOwnerIdOrderByIdDesc(ownerId);
        } else {
            Status status = Status.valueOf(state);
            if (status == Status.FUTURE) {
                return bookingRepository.findBookingsByStartAfter(LocalDateTime.now());
            }
            else {
                return bookingRepository.findBookingsByOwnerIdAndStatusOrderByOwnerIdDesc(ownerId, status);
            }
        }
    }

    @Override
    public Booking getByItemId(Long itemId) {
        return bookingRepository.findBookingByItemId(itemId);
    }
}
