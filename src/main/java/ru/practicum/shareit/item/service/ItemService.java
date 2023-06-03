package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.storage.InDbBookingStorage;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.CommentDtoMapper;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.CommentStorage;
import ru.practicum.shareit.item.storage.InDbItemStorage;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ItemService implements CrudOperations<ItemDto> {

    private final InDbItemStorage itemStorage;
    private final InDbBookingStorage bookingStorage;
    private final UserService userService;
    private final CommentStorage commentStorage;

    @Override
    public ItemDto create(ItemDto itemDto) {
        checkItemDtoOwner(itemDto);
        Item item = itemStorage.create(ItemDtoMapper.toItem(itemDto));
        Booking lastBooking = bookingStorage.getLastBookingForItem(item.getId());
        Booking nextBooking = bookingStorage.getNextBookingForItem(item.getId());
        List<CommentDto> comments = CommentDtoMapper.toCommentDtoList(commentStorage.getAllForItem(item.getId()), userService);
        return ItemDtoMapper.toItemDto(item, lastBooking, nextBooking, comments);
    }

    @Override
    public ItemDto update(ItemDto itemDto) {
        Item item = ItemDtoMapper.toItem(itemDto);
        checkOwner(item);
        Item updateItem = updateItem(item, itemStorage.get(item.getId()));
        Booking lastBooking = bookingStorage.getLastBookingForItem(item.getId());
        Booking nextBooking = bookingStorage.getNextBookingForItem(item.getId());
        List<CommentDto> comments = CommentDtoMapper.toCommentDtoList(commentStorage.getAllForItem(item.getId()), userService);
        return ItemDtoMapper.toItemDto(itemStorage.update(updateItem), lastBooking, nextBooking, comments);
    }

    @Override
    public ItemDto get(Long id) {
        log.info(ItemService.class + " get itemId=" + id);
        Item item = itemStorage.get(id);
        log.info(item.toString());
        List<CommentDto> comments = CommentDtoMapper.toCommentDtoList(commentStorage.getAllForItem(item.getId()), userService);
        return ItemDtoMapper.toItemDto(item, null, null, comments);
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
        return ItemDtoMapper.toItemDtoList(itemStorage.getAll(), bookingStorage, commentStorage, userService);
    }

    @Override
    public ItemDto delete(Long id) {
        Item item = itemStorage.get(id);
        Booking lastBooking = bookingStorage.getLastBookingForItem(item.getId());
        Booking nextBooking = bookingStorage.getNextBookingForItem(item.getId());
        List<CommentDto> comments = CommentDtoMapper.toCommentDtoList(commentStorage.getAllForItem(item.getId()), userService);
        return ItemDtoMapper.toItemDto(itemStorage.delete(id), lastBooking, nextBooking, comments);
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
        return ItemDtoMapper.toItemDtoList(findItems, bookingStorage, commentStorage, userService);
    }

    public List<ItemDto> getOwnerItems(Long ownerId) {
        log.info(ItemService.class + " get for ownerId=" + ownerId);
        List<ItemDto> ownerItems = new ArrayList<>();
        for (Item item : itemStorage.getOwnerItems(ownerId)) {
            Booking lastBooking = bookingStorage.getLastBookingForItemByOwner(item.getId(), ownerId);
            Booking nextBooking = bookingStorage.getNextBookingForItemByOwner(item.getId(), ownerId);
            List<CommentDto> comments = CommentDtoMapper.toCommentDtoList(commentStorage.getAllForItem(item.getId()), userService);
            ownerItems.add(ItemDtoMapper.toItemDto(item, lastBooking, nextBooking, comments));
        }
        return ownerItems;
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

    public ItemDto get(Long itemId, Long userId) {
        log.info(ItemService.class + " get itemId=" + itemId + " for userId=" + userId);
        Item item = itemStorage.get(itemId);
        log.info(item.toString());
        if (item.getOwnerId().equals(userId)) {
            log.info(ItemService.class + " бронирования запрашивает владелец get itemId=" + itemId + " for userId=" + userId);
            Booking lastBooking = bookingStorage.getLastBookingForItemByOwner(item.getId(), userId);
            log.info("last booking=" + lastBooking);
            Booking nextBooking = bookingStorage.getNextBookingForItemByOwner(item.getId(), userId);
            log.info("next booking=" + nextBooking);
            List<CommentDto> comments = CommentDtoMapper.toCommentDtoList(commentStorage.getAllForItem(item.getId()), userService);
            return ItemDtoMapper.toItemDto(item, lastBooking, nextBooking, comments);
        } else {
            log.info(ItemService.class + " бронирования запрашивает не владелец get itemId=" + itemId + " for userId=" + userId);
            return get(itemId);
        }
    }

}
