package ru.practicum.shareit;

import ru.practicum.shareit.user.model.User;

import java.util.Collection;
import java.util.List;

public interface CrudOperations<T> {
    T create(T t);
    T update(T t);
    Boolean isExist(Long id);
    T get(Long id);
    Collection<T> getAll();
    T delete(Long id);
}
