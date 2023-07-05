package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item1;
    private Item item2;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("name")
                .email("userMail@mail.ru")
                .items(new ArrayList<>())
                .build();
        item1 = new Item(1L, "name", "description", true, user);
        item2 = new Item(1L, "name", "description", true, user);
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
        assertEquals(1L, item1.getOwner().getId());
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
        item1.setOwner(user);
        assertEquals(user, item1.getOwner());
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
        String string = "Item(id=1, name=name, description=description, available=true, owner=User(id=1, name=name, email=userMail@mail.ru, items=[]))";
        assertEquals(string, item1.toString());
    }

    @Test
    void testBuilder() {
        Item itemBuilder = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(user)
                .build();
        assertEquals(itemBuilder, item1);
    }

}