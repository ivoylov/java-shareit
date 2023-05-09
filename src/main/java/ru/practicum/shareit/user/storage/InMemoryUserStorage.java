package ru.practicum.shareit.user.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.UserAlreadyExistException;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Slf4j
@Component
public class InMemoryUserStorage extends UserStorage {

    private final HashMap<Long, User> users = new HashMap<>();
    private Long counter = 0L;

    public User create(User user) {
        if (isExist(user)) throw new UserAlreadyExistException();
        user.setId(++counter);
        users.put(user.getId(), user);
        log.info("создан {}", user);
        return user;
    }

    @Override
    public User update(User user) {
        if (!users.containsKey(user.getId())) throw new EntityNotFoundException(user);
        if (user.getEmail() != null) {
            if (isExist(user)) throw new UserAlreadyExistException();
        }
        User updatedUser = updateUser(user, users.get(user.getId()));
        log.info("обновлён {}", user);
        return users.put(updatedUser.getId(),updatedUser);
    }

    @Override
    public Boolean isExist(Long id) {
        return users.containsKey(id);
    }

    @Override
    public User get(Long id) {
        if (!users.containsKey(id)) {
            throw new EntityNotFoundException(new Formatter().format("Пользователь с id %d не найден", id));
        }
        log.info("отдан пользователь с id {}", id);
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("Отдан список всех пользователей");
        return new ArrayList<>(users.values());
    }

    @Override
    public User delete(Long id) {
        if (!users.containsKey(id)) {
            throw new EntityNotFoundException(new Formatter().format("Пользователь с id %d не найден", id));
        }
        log.info("Пользователь с id {} удалён", id);
        return users.remove(id);
    }

    private User updateUser(User updatedUser, User userToUpdate) {
        if (updatedUser.getEmail() != null) {
            userToUpdate.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getName() != null) {
            userToUpdate.setName(updatedUser.getName());
        }
        return userToUpdate;
    }

    private boolean isExist(User user) {
        for (User checkedUser : users.values()) {
            if (isSameUsers(checkedUser, user)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameUsers(User user1, User user2) {
        return user1.getEmail().equals(user2.getEmail()) && !Objects.equals(user1.getId(), user2.getId());
    }

}
