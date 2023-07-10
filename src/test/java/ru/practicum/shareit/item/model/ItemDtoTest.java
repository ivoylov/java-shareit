package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItemDtoTest {

    private ItemDto itemDto1;
    private ItemDto itemDto2;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "name", "user@mail.ru", new ArrayList<>(), new ArrayList<>());
        itemDto1 = new ItemDto(1L, "name", "description", true, user);
        itemDto2 = new ItemDto(1L, "name", "description", true, user);
    }

    @Test
    void getId() {
        assertEquals(1l, itemDto1.getId());
    }

    @Test
    void getName() {
        assertEquals("name", itemDto1.getName());
    }

    @Test
    void getDescription() {
        assertEquals("description", itemDto1.getDescription());
    }

    @Test
    void getAvailable() {
        assertTrue(itemDto1.getAvailable());
    }

    @Test
    void setId() {
        itemDto1.setId(2L);
        assertEquals(2L,itemDto1.getId());
    }

    @Test
    void setName() {
        itemDto1.setName("newName");
        assertEquals("newName", itemDto1.getName());
    }

    @Test
    void setDescription() {
        itemDto1.setDescription("newDescription");
        assertEquals("newDescription", itemDto1.getDescription());
    }

    @Test
    void setAvailable() {
        itemDto1.setAvailable(false);
        assertFalse(itemDto1.getAvailable());
    }

    @Test
    void testEquals() {
        assertEquals(itemDto1, itemDto2);
    }

    @Test
    void testHashCode() {
        assertEquals(itemDto1.hashCode(), itemDto2.hashCode());
    }

    @Test
    void testToString() {
        String string = "ItemDto(id=1, name=name, description=description, available=true, owner=id=1 name=name, email = user@mail.ru)";
        assertEquals(string, itemDto1.toString());
    }

    @Test
    void builder() {
        ItemDto itemDto = ItemDto.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(user)
                .build();
        assertEquals(itemDto, itemDto1);
    }

}