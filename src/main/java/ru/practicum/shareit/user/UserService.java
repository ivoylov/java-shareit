package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.UserAlreadyExistException;
import ru.practicum.shareit.user.model.User;

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
        if (isExist(user.getId())) throw new UserAlreadyExistException();
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        if (!isExist(user.getId())) throw new EntityNotFoundException(user);
        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            if (isHaveSameUser(user)) throw new UserAlreadyExistException();
        }
        log.info(this.getClass() + "обновлён {}", user);
        User updatedUser = updateUser(user, this.get(user.getId()));
        log.info(this.getClass() + "новое состояние user={}", updatedUser);
        userRepository.update(updatedUser.getName(), updatedUser.getEmail(), updatedUser.getId());
        return updatedUser;
    }

    @Override
    public Boolean isExist(Long id) {
        return userRepository.findById(id).isPresent();
    }

    @Override
    public User get(Long id) {
        if (!isExist(id)) {
            throw new EntityNotFoundException(new Formatter().format("Пользователь с id %d не найден", id));
        }
        log.info(this.getClass() + "отдан пользователь с id {}", id);
        return userRepository.getReferenceById(id);
    }

    @Override
    public List<User> getAll() {
        log.info(this.getClass() + "Отдан список всех пользователей");
        return userRepository.findAll();
    }

    @Override
    public User delete(Long id) {
        if (!isExist(id)) {
            throw new EntityNotFoundException(new Formatter().format("Пользователь с id %d не найден", id));
        }
        log.info("Пользователь с id {} удалён", id);
        User user = userRepository.getReferenceById(id);
        userRepository.deleteById(id);
        return user;
    }

    private User updateUser(User updatedUser, User userToUpdate) {
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isBlank()) {
            userToUpdate.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getName() != null && !updatedUser.getName().isBlank()) {
            userToUpdate.setName(updatedUser.getName());
        }
        return userToUpdate;
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
