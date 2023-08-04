package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemDtoMapperTest {

    private Item item;
    private ItemDtoIn itemDto;
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
        itemDto = ItemDtoIn.builder()
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
        assertEquals(itemDto, ItemMapper.toItemDtoIn(testItem));
    }

    @Test
    void toItem() {
        ItemDtoIn testItemDto = ItemDtoIn.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(user)
                .build();
        assertEquals(item, ItemMapper.toItem(testItemDto));
    }

    @Test
    void toItemDtoList() {
        ItemDtoIn testItemDto = ItemDtoIn.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(user)
                .build();
        ArrayList<ItemDtoIn> itemDtoList = new ArrayList<>(List.of(testItemDto));
        ArrayList<Item> itemList = new ArrayList<>(List.of(item));
        assertEquals(itemDtoList, ItemMapper.toItemDtoOutList(itemList));
    }

}