package ru.practicum.shareit.booking.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;

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
    void update(Long bookingId, Integer bookingStatus);

    List<Booking> findBookingsByBookerIdOrderByIdDesc(Long bookerId);
    List<Booking> findBookingsByStatus(String state);

}
