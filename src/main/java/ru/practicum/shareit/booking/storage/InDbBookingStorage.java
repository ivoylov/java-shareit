package ru.practicum.shareit.booking.storage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
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
        bookingRepository.update(booking.getStatus().getId(), booking.getBookerId());
        return get(booking.getId());
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
    public List<Booking> getAllBookingsForUser(Long userId) {
        return bookingRepository.findBookingsByBookerIdOrderByIdDesc(userId);
    }

    @Override
    public List<Booking> getAllCurrentBookingsForUser(Long userId) {
        return bookingRepository.findBookingsByBookerIdAndStatus(userId,Status.APPROVED);
    }

    @Override
    public List<Booking> getAllPastBookingsForUser(Long userId) {
        return bookingRepository.findBookingsByBookerIdAndEndBefore(userId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllFutureBookingsForUser(Long userId) {
        return bookingRepository.findBookingsByBookerIdAndStartAfterOrderByStart(userId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllWaitingBookingsForUser(Long userId) {
        return bookingRepository.findBookingsByBookerIdAndStatus(userId,Status.WAITING);
    }

    @Override
    public List<Booking> getAllRejectedBookingsForUser(Long userId) {
        return bookingRepository.findBookingsByBookerIdAndStatus(userId,Status.REJECTED);
    }

    @Override
    public List<Booking> getAllBookingsForOwner(Long userId) {
        return bookingRepository.findBookingsByOwnerIdOrderByIdDesc(userId);
    }

    @Override
    public List<Booking> getAllCurrentBookingsForOwner(Long userId) {
        return bookingRepository.findBookingsByOwnerIdAndStatus(userId,Status.APPROVED);
    }

    @Override
    public List<Booking> getAllPastBookingsForOwner(Long userId) {
        return bookingRepository.findBookingsByOwnerIdAndEndBefore(userId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllFutureBookingsForOwner(Long userId) {
        return bookingRepository.findBookingsByOwnerIdAndStartAfter(userId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllWaitingBookingsForOwner(Long userId) {
        return bookingRepository.findBookingsByOwnerIdAndStatus(userId,Status.WAITING);
    }

    @Override
    public List<Booking> getAllRejectedBookingsForOwner(Long userId) {
        return bookingRepository.findBookingsByOwnerIdAndStatus(userId,Status.REJECTED);
    }
}
