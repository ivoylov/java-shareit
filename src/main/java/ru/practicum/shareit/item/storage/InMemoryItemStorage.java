package ru.practicum.shareit.item.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Component
@Slf4j
public class InMemoryItemStorage extends ItemStorage {

    private final HashMap<Long, Item> items = new HashMap();
    private Long counter = 0L;

    @Override
    public Item create(Item item) {
        item.setId(++counter);
        return items.put(item.getId(), item);
    }

    @Override
    public Item update(Item item) {
        return items.put(item.getId(), item);
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

}
