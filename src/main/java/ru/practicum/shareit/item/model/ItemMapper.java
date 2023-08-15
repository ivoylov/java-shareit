package ru.practicum.shareit.item.model;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.booking.model.BookingMapper;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ItemMapper {

    public ItemDtoOutShort toShortItemDtoMapper(Item item) {
        return ItemDtoOutShort.builder()
                .id(item.getId())
                .name(item.getName())
                .build();
    }

    public ItemDtoOut toItemDtoOut(Item item) {
        return ItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .lastBooking(BookingMapper.toBookingDtoOutShort(item.getLastBooking()))
                .nextBooking(BookingMapper.toBookingDtoOutShort(item.getNextBooking()))
                .comments(CommentMapper.toCommentDtoOutList(item.getComments()))
                .build();
    }

    public Item toItem(ItemDtoIn itemDtoIn) {
        return Item.builder()
                .name(itemDtoIn.getName())
                .description(itemDtoIn.getDescription())
                .available(itemDtoIn.getAvailable())
                .build();
    }

    public List<ItemDtoOut> toItemDtoOutList(List<Item> items) {
        List<ItemDtoOut> itemsDto = new ArrayList<>();
        for (Item item : items) {
            itemsDto.add(toItemDtoOut(item));
        }
        return itemsDto;
    }

}
