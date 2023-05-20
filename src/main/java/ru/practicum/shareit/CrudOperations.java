package ru.practicum.shareit;

import java.util.List;

public interface CrudOperations<T> {
    T create(T t);

    T update(T t);

    Boolean isExist(Long id);

    Boolean isExist(T t);

    T get(Long id);

    List<T> getAll();

    T delete(Long id);

}
