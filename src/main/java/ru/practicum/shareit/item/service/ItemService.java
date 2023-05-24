package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.InDbItemStorage;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService implements CrudOperations<Item> {

    private final InDbItemStorage itemStorage;
    private final UserService userService;

    @Override
    public Item create(Item item) {
        return itemStorage.create(item);
    }

    @Override
    public Item update(Item item) {
        checkOwner(item);
        Item updateItem = ItemService.updateItem(item, itemStorage.get(item.getId()));
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
    public Boolean isExist(Item item) {
        return itemStorage.isExist(item);
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
        List<Item> findItems = new ArrayList<>();
        for (Item item : itemStorage.getAll()) {
            if ((item.getName().toLowerCase().contains(text) || item.getDescription().toLowerCase().contains(text)) &&
                    item.getAvailable()) {
                findItems.add(item);
            }
        }
        return findItems;
    }

    public List<Item> getOwnerItems(Long ownerId) {
        return itemStorage.getAll().stream().filter(item -> item.getOwnerId().equals(ownerId)).collect(Collectors.toList());
    }

    public void checkItemDtoOwner(ItemDto itemDto) {
        if (!userService.isExist(itemDto.getOwnerId())) throw new EntityNotFoundException(itemDto);
    }

    public static Item updateItem(Item updatedItem, Item itemToUpdate) {
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

    private void checkOwner(Item item) {
        if (!Objects.equals(item.getOwnerId(), itemStorage.get(item.getId()).getOwnerId())) {
            throw new EntityNotFoundException(item);
        }
    }

}
