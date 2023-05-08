package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.item.model.Item;
import java.util.Collection;

public abstract class ItemStorage implements CrudOperations<Item> {
    public abstract Collection<Item> search(String text);

    public abstract Collection<Item> getOwnerItems(Long ownerId);

    public abstract Collection<Item> search(String text, Long ownerId);
}
