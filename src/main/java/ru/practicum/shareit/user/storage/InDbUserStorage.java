package ru.practicum.shareit.user.storage;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class InDbUserStorage extends UserStorage {

    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean isExist(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User get(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User delete(Long id) {
        User user = get(id);
        userRepository.deleteById(id);
        return user;
    }
}
