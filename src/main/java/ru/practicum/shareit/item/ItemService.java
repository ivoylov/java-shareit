package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemDto;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.user.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService implements CrudOperations<Item> {

    private final ItemStorage itemStorage;
    private final UserService userService;

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

    public List<Item> getOwnerItems(Long ownerId) {
        return itemStorage.getOwnerItems(ownerId);
    }

    public void checkItemOwner(Long ownerId) {
        if (!userService.isExist(ownerId)) throw new EntityNotFoundException("не найден пользователь id=" + ownerId);
    }

}
