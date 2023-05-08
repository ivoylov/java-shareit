package ru.practicum.shareit.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.user.storage.UserStorage;
import ru.practicum.shareit.user.model.User;
import java.util.Collection;

@Service
@AllArgsConstructor
public class UserService implements CrudOperations<User> {

    private final UserStorage userStorage;

    @Override
    public User create(User user) {
        return userStorage.create(user);
    }

    @Override
    public User update(User user) {
        return userStorage.update(user);
    }

    @Override
    public Boolean isExist(Long id) {
        return userStorage.isExist(id);
    }

    @Override
    public User get(Long id) {
        return userStorage.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return userStorage.getAll();
    }

    @Override
    public User delete(Long id) {
        return userStorage.delete(id);
    }

}
