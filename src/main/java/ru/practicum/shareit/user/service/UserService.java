package ru.practicum.shareit.user.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.InDbUserStorage;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements CrudOperations<User> {

    private final UserStorage userStorage;

    @Autowired
    public UserService(InDbUserStorage userStorage) {
        this.userStorage = userStorage;
    }

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
    public List<User> getAll() {
        return userStorage.getAll();
    }

    @Override
    public User delete(Long id) {
        return userStorage.delete(id);
    }

}
