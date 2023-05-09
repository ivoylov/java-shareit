package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemStorage;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService implements CrudOperations<Item> {

    private final ItemStorage itemStorage;

    @Override
    public Item create(Item item) {
        return itemStorage.create(item);
    }

    @Override
    public Item update(Item item) {
        return itemStorage.update(item);
    }

    @Override
    public Item get(Long id) {
        return itemStorage.get(id);
    }

    @Override
    public Boolean isExist(Long id) {
        return itemStorage.isExist(id);
    }

    @Override
    public List<Item> getAll() {
        return itemStorage.getAll();
    }

    @Override
    public Item delete(Long id) {
        return itemStorage.delete(id);
    }

    public List<Item> search(String text) {
        return itemStorage.search(text);
    }

    public List<Item> search(String text, Long ownerId) {
        return itemStorage.search(text, ownerId);
    }

    public List<Item> getOwnerItems(Long ownerId) {
        return itemStorage.getOwnerItems(ownerId);
    }

}
