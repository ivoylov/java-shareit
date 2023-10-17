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
    void getId_whenExpected1() {
        assertEquals(1L, itemDtoOutShort.getId());
    }

    @Test
    void getName_whenExpectedNameAsString() {
        assertEquals("name", itemDtoOutShort.getName());
    }

    @Test
    void setId_whenExpected2() {
        itemDtoOutShort.setId(2L);
        assertEquals(2L, itemDtoOutShort.getId());
    }

    @Test
    void setName_whenExpectedNewNameAsString() {
        itemDtoOutShort.setName("newName");
        assertEquals("newName", itemDtoOutShort.getName());
    }

    @Test
    void testEquals_whenExpectedEqualsWithNEwItemDtoOutShort() {
        ItemDtoOutShort newItemDtoOutShort = new ItemDtoOutShort(1L, "name", 1L, "description", true);
        assertEquals(newItemDtoOutShort, itemDtoOutShort);
    }

    @Test
    void testHashCode_whenExpectedSameCodeWithNewItemDtoOutShort() {
        ItemDtoOutShort newItemDtoOutShort = new ItemDtoOutShort(1L, "name", 1L, "description", true);
        assertEquals(newItemDtoOutShort.hashCode(), itemDtoOutShort.hashCode());
    }

    @Test
    void testToString_whenExpectedTargetString() {
        String targetString = "ItemDtoOutShort(id=1, name=name, requestId=1, description=description, available=true)";
        assertEquals(targetString, itemDtoOutShort.toString());
    }

    @Test
    void testBuilder_whenExpectedEqualsFieldsWithNewItemDtoOutShort() {
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
    void noArgsConstructor_whenExpectedAllNullFields() {
        ItemDtoOutShort newItemDtoOutShort = new ItemDtoOutShort();
        assertNull(newItemDtoOutShort.getName());
        assertNull(newItemDtoOutShort.getAvailable());
        assertNull(newItemDtoOutShort.getDescription());
        assertNull(newItemDtoOutShort.getId());
        assertNull(newItemDtoOutShort.getRequestId());
    }

}