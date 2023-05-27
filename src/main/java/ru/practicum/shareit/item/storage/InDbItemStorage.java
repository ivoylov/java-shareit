package ru.practicum.shareit.item.storage;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.storage.InDbBookingStorage;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class InDbItemStorage implements ItemStorage {

    private final ItemRepository itemRepository;

    @Override
    public Item create(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item update(Item item) {
        itemRepository.update(item.getName(),item.getDescription(), item.getAvailable(), item.getId());
        return item;
    }

    @Override
    public Boolean isExist(Long id) {
        return itemRepository.existsById(id);
    }

    @Override
    public Boolean isExist(Item item) {
        for (Item checkedItem : itemRepository.findAll()) {
            if (checkedItem.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Item get(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) throw new EntityNotFoundException(item);
        return item.orElse(null);
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item delete(Long id) {
        Item item = get(id);
        itemRepository.deleteById(id);
        return item;
    }

}
