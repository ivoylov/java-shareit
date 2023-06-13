package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemStorage extends CrudOperations<Item> {
    List<Item> get(Long itemId, Long ownerId);

    List<Item> getOwnerItems(Long ownerId);

    List<Item> getItemsForRequest(Long requestId);
}
