package ru.practicum.shareit.item.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.itemException.ItemNotFoundException;
import ru.practicum.shareit.item.model.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

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
