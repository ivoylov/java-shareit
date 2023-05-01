package ru.practicum.shareit.user.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.UserAlreadyExistException;
import ru.practicum.shareit.storage.UserStorage;
import ru.practicum.shareit.user.model.User;
import java.util.HashMap;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {

    private final HashMap<Long, User> users = new HashMap<>();
    private Long counter = 0L;

    public User create(User user) {
        if (users.size() != 0) {
            for (User checkedUser : users.values()) {
                if (checkedUser.getEmail().equals(user.getEmail())) {
                    log.info(user + " уже существует");
                    throw new UserAlreadyExistException("Пользователь уже существует");
                }
            }
        }
        user.setId(++counter);
        users.put(user.getId(), user);
        log.info(user + " создан");
        return user;
    }

}
