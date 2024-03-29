package ru.practicum.shareit.item.model;

import ru.practicum.shareit.booking.model.BookingMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {

    public static ItemDtoOutShort toItemDtoOutShort(Item item) {
        return ItemDtoOutShort.builder()
                .id(item.getId())
                .name(item.getName())
                .requestId(item.getRequest() == null ? null : item.getRequest().getId())
                .description(item.getDescription())
                .available(item.getAvailable())
                .build();
    }

    public static ItemDtoOut toItemDtoOut(Item item) {
        return ItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .lastBooking(BookingMapper.toBookingDtoOutShort(item.getLastBooking()))
                .nextBooking(BookingMapper.toBookingDtoOutShort(item.getNextBooking()))
                .comments(CommentMapper.toCommentDtoOutList(item.getComments()))
                .requestId(item.getRequest() == null ? null : item.getRequest().getId())
                .build();
    }

    public static Item toItem(ItemDtoIn itemDtoIn) {
        return Item.builder()
                .name(itemDtoIn.getName())
                .description(itemDtoIn.getDescription())
                .available(itemDtoIn.getAvailable())
                .build();
    }

    public static List<ItemDtoOut> toListItemDtoOut(List<Item> items) {
        if (items == null || items.size() == 0) return Collections.emptyList();
        return items.stream()
                .map(ItemMapper::toItemDtoOut)
                .collect(Collectors.toList());
    }

    public static List<ItemDtoOutShort> toListItemDtoOutShort(List<Item> items) {
        if (items == null || items.size() == 0) return Collections.emptyList();
        return items.stream()
                .map(ItemMapper::toItemDtoOutShort)
                .collect(Collectors.toList());
    }

}
