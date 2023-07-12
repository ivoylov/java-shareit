package ru.practicum.shareit.item;

import ch.qos.logback.classic.spi.IThrowableProxy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;

import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ItemService implements CrudOperations<Item> {

    private final ItemRepository itemRepository;
    private final UserService userService;

    @Override
    public Item create(Item item) {
        log.info(this.getClass() + " запрос на создание {}", item);
        if (!userService.isExist(item.getOwner().getId())) {
            throw new EntityNotFoundException(" не найден пользователь " + item.getOwner());
        }
        return itemRepository.save(item);
    }

    @Override
    public Item update(Item updatedItem) {
        log.info(this.getClass() + " запрос на обновление {}", updatedItem);
        checkOwner(updatedItem);
        Item itemToUpdate = itemRepository.findById(updatedItem.getId()).orElseThrow(() -> new EntityNotFoundException(updatedItem));
        itemToUpdate.updateItem(updatedItem);
        itemRepository.update(itemToUpdate.getName(), itemToUpdate.getDescription(), itemToUpdate.getAvailable(), itemToUpdate.getId());
        return itemRepository.findById(itemToUpdate.getId()).orElse(null);
    }

    @Override
    public Item get(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(new Formatter().format("Пользователь с id %d не найден", id)));
    }

    @Override
    public Boolean isExist(Long id) {
        return itemRepository.existsById(id);
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item delete(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Не найден Item с d"));
        itemRepository.deleteById(id);
        item.setBookings(Collections.emptyList());
        return item;
    }

    public List<Item> searchByNameOrDescription(String text) {
        String formText = text.toLowerCase();
        return itemRepository.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndAvailable(formText, formText, true);
    }

    public List<Item> getOwnerItems(Long ownerId) {
        return itemRepository.findOwnerItems(ownerId);
    }

    private void checkOwner(Item item) {
        Item checkedItem = itemRepository.findById(item.getId()).orElseThrow(() -> new EntityNotFoundException(item));
        Long itemOwnerId = item.getOwner().getId();
        Long checkedOwnerId = checkedItem.getOwner().getId();
        if (!itemOwnerId.equals(checkedOwnerId)) {
            throw new EntityNotFoundException(item);
        }
    }

}
