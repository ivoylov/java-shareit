package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.booking.model.Booking;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE bookings SET status = :statusId WHERE booking_id = :bookingId",
            nativeQuery = true)
    void update(Integer statusId, Long bookingId);

    List<Booking> findAllByBookerId(Long bookerId);

}
