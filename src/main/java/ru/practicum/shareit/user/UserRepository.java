package ru.practicum.shareit.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.user.model.User;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(value =
            "UPDATE shareit_user " +
                    "SET name = :user.userName, email = :userEmail " +
                    "WHERE shareit_user_id = :userId",
            nativeQuery = true)
    void update(String userName, String userEmail, Long userId);
}
