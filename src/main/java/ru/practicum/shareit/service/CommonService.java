package ru.practicum.shareit.service;

public interface CommonService<T> {
    T create(T t);
    T update(T t);
    T get(Long id);
    T delete (Long id);
}
