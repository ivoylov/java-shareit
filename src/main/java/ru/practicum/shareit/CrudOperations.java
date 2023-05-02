package ru.practicum.shareit;

public interface CrudOperations<T> {
    T create(T t);
    T update(T t);
    T get(Long id);
    T delete(Long id);
}
