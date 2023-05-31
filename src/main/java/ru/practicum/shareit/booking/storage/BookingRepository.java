package ru.practicum.shareit.booking.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE bookings " +
            "SET status = :bookingStatus " +
            "WHERE id = :bookingId",
            nativeQuery = true)
    void update(Integer bookingStatus, Long bookingId);

    List<Booking> findBookingsByBookerIdOrderByIdDesc(Long userId);
    List<Booking> findBookingsByOwnerIdOrderByIdDesc(Long userId);
    List<Booking> findBookingsByBookerIdAndStatus(Long userId, Status status);
    List<Booking> findBookingsByBookerIdAndStartAfterOrderByStartDesc(Long userId, LocalDateTime now);
    List<Booking> findBookingsByBookerIdAndStartBeforeAndEndAfter(Long bookerId, LocalDateTime start, LocalDateTime end);
    List<Booking> findBookingsByBookerIdAndEndBeforeOrderByEndDesc(Long userId, LocalDateTime now);
    List<Booking> findBookingsByOwnerIdAndStatusOrderByIdDesc(Long userId, Status status);
    List<Booking> findBookingsByOwnerIdAndStartAfterOrderByIdDesc(Long userId, LocalDateTime now);
    List<Booking> findBookingsByOwnerIdAndStartBeforeAndEndAfterOrderByEndDesc(Long bookerId, LocalDateTime start, LocalDateTime end);
    List<Booking> findBookingsByOwnerIdAndEndBeforeOrderByEndDesc(Long userId, LocalDateTime now);
    List<Booking> findBookingsByItemIdAndStartAfter(Long itemId, LocalDateTime now);
    List<Booking> findBookingsByItemIdAndStartBeforeAndStatusOrderByEndDesc(Long itemId, LocalDateTime time, Status status);
    List<Booking> findBookingsByItemIdAndStartAfterAndStatusOrderByStartAsc(Long itemId, LocalDateTime now, Status status);

    List<Booking> findBookingsByItemIdAndEndBeforeOrderByEndAsc(Long itemId, LocalDateTime now);
}
