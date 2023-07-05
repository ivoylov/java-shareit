package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class InDbUserStorage implements CrudOperations<User> {

    private final UserRepository userRepository;

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
        return null;
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
