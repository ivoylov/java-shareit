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
    List<Booking> findBookingsByBookerIdAndEndBefore(Long userId, LocalDateTime now);
    List<Booking> findBookingsByOwnerIdAndStatus(Long userId, Status status);
    List<Booking> findBookingsByOwnerIdAndStartAfter(Long userId, LocalDateTime now);
    List<Booking> findBookingsByOwnerIdAndEndBefore(Long userId, LocalDateTime now);
    List<Booking> findBookingsByItemIdAndStartAfter(Long itemId, LocalDateTime now);
    Booking findBookingByItemIdAndStartAfterOrderByStartAsc(Long itemId, LocalDateTime now);
    Booking findBookingByItemIdAndEndBeforeOrderByEndAsc(Long itemId, LocalDateTime now);
}
