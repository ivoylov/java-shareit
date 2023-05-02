package ru.practicum.shareit.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.service.CommonService;
import ru.practicum.shareit.storage.UserStorage;
import ru.practicum.shareit.user.model.User;

@Service
@AllArgsConstructor
public class UserService implements CommonService<User> {

    private UserStorage storage;

    @Override
    public User create(User user) {
        return storage.create(user);
    }

    @Override
    public User update(User user) {
        return storage.update(user);
    }

    @Override
    public User delete(Long id) {
        return storage.delete(id);
    }

    @Override
    public User get(Long id) {
        return storage.delete(id);
    }

}
