package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemStorage;
import java.util.Collection;

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
    public Collection<Item> getAll() {
        return itemStorage.getAll();
    }

    @Override
    public Item delete(Long id) {
        return itemStorage.delete(id);
    }

    public Collection<Item> search(String text) {
        return itemStorage.search(text);
    }
}
