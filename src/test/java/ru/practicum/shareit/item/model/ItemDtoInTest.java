package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemDtoInTest {

    private ItemDtoIn itemDtoIn;

    @BeforeEach
    void setUp() {
        itemDtoIn = new ItemDtoIn("name", "description", true, 1L);
    }

    @Test
    void getName() {
        assertEquals("name", itemDtoIn.getName());
    }

    @Test
    void getDescription() {
        assertEquals("description", itemDtoIn.getDescription());
    }

    @Test
    void getAvailable() {
        assertEquals(true, itemDtoIn.getAvailable());
    }

    @Test
    void setName() {
        itemDtoIn.setName("newName");
        assertEquals("newName", itemDtoIn.getName());
    }

    @Test
    void setDescription() {
        itemDtoIn.setDescription("newDescription");
        assertEquals("newDescription", itemDtoIn.getDescription());
    }

    @Test
    void setAvailable() {
        itemDtoIn.setAvailable(false);
        assertEquals(false, itemDtoIn.getAvailable());
    }

    @Test
    void testEquals() {
        ItemDtoIn newItemDtoIn = new ItemDtoIn("name", "description", true, 1L);
        assertEquals(itemDtoIn, newItemDtoIn);
    }

    @Test
    void testHashCode() {
        ItemDtoIn newItemDtoIn = new ItemDtoIn("name", "description", true, 1L);
        assertEquals(itemDtoIn.hashCode(), newItemDtoIn.hashCode());
    }

    @Test
    void testToString() {
        String targetString = "ItemDtoIn(name=name, description=description, available=true, requestId=1)";
        assertEquals(targetString, itemDtoIn.toString());
    }

    @Test
    void builder() {
        ItemDtoIn newItemDtoIn = ItemDtoIn.builder()
                .name("name")
                .description("description")
                .available(true)
                .requestId(1L)
                .build();
        assertEquals(itemDtoIn, newItemDtoIn);
    }

}