package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ItemDtoOutShortTest {

    private ItemDtoOutShort itemDtoOutShort;

    @BeforeEach
    void setUp() {
        itemDtoOutShort = new ItemDtoOutShort(1L, "name", 1L, "description", true);
    }

    @Test
    void getId() {
        assertEquals(1L, itemDtoOutShort.getId());
    }

    @Test
    void getName() {
        assertEquals("name", itemDtoOutShort.getName());
    }

    @Test
    void setId() {
        itemDtoOutShort.setId(2L);
        assertEquals(2L, itemDtoOutShort.getId());
    }

    @Test
    void setName() {
        itemDtoOutShort.setName("newName");
        assertEquals("newName", itemDtoOutShort.getName());
    }

    @Test
    void testEquals() {
        ItemDtoOutShort newItemDtoOutShort = new ItemDtoOutShort(1L, "name", 1L, "description", true);
        assertEquals(newItemDtoOutShort, itemDtoOutShort);
    }

    @Test
    void testHashCode() {
        ItemDtoOutShort newItemDtoOutShort = new ItemDtoOutShort(1L, "name", 1L, "description", true);
        assertEquals(newItemDtoOutShort.hashCode(), itemDtoOutShort.hashCode());
    }

    @Test
    void testToString() {
        String targetString = "ItemDtoOutShort(id=1, name=name, requestId=1, description=description, available=true)";
        assertEquals(targetString, itemDtoOutShort.toString());
    }

    @Test
    void builder() {
        ItemDtoOutShort newItemDtoOutShort = ItemDtoOutShort.builder()
                .id(1L)
                .name("name")
                .description("description")
                .requestId(1L)
                .available(true)
                .build();
        assertEquals(newItemDtoOutShort, itemDtoOutShort);
    }

    @Test
    void noArgsConstructor() {
        ItemDtoOutShort newItemDtoOutShort = new ItemDtoOutShort();
        assertNull(newItemDtoOutShort.getName());
        assertNull(newItemDtoOutShort.getAvailable());
        assertNull(newItemDtoOutShort.getDescription());
        assertNull(newItemDtoOutShort.getId());
        assertNull(newItemDtoOutShort.getRequestId());
    }

}