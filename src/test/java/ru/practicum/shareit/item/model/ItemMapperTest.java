package ru.practicum.shareit.item.model;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {

    @Test
    void toShortItemDtoMapper() {
        Item item = new Item(1L, "name", "description", true, null, null, null);
        ItemDtoOutShort itemDtoOutShort = new ItemDtoOutShort(1L, "name");
        assertEquals(itemDtoOutShort, ItemMapper.toShortItemDtoMapper(item));
    }

    @Test
    void toItemDtoOut() {
        Item item = new Item(null, "name", "description", true, null, null, null);
        ItemDtoOut itemDtoOut = new ItemDtoOut(null, "name", "description", true, null, null, new ArrayList<>());
        assertEquals(itemDtoOut, ItemMapper.toItemDtoOut(item));
    }

    @Test
    void toItem() {
        Item item = new Item(null, "name", "description", true, null, null, null);
        ItemDtoIn itemDtoIn = new ItemDtoIn("name", "description", true);
        assertEquals(item, ItemMapper.toItem(itemDtoIn));
    }

    @Test
    void toItemDtoOutList() {
        Item item = new Item(null, "name", "description", true, null, null, new ArrayList<>());
        ItemDtoOut itemDtoOut = new ItemDtoOut(null, "name", "description", true, null, null, new ArrayList<>());
        assertEquals(List.of(itemDtoOut), ItemMapper.toItemDtoOutList(List.of(item)));
    }

}