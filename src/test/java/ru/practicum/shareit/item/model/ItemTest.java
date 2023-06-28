package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        item1 = new Item(1L, "name", "description", true, 1L);
        item2 = new Item(1L, "name", "description", true, 1L);
    }

    @Test
    void getId() {
        assertEquals(1L, item1.getId());
    }

    @Test
    void getName() {
        assertEquals("name", item1.getName());
    }

    @Test
    void getDescription() {
        assertEquals("description", item1.getDescription());
    }

    @Test
    void getAvailable() {
        assertTrue(item1.getAvailable());
    }

    @Test
    void getOwnerId() {
        assertEquals(1L, item1.getOwnerId());
    }

    @Test
    void setId() {
        item1.setId(2L);
        assertEquals(2L, item1.getId());
    }

    @Test
    void setName() {
        item1.setName("newName");
        assertEquals("newName", item1.getName());
    }

    @Test
    void setDescription() {
        item1.setDescription("newDescription");
        assertEquals("newDescription", item1.getDescription());
    }

    @Test
    void setAvailable() {
        item1.setAvailable(false);
        assertFalse(item1.getAvailable());
    }

    @Test
    void setOwnerId() {
        item1.setOwnerId(2L);
        assertEquals(2L, item1.getOwnerId());
    }

    @Test
    void testEquals() {
        assertEquals(item1, item2);
    }

    @Test
    void testHashCode() {
        assertEquals(item1.hashCode(), item2.hashCode());
    }

    @Test
    void testToString() {
        String string = "Item(id=1, name=name, description=description, available=true, ownerId=1)";
        assertEquals(string, item1.toString());
    }

    @Test
    void testBuilder() {
        Item itemBuilder = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .ownerId(1L)
                .build();
        assertEquals(itemBuilder, item1);
    }

}