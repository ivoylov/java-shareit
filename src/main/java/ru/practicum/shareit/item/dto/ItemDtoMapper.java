package ru.practicum.shareit.item.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ItemDtoMapper {

    public ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .ownerId(item.getOwnerId())
                .available(item.getAvailable())
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

    public List<ItemDto> toItemDtoList(List<Item> items) {
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            itemsDto.add(toItemDto(item));
        }
        return itemsDto;
    }

}
