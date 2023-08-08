package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemDtoTest {

    private ItemDtoIn itemDto1;
    private ItemDtoIn itemDto2;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "name", "user@mail.ru", new ArrayList<>(), new ArrayList<>());
        itemDto1 = new ItemDtoIn("name", "description", true);
        itemDto2 = new ItemDtoIn("name", "description", true);
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
    void testEquals() {
        assertEquals(itemDto1, itemDto2);
    }

    @Test
    void testHashCode() {
        assertEquals(itemDto1.hashCode(), itemDto2.hashCode());
    }

    @Test
    void testToString() {
        String string = "ItemDto(id=1, name=name, description=description, available=true, owner=User(id=1, name=name, email=user@mail.ru, items=[], bookings=[]), bookings=[])";
        assertEquals(string, itemDto1.toString());
    }

    @Test
    void builder() {
        ItemDtoIn itemDto = ItemDtoIn.builder()
                .name("name")
                .description("description")
                .available(true)
                .build();
        assertEquals(itemDto, itemDto1);
    }

}