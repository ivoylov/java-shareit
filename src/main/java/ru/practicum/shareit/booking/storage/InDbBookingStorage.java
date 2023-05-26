package ru.practicum.shareit.booking.storage;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class InDbBookingStorage extends BookingStorage {

    private final BookingRepository bookingRepository;

    @Override
    public Booking create(Booking booking) {
        log.info(InDbBookingStorage.class + " create " + booking);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        log.info(InDbBookingStorage.class + " update " + booking);
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
        log.info(InDbBookingStorage.class + " get bookingId" + id);
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
        log.info(InDbBookingStorage.class + " get all booking for bookerId=" + userId);
        return bookingRepository.findBookingsByBookerIdOrderByIdDesc(userId);
    }

    @Override
    public List<Booking> getAllCurrentBookingsForUser(Long userId) {
        log.info(InDbBookingStorage.class + " get all booking for bookerId=" + userId);
        return bookingRepository.findBookingsByBookerIdAndStatus(userId,Status.APPROVED);
    }

    @Override
    public List<Booking> getAllPastBookingsForUser(Long userId) {
        log.info(InDbBookingStorage.class + " get all booking for bookerId=" + userId);
        return bookingRepository.findBookingsByBookerIdAndEndBefore(userId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllFutureBookingsForUser(Long userId) {
        log.info(InDbBookingStorage.class + " get all booking for bookerId=" + userId);
        return bookingRepository.findBookingsByBookerIdAndStartAfterOrderByStart(userId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllWaitingBookingsForUser(Long userId) {
        log.info(InDbBookingStorage.class + " get all booking for bookerId=" + userId);
        return bookingRepository.findBookingsByBookerIdAndStatus(userId,Status.WAITING);
    }

    @Override
    public List<Booking> getAllRejectedBookingsForUser(Long userId) {
        log.info(InDbBookingStorage.class + " get all booking for bookerId=" + userId);
        return bookingRepository.findBookingsByBookerIdAndStatus(userId,Status.REJECTED);
    }

    @Override
    public List<Booking> getAllBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all booking for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdOrderByIdDesc(ownerId);
    }

    @Override
    public List<Booking> getAllCurrentBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all booking for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdAndStatus(ownerId,Status.APPROVED);
    }

    @Override
    public List<Booking> getAllPastBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all booking for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdAndEndBefore(ownerId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllFutureBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all booking for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdAndStartAfter(ownerId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllWaitingBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all booking for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdAndStatus(ownerId,Status.WAITING);
    }

    @Override
    public List<Booking> getAllRejectedBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all booking for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdAndStatus(ownerId,Status.REJECTED);
    }
}
