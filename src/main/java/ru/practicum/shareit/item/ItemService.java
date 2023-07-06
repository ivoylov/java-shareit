package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;

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
        if (!itemRepository.existsById(updatedItem.getId())) throw new EntityNotFoundException(updatedItem);
        log.info(this.getClass() + " запрос на обновление {}", updatedItem);
        checkOwner(updatedItem);
        Item itemToUpdate = itemRepository.findById(updatedItem.getId()).get();
        Item updateItem = updateItem(updatedItem, itemToUpdate);
        itemRepository.update(updateItem.getName(), updateItem.getDescription(), updateItem.getAvailable(), updateItem.getId());
        return updateItem;
    }

    @Override
    public Item get(Long id) {
        return itemRepository.getReferenceById(id);
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
        Item item = itemRepository.getReferenceById(id);
        itemRepository.deleteById(id);
        return item;
    }

    public List<Item> searchByNameOrDescription(String text) {
        String formText = text.toLowerCase();
        return itemRepository.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(formText, formText);
    }

    public List<Item> getOwnerItems(Long ownerId) {
        return itemRepository.findOwnerItems(ownerId);
    }

    private void checkOwner(Item item) {
        if (!Objects.equals(item.getOwner().getId(), itemRepository.getReferenceById(item.getId()).getOwner().getId())) {
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
