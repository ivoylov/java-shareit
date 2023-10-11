package ru.practicum.shareit.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(value =
            "UPDATE shareit_user " +
                    "SET user_name = :userName, user_email = :userEmail " +
                    "WHERE user_id = :userId",
            nativeQuery = true)
    void update(String userName, String userEmail, Long userId);
}
