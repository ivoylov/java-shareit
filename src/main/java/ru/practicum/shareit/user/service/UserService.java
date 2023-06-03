package ru.practicum.shareit.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.InDbUserStorage;

import java.util.Formatter;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements CrudOperations<User> {

    private InDbUserStorage userStorage;

    @Override
    public User create(User user) {
        return userStorage.create(user);
    }

    @Override
    public User update(User user) {
        if (!userStorage.isExist(user.getId())) throw new EntityNotFoundException(user);
        User userToUpdate = UserService.updateUser(user, userStorage.get(user.getId()));
        return userStorage.update(userToUpdate);
    }

    @Override
    public Boolean isExist(Long id) {
        return userStorage.isExist(id);
    }

    @Override
    public Boolean isExist(User user) {
        return userStorage.isExist(user);
    }

    @Override
    public User get(Long id) {
        if (!userStorage.isExist(id)) {
            throw new EntityNotFoundException(new Formatter().format("Пользователь с id %d не найден", id));
        }
        return userStorage.get(id);
    }

    @Override
    public List<User> getAll() {
        return userStorage.getAll();
    }

    @Override
    public User delete(Long id) {
        if (!userStorage.isExist(id)) {
            throw new EntityNotFoundException(new Formatter().format("Пользователь с id %d не найден", id));
        }
        return userStorage.delete(id);
    }

    public static User updateUser(User updatedUser, User userToUpdate) {
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isBlank()) {
            userToUpdate.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getName() != null && !updatedUser.getName().isBlank()) {
            userToUpdate.setName(updatedUser.getName());
        }
        return userToUpdate;
    }

    public static Boolean isSameUsers(User user1, User user2) {
        return user1.getEmail().equals(user2.getEmail()) && user1.getId().equals(user2.getId());
    }

}
