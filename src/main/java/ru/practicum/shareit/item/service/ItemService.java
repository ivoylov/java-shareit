package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.booking.storage.InDbBookingStorage;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.InDbItemStorage;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ItemService implements CrudOperations<ItemDto> {

    private final InDbItemStorage itemStorage;
    private final InDbBookingStorage bookingStorage;
    private final UserService userService;

    @Override
    public ItemDto create(ItemDto itemDto) {
        checkItemDtoOwner(itemDto);
        Item item = itemStorage.create(ItemDtoMapper.toItem(itemDto));
        Booking lastBooking = bookingStorage.getLastBookingForItem(item.getId());
        Booking nextBooking = bookingStorage.getNextBookingForItem(item.getId());
        return ItemDtoMapper.toItemDto(item, lastBooking, nextBooking);
    }

    @Override
    public ItemDto update(ItemDto itemDto) {
        Item item = ItemDtoMapper.toItem(itemDto);
        checkOwner(item);
        Item updateItem = updateItem(item, itemStorage.get(item.getId()));
        Booking lastBooking = bookingStorage.getLastBookingForItem(item.getId());
        Booking nextBooking = bookingStorage.getNextBookingForItem(item.getId());
        return ItemDtoMapper.toItemDto(itemStorage.update(updateItem), lastBooking, nextBooking);
    }

    @Override
    public ItemDto get(Long id) {
        log.info(ItemService.class + " get itemId=" + id);
        Item item = itemStorage.get(id);
        log.info(item.toString());
        Booking lastBooking = bookingStorage.getLastBookingForItem(item.getId());
        log.info("last booking=" + lastBooking);
        Booking nextBooking = bookingStorage.getNextBookingForItem(item.getId());
        log.info("last booking=" + nextBooking);
        return ItemDtoMapper.toItemDto(item, lastBooking, nextBooking);
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
        return toItemDtoList(itemStorage.getAll());
    }

    @Override
    public ItemDto delete(Long id) {
        Item item = itemStorage.get(id);
        Booking lastBooking = bookingStorage.getLastBookingForItem(item.getId());
        Booking nextBooking = bookingStorage.getNextBookingForItem(item.getId());
        return ItemDtoMapper.toItemDto(itemStorage.delete(id), lastBooking, nextBooking);
    }

    public List<ItemDto> search(String text) {
        List<Item> findItems = new ArrayList<>();
        for (Item item : itemStorage.getAll()) {
            if ((item.getName().toLowerCase().contains(text) ||
                item.getDescription().toLowerCase().contains(text)) &&
                item.getAvailable()) {
                    findItems.add(item);
            }
        }
        return toItemDtoList(findItems);
    }

    public List<ItemDto> getOwnerItems(Long ownerId) {
        return  toItemDtoList(itemStorage.getAll().stream()
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
        if (!item.getOwnerId().equals(itemStorage.get(item.getId()).getOwnerId())) {
            throw new EntityNotFoundException(item);
        }
    }

    private List<ItemDto> toItemDtoList(List<Item> items) {
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            Booking lastBooking = bookingStorage.getLastBookingForItem(item.getId());
            Booking nextBooking = bookingStorage.getNextBookingForItem(item.getId());
            itemsDto.add(ItemDtoMapper.toItemDto(item, lastBooking, nextBooking));
        }
        return itemsDto;
    }

    public ItemDto get(Long itemId, Long ownerId) {
        log.info(ItemService.class + " get itemId=" + itemId + " for ownerId=" + ownerId);
        List<Item> items = itemStorage.get(itemId, ownerId);
        log.info(items.toString());
        return null;
    }
}
