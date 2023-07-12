package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.UserAlreadyExistException;
import ru.practicum.shareit.user.model.User;

import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements CrudOperations<User> {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        if (isHaveSameUser(user)) throw new UserAlreadyExistException(user);
        return userRepository.save(user);
    }

    @Override
    public User update(User updatedUser) {
        log.info(this.getClass() + "запрос на обновление {}", updatedUser);
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isBlank()) {
            if (isHaveSameUser(updatedUser)) throw new UserAlreadyExistException(updatedUser);
        }
        User userToUpdate = userRepository.findById(updatedUser.getId()).orElseThrow(() -> new EntityNotFoundException(updatedUser));
        userToUpdate.updateUser(updatedUser);
        log.info(this.getClass() + "новое состояние user={}", userToUpdate);
        userRepository.update(userToUpdate.getName(), userToUpdate.getEmail(), userToUpdate.getId());
        return userRepository.findById(userToUpdate.getId()).orElse(null);
    }

    @Override
    public Boolean isExist(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User get(Long id) {
        log.info(this.getClass() + "запрос на получение пользователя с id={}", id);
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(new Formatter().format("Пользователь с id %d не найден", id)));
    }

    @Override
    public List<User> getAll() {
        log.info(this.getClass() + "Отдан список всех пользователей");
        return userRepository.findAll();
    }

    @Override
    public User delete(Long id) {
        log.info("Запрос на удаление пользователя с id={}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(new Formatter().format("Пользователь с id %d не найден", id)));
        userRepository.deleteById(id);
        user.setBookings(Collections.emptyList());
        user.setItems(Collections.emptyList());
        return user;
    }



    private boolean isHaveSameUser(User user) {
        for (User checkedUser : getAll()) {
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
