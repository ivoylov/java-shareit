package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemDtoMapperTest {

    private Item item;
    private ItemDto itemDto;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "name", "user@email.ru", new ArrayList<>(), new ArrayList<>());
        item = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(user)
                .build();
        itemDto = ItemDto.builder()
                .id(2L)
                .name("dtoName")
                .description("dtoDescription")
                .available(true)
                .owner(user)
                .build();
    }

    @Test
    void toItemDto() {
        Item testItem = Item.builder()
                .id(2L)
                .name("dtoName")
                .description("dtoDescription")
                .available(true)
                .owner(user)
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
                .owner(user)
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
                .owner(user)
                .build();
        ArrayList<ItemDto> itemDtoList = new ArrayList<>(List.of(testItemDto));
        ArrayList<Item> itemList = new ArrayList<>(List.of(item));
        assertEquals(itemDtoList, ItemDtoMapper.toItemDtoList(itemList));
    }

}