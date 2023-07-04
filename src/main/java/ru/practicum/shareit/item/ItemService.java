package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.InMemoryItemStorage;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.storage.InMemoryUserStorage;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ItemService implements CrudOperations<Item> {

    private final InMemoryItemStorage itemStorage;
    private final UserService userService;

    @Override
    public Item create(Item item) {
        log.info(this.getClass() + " запрос на создание item={}", item);
        checkItemOwner(item.getOwnerId());
        return itemStorage.create(item);
    }

    @Override
    public Item update(Item item) {
        log.info(this.getClass() + " запрос на обновление item={}", item);
        checkOwner(item);
        Item updateItem = updateItem(item, itemStorage.get(item.getId()));
        return itemStorage.update(updateItem);
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


    public List<Item> searchByNameOrDescription(String text) {
        return itemStorage.searchByNameOrDescription(text);
    }

    public List<Item> getOwnerItems(Long ownerId) {
        return itemStorage.getOwnerItems(ownerId);
    }

    private void checkItemOwner(Long ownerId) {
        if (!userService.isExist(ownerId)) throw new EntityNotFoundException("не найден пользователь id=" + ownerId);
    }

    private void checkOwner(Item item) {
        if (!Objects.equals(item.getOwnerId(), itemStorage.get(item.getId()).getOwnerId())) {
            throw new EntityNotFoundException(item);
        }
    }

    private Item updateItem(Item updatedItem, Item itemToUpdate) {
        if (updatedItem.getName() != null && !updatedItem.getName().isBlank()) {
            itemToUpdate.setName(updatedItem.getName());
        }
        if (updatedItem.getDescription() != null && !updatedItem.getDescription().isBlank()) {
            itemToUpdate.setDescription(updatedItem.getDescription());
        }
        if (updatedItem.getAvailable() != null) {
            itemToUpdate.setAvailable(updatedItem.getAvailable());
        }
        return itemToUpdate;
    }

}
