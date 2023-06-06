package ru.practicum.shareit.booking.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingPageableRepository extends JpaRepository<Booking, Long> {

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE bookings " +
            "SET status = :bookingStatus " +
            "WHERE id = :bookingId",
            nativeQuery = true)
    void update(Integer bookingStatus, Long bookingId);

    List<Booking> findBookingsByBookerIdOrderByIdDesc(Long userId, Pageable pageable);

    List<Booking> findBookingsByBookerId(Long userId, Pageable pageable);

    List<Booking> findBookingsByBookerId(Long userId);

    List<Booking> findBookingsByOwnerIdOrderByIdDesc(Long userId, Pageable pageable);

    List<Booking> findBookingsByBookerIdAndStatus(Long userId, Status status, Pageable pageable);

    List<Booking> findBookingsByBookerIdAndStartAfterOrderByStartDesc(Long userId, LocalDateTime now, Pageable pageable);

    List<Booking> findBookingsByBookerIdAndStartBeforeAndEndAfter(Long bookerId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    List<Booking> findBookingsByBookerIdAndEndBeforeOrderByEndDesc(Long userId, LocalDateTime now, Pageable pageable);

    List<Booking> findBookingsByOwnerIdAndStatusOrderByIdDesc(Long userId, Status status, Pageable pageable);

    List<Booking> findBookingsByOwnerIdAndStartAfterOrderByIdDesc(Long userId, LocalDateTime now, Pageable pageable);

    List<Booking> findBookingsByOwnerIdAndStartBeforeAndEndAfterOrderByEndDesc(Long bookerId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    List<Booking> findBookingsByOwnerIdAndEndBeforeOrderByEndDesc(Long userId, LocalDateTime now, Pageable pageable);

    List<Booking> findBookingsByItemIdAndStartAfter(Long itemId, LocalDateTime now, Pageable pageable);

    List<Booking> findBookingsByItemIdAndStartAfter(Long itemId, LocalDateTime now);

    List<Booking> findBookingsByItemIdAndStartBeforeAndStatusOrderByEndDesc(Long itemId, LocalDateTime time, Status status, Pageable pageable);

    List<Booking> findBookingsByItemIdAndStartAfterAndStatusOrderByStartAsc(Long itemId, LocalDateTime now, Status status, Pageable pageable);

    List<Booking> findBookingsByItemIdAndEndBeforeOrderByEndAsc(Long itemId, LocalDateTime now, Pageable pageable);

}
