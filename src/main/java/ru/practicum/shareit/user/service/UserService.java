package ru.practicum.shareit.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.storage.UserStorage;
import ru.practicum.shareit.user.model.User;

@Service
@AllArgsConstructor
public class UserService {

    private UserStorage storage;

    public User create(User user) {
        return storage.create(user);
    }

}
