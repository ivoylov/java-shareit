package ru.practicum.shareit.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.user.model.User;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface RequestRepository extends PagingAndSortingRepository<Request, Long> {

    @Query(value = "SELECT * FROM requests WHERE user_id = :userId", nativeQuery = true)
    List<Request> getRequestByUserId(Long userId);

}
