package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemStorage extends CrudOperations<Item> {
    List<Item> searchByNameOrDescription(String text);

    List<Item> getOwnerItems(Long ownerId);
}
