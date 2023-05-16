package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.InDbItemStorage;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService implements CrudOperations<Item> {

    private final ItemStorage itemStorage;
    private final UserService userService;

    @Autowired
    public ItemService(InDbItemStorage itemStorage, UserService userService) {
        this.itemStorage = itemStorage;
        this.userService = userService;
    }

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

    public void checkItemDtoOwner(ItemDto itemDto) {
        if (!userService.isExist(itemDto.getOwnerId())) throw new EntityNotFoundException(itemDto);
    }

}
