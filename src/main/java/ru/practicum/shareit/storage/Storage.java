package ru.practicum.shareit.storage;

public interface Storage<T> {
    T create(T t);
    T update(T t);
    T get(Long id);
    T delete(Long id);
}
