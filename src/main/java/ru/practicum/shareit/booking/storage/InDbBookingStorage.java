package ru.practicum.shareit.booking.storage;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class InDbBookingStorage implements BookingStorage {

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
        log.info(InDbBookingStorage.class + " get bookingId=" + id);
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
    public List<Booking> getAllBookingsForBooker(Long bookerId) {
        log.info(InDbBookingStorage.class + " get all bookings for bookerId=" + bookerId);
        return bookingRepository.findBookingsByBookerIdOrderByIdDesc(bookerId);
    }

    @Override
    public List<Booking> getAllCurrentBookingsForBooker(Long bookerId) {
        log.info(InDbBookingStorage.class + " get all current bookings for bookerId=" + bookerId);
        return bookingRepository.findBookingsByBookerIdAndStatus(bookerId,Status.APPROVED);
    }

    @Override
    public List<Booking> getAllPastBookingsForBooker(Long bookerId) {
        log.info(InDbBookingStorage.class + " get all past bookings for bookerId=" + bookerId);
        return bookingRepository.findBookingsByBookerIdAndEndBefore(bookerId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllFutureBookingsForBooker(Long bookerId) {
        log.info(InDbBookingStorage.class + " get all future bookings for bookerId=" + bookerId);
        return bookingRepository.findBookingsByBookerIdAndStartAfterOrderByStartDesc(bookerId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllWaitingBookingsForBooker(Long bookerId) {
        log.info(InDbBookingStorage.class + " get all waiting bookings for bookerId=" + bookerId);
        return bookingRepository.findBookingsByBookerIdAndStatus(bookerId, Status.WAITING);
    }

    @Override
    public List<Booking> getAllRejectedBookingsForBooker(Long bookerId) {
        log.info(InDbBookingStorage.class + " get all rejected booking for bookerId=" + bookerId);
        return bookingRepository.findBookingsByBookerIdAndStatus(bookerId, Status.REJECTED);
    }

    @Override
    public List<Booking> getAllBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all bookings for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdOrderByIdDesc(ownerId);
    }

    @Override
    public List<Booking> getAllCurrentBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all current bookings for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdAndStatusOrderByIdDesc(ownerId,Status.APPROVED);
    }

    @Override
    public List<Booking> getAllPastBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all past bookings for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdAndEndBefore(ownerId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllFutureBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all future bookings for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdAndStartAfterOrderByIdDesc(ownerId, LocalDateTime.now());
    }

    @Override
    public List<Booking> getAllWaitingBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all waiting bookings for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdAndStatusOrderByIdDesc(ownerId,Status.WAITING);
    }

    @Override
    public List<Booking> getAllRejectedBookingsForOwner(Long ownerId) {
        log.info(InDbBookingStorage.class + " get all rejected bookings for ownerId=" + ownerId);
        return bookingRepository.findBookingsByOwnerIdAndStatusOrderByIdDesc(ownerId,Status.REJECTED);
    }

    @Override
    public List<Booking> getAllFutureBookingsForItem(Long itemId) {
        log.info(InDbBookingStorage.class + " get all future bookings for itemId=" + itemId);
        return bookingRepository.findBookingsByItemIdAndStartAfter(itemId, LocalDateTime.now());
    }

    @Override
    public Booking getLastBookingForItem(Long itemId) {
        log.info(InDbBookingStorage.class + " get last booking for item itemId=" + itemId);
        ArrayList<Booking> bookings = new ArrayList<>(bookingRepository.findBookingsByItemIdAndEndBeforeOrderByEndAsc(itemId, LocalDateTime.now()));
        if (bookings.isEmpty()) return null;
        return bookings.get(0);
    }

    @Override
    public Booking getNextBookingForItem(Long itemId) {
        log.info(InDbBookingStorage.class + " get next booking for item itemId=" + itemId);
        ArrayList<Booking> bookings = new ArrayList<>(bookingRepository.findBookingsByItemIdAndStartAfterAndStatusOrderByStartAsc(itemId, LocalDateTime.now(), Status.APPROVED));
        if (bookings.isEmpty()) return null;
        return bookings.get(0);
    }

    @Override
    public Booking getLastBookingForItemByOwner(Long itemId, Long userId) {
        log.info(InDbBookingStorage.class + " get last booking for item itemId=" + itemId + " userId=" + userId);
        ArrayList<Booking> bookings = new ArrayList<>(bookingRepository.findBookingsByItemIdAndOwnerIdAndEndBeforeAndStatusOrderByIdDesc(itemId, userId, LocalDateTime.now(), Status.APPROVED));
        if (bookings.isEmpty()) return null;
        return bookings.get(0);
    }

    @Override
    public Booking getNextBookingForItemByOwner(Long itemId, Long userId) {
        log.info(InDbBookingStorage.class + " get next booking for item itemId=" + itemId + " userId=" + userId);
        ArrayList<Booking> bookings = new ArrayList<>(bookingRepository.findBookingsByItemIdAndOwnerIdAndStartAfterAndStatusOrderByIdDesc(itemId, userId, LocalDateTime.now(), Status.APPROVED));
        if (bookings.isEmpty()) return null;
        return bookings.get(0);
    }
}