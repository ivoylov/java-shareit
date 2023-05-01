package ru.practicum.shareit.storage;

public interface Storage<T> {
    T create(T t);
}
