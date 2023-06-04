package ru.practicum.shareit.request.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.request.model.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE requests " +
                    "SET description = :requestDescription " +
                    "WHERE id = :requestId",
            nativeQuery = true)
    void update(String requestDescription, Long requestId);

    List<Request> findRequestsByRequestorId(Long requestorId);
    
}
