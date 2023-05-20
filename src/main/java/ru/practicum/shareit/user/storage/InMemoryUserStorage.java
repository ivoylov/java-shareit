package ru.practicum.shareit.user.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.*;

@Slf4j
@Component
public class InMemoryUserStorage extends UserStorage {

    private final Map<Long, User> users = new HashMap<>();
    private Long counter = 0L;

    public User create(User user) {
        user.setId(++counter);
        users.put(user.getId(), user);
        log.info("В памяти создан {}", user);
        return user;
    }

    @Override
    public User update(User user) {
        log.info("В памяти обновлён {}", user);
        return users.put(user.getId(),user);
    }

    @Override
    public Boolean isExist(Long id) {
        return users.containsKey(id);
    }

    @Override
    public Boolean isExist(User user) {
        for (User checkedUser : users.values()) {
            if (UserService.isSameUsers(checkedUser, user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User get(Long id) {
        log.info("Из памяти отдан пользователь с id {}", id);
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("Из памяти отдан список всех пользователей");
        return new ArrayList<>(users.values());
    }

    @Override
    public User delete(Long id) {
        log.info("Из памяти пользователь с id {} удалён", id);
        return users.remove(id);
    }

}
