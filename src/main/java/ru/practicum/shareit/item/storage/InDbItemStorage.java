package ru.practicum.shareit.item.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Component
public class InDbItemStorage extends ItemStorage {
    @Override
    public Item create(Item item) {
        return null;
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public Boolean isExist(Long id) {
        return null;
    }

    @Override
    public Boolean isExist(Item item) {
        return null;
    }

    @Override
    public Item get(Long id) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public Item delete(Long id) {
        return null;
    }

    @Override
    public List<Item> search(String text) {
        return null;
    }

    @Override
    public List<Item> getOwnerItems(Long ownerId) {
        return null;
    }

    @Override
    public List<Item> search(String text, Long ownerId) {
        return null;
    }
}
