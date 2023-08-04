package ru.practicum.shareit.item.model;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ItemMapper {

    public ItemDtoOut toItemDtoOut(Item item) {
        return ItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .build();
    }

    public Item toItem(ItemDtoIn itemDtoIn) {
        return Item.builder()
                .name(itemDtoIn.getName())
                .description(itemDtoIn.getDescription())
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
