package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.entity.EntityNotFoundException;
import ru.practicum.shareit.user.model.User;

import java.util.Collections;
import java.util.Formatter;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User updatedUser, Long userId) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(updatedUser));
        updatedUser.setId(userId);
        log.info(this.getClass() + " запрос на обновление {}", userToUpdate);
        userToUpdate.updateUser(updatedUser);
        userRepository.update(userToUpdate.getName(), userToUpdate.getEmail(), userToUpdate.getId());
        log.info(this.getClass() + " новое состояние {}", userToUpdate);
        return userRepository.findById(userToUpdate.getId()).orElse(null);
    }

    public Boolean isExist(Long id) {
        return userRepository.existsById(id);
    }

    public User get(Long id) {
        log.info(this.getClass() + "запрос на получение пользователя с id={}", id);
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(new Formatter().format("Пользователь с id %d не найден", id)));
    }

    public List<User> getAll() {
        log.info(this.getClass() + "Отдан список всех пользователей");
        return userRepository.findAll();
    }

    public User delete(Long id) {
        log.info("Запрос на удаление пользователя с id={}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(new Formatter().format("Пользователь с id %d не найден", id)));
        userRepository.deleteById(id);
        user.setBookings(Collections.emptyList());
        user.setItems(Collections.emptyList());
        return user;
    }

}
