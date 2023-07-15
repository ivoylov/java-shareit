package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE bookings SET status = :status WHERE booking_id = :bookingId",
            nativeQuery = true)
    void update(Integer status, Long bookingId);

    List<Booking> findAllByBookerId(Long bookerId);

}
