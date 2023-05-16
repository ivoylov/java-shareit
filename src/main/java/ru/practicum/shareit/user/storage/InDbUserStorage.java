package ru.practicum.shareit.user.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

@Component
public class InDbUserStorage extends UserStorage {

    UserRepository userRepository;

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Boolean isExist(Long id) {
        return null;
    }

    @Override
    public User get(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User delete(Long id) {
        return null;
    }
}
