package ru.practicum.shareit.item.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.itemException.ItemNotFoundException;
import ru.practicum.shareit.item.model.Item;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InMemoryItemStorage extends ItemStorage {

    private final HashMap<Long, Item> items = new HashMap();
    private Long counter = 0L;

    @Override
    public Item create(Item item) {
        item.setId(++counter);
        items.put(item.getId(), item);
        log.info(item + " создан");
        return item;
    }

    @Override
    public Item update(Item item) {
        checkOwner(item);
        Item updateItem = updateItem(item, items.get(item.getId()));
        log.info(updateItem + " обновлён");
        return items.put(updateItem.getId(),updateItem);
    }

    @Override
    public Boolean isExist(Long id) {
        return items.containsKey(id);
    }

    @Override
    public Item get(Long id) {
        return items.get(id);
    }

    @Override
    public Collection<Item> getAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Item delete(Long id) {
        return items.remove(id);
    }

    @Override
    public Collection<Item> search(String text) {
        Collection<Item> findItems = new ArrayList<>();
        for (Item item : items.values()) {
            if ((item.getName().toLowerCase().contains(text) || item.getDescription().toLowerCase().contains(text)) &&
                    item.getAvailable()) {
                findItems.add(item);
            }
        }
        return findItems;
    }

    @Override
    public Collection<Item> search(String text, Long ownerId) {
        Collection<Item> findItems = new ArrayList<>();
        for (Item item : items.values()) {
            if ((item.getName().toLowerCase().contains(text) ||
                    item.getDescription().toLowerCase().contains(text)) &&
                    item.getOwnerId().equals(ownerId)) {
                findItems.add(item);
            }
        }
        return findItems;
    }

    @Override
    public Collection<Item> getOwnerItems(Long ownerId) {
        return items.values().stream().filter(item->item.getOwnerId().equals(ownerId)).collect(Collectors.toList());
    }

    private Item updateItem(Item updatedItem, Item itemToUpdate) {
        if (updatedItem.getName() != null) {
            itemToUpdate.setName(updatedItem.getName());
        }
        if (updatedItem.getDescription() != null) {
            itemToUpdate.setDescription(updatedItem.getDescription());
        }
        if (updatedItem.getAvailable() != null) {
            itemToUpdate.setAvailable(updatedItem.getAvailable());
        }
        return itemToUpdate;
    }

    private void checkOwner(Item item) {
        if (!Objects.equals(item.getOwnerId(), items.get(item.getId()).getOwnerId())) throw new ItemNotFoundException();
    }

}
