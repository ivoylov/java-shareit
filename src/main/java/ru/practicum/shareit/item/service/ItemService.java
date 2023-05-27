package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.InDbItemStorage;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService implements CrudOperations<ItemDto> {

    private final InDbItemStorage itemStorage;
    private final UserService userService;
    private final BookingService bookingService;

    @Override
    public ItemDto create(ItemDto itemDto) {
        checkItemDtoOwner(itemDto);
        Item item = itemStorage.create(ItemDtoMapper.toItem(itemDto));
        Booking lastBooking = bookingService.getLastBookingForItem(item.getId());
        Booking nextBooking = bookingService.getNextBookingForItem(item.getId());
        return ItemDtoMapper.toItemDto(item, lastBooking, nextBooking);
    }

    @Override
    public ItemDto update(ItemDto itemDto) {
        Item item = ItemDtoMapper.toItem(itemDto);
        checkOwner(item);
        Item updateItem = ItemService.updateItem(item, itemStorage.get(item.getId()));
        return ItemDtoMapper.toItemDto(itemStorage.update(updateItem));
    }

    @Override
    public ItemDto get(Long id) {
        return ItemDtoMapper.toItemDto(itemStorage.get(id));
    }

    @Override
    public Boolean isExist(Long id) {
        return itemStorage.isExist(id);
    }

    @Override
    public Boolean isExist(ItemDto itemDto) {
        return itemStorage.isExist(ItemDtoMapper.toItem(itemDto));
    }

    @Override
    public List<ItemDto> getAll() {
        return ItemDtoMapper.toItemDtoList(itemStorage.getAll());
    }

    @Override
    public ItemDto delete(Long id) {
        return ItemDtoMapper.toItemDto(itemStorage.delete(id));
    }

    public List<ItemDto> search(String text) {
        List<ItemDto> findItems = new ArrayList<>();
        for (Item item : itemStorage.getAll()) {
            if ((item.getName().toLowerCase().contains(text) ||
                    item.getDescription().toLowerCase().contains(text)) &&
                    item.getAvailable()) {
                findItems.add(ItemDtoMapper.toItemDto(item));
            }
        }
        return findItems;
    }

    public List<ItemDto> getOwnerItems(Long ownerId) {
        return  ItemDtoMapper.toItemDtoList(itemStorage.getAll().stream()
                .filter(item -> item.getOwnerId().equals(ownerId)).collect(Collectors.toList()));
    }

    public void checkItemDtoOwner(ItemDto itemDto) {
        if (!userService.isExist(itemDto.getOwnerId())) {
            throw new EntityNotFoundException(itemDto);
        }
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
