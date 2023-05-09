package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public abstract class ItemStorage implements CrudOperations<Item> {
    public abstract List<Item> search(String text);

    public abstract List<Item> getOwnerItems(Long ownerId);

    public abstract List<Item> search(String text, Long ownerId);
}
