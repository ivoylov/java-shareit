package ru.practicum.shareit.user.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.userException.UserAlreadyExistException;
import ru.practicum.shareit.exception.userException.UserNotFoundException;
import ru.practicum.shareit.user.model.User;
import java.util.*;

@Slf4j
@Component
public class InMemoryUserStorage extends UserStorage {

    private final HashMap<Long, User> users = new HashMap<>();
    private Long counter = 0L;

    public User create(User user) {
        if (users.size() != 0) {
            for (User checkedUser : users.values()) {
                if (checkedUser.getEmail().equals(user.getEmail())) {
                    throw new UserAlreadyExistException();
                }
            }
        }
        user.setId(++counter);
        users.put(user.getId(), user);
        log.info(user + " создан");
        return user;
    }

    @Override
    public User update(User user) {
        if (!users.containsKey(user.getId())) throw new UserNotFoundException();
        User updatedUser = updateUser(user, users.get(user.getId()));
        return users.put(updatedUser.getId(),updatedUser);
    }

    @Override
    public User get(Long id) {
        if (!users.containsKey(id)) throw new UserNotFoundException();
        return users.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User delete(Long id) {
        if (!users.containsKey(id)) throw new UserNotFoundException();
        log.info("Пользователь с id " + id + " удалён");
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

}
