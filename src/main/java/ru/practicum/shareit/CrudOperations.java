package ru.practicum.shareit;

import java.util.Collection;

public interface CrudOperations<T> {
    T create(T t);

    T update(T t);

    Boolean isExist(Long id);

    T get(Long id);

    Collection<T> getAll();

    T delete(Long id);

}
