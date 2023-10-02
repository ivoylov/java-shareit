package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {

    @Test
    void toShortItemDtoMapper() {
        Item item = new Item(1L, "name", "description", true, null, null, null, null);
        ItemDtoOutShort itemDtoOutShort = new ItemDtoOutShort(1L, "name", null, "description", true);
        assertEquals(itemDtoOutShort, ItemMapper.toItemDtoOutShort(item));
    }

    @Test
    void toItemDtoOut() {
        Item item = new Item(null, "name", "description", true, null, null, null, null);
        ItemDtoOut itemDtoOut = new ItemDtoOut(null, "name", "description", true, null, null, new ArrayList<>(), null);
        assertEquals(itemDtoOut, ItemMapper.toItemDtoOut(item));
    }

    @Test
    void toItem() {
        Item item = new Item(null, "name", "description", true, null, null, null, null);
        ItemDtoIn itemDtoIn = new ItemDtoIn("name", "description", true, 1L);
        assertEquals(item, ItemMapper.toItem(itemDtoIn));
    }

    @Test
    void toItemDtoOutList() {
        Item item = new Item(null, "name", "description", true, null, null, new ArrayList<>(), null);
        ItemDtoOut itemDtoOut = new ItemDtoOut(null, "name", "description", true, null, null, new ArrayList<>(), null);
        assertEquals(List.of(itemDtoOut), ItemMapper.toListItemDtoOut(List.of(item)));
    }

}