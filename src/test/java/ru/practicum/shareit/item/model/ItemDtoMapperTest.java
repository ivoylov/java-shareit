package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemDtoMapperTest {

    private Item item;
    private ItemDto itemDto;

    @BeforeEach
    void setUp() {
        item = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .ownerId(1L)
                .build();
        itemDto = ItemDto.builder()
                .id(2L)
                .name("dtoName")
                .description("dtoDescription")
                .available(true)
                .ownerId(2L)
                .build();
    }

    @Test
    void toItemDto() {
        Item testItem = Item.builder()
                .id(2L)
                .name("dtoName")
                .description("dtoDescription")
                .available(true)
                .ownerId(2L)
                .build();
        assertEquals(itemDto, ItemDtoMapper.toItemDto(testItem));
    }

    @Test
    void toItem() {
        ItemDto testItemDto = ItemDto.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .ownerId(1L)
                .build();
        assertEquals(item, ItemDtoMapper.toItem(testItemDto));
    }

    @Test
    void toItemDtoList() {
        ItemDto testItemDto = ItemDto.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .ownerId(1L)
                .build();
        ArrayList<ItemDto> itemDtoList = new ArrayList<>(List.of(testItemDto));
        ArrayList<Item> itemList = new ArrayList<>(List.of(item));
        assertEquals(itemDtoList, ItemDtoMapper.toItemDtoList(itemList));
    }

}