package ru.practicum.shareit.item.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.storage.BookingStorage;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.CommentStorage;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ItemDtoMapper {

    public ItemDto toItemDto(Item item, Booking lastBooking, Booking nextBooking, List<CommentDto> comments) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .ownerId(item.getOwnerId())
                .available(item.getAvailable())
                .lastBooking(lastBooking)
                .nextBooking(nextBooking)
                .comments(comments)
                .build();
    }

    public Item toItem(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .ownerId(itemDto.getOwnerId())
                .available(itemDto.getAvailable())
                .build();
    }

    public List<ItemDto> toItemDtoList(List<Item> items, BookingStorage bookingStorage, CommentStorage commentStorage, UserService userService) {
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            Booking lastBooking = bookingStorage.getLastBookingForItem(item.getId());
            Booking nextBooking = bookingStorage.getNextBookingForItem(item.getId());
            List<CommentDto> comments = CommentDtoMapper.toCommentDtoList(commentStorage.getAllForItem(item.getId()), userService);
            itemsDto.add(ItemDtoMapper.toItemDto(item, lastBooking, nextBooking, comments));
        }
        return itemsDto;
    }

}
