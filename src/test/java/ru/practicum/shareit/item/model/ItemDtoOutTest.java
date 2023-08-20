package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.BookingDtoOutShort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDtoOutTest {

    private ItemDtoOut itemDtoOut;
    private BookingDtoOutShort lastBooking;
    private BookingDtoOutShort nextBooking;
    private List<CommentDtoOut> comments;

    @BeforeEach
    void setUp() {
        lastBooking = new BookingDtoOutShort(1L, 1L);
        nextBooking = new BookingDtoOutShort(2L, 2L);
        comments = new ArrayList<>();
        itemDtoOut = new ItemDtoOut(1L, "name", "description", true, lastBooking, nextBooking, comments);
    }

    @Test
    void getId() {
        assertEquals(1L, itemDtoOut.getId());
    }

    @Test
    void getName() {
        assertEquals("name", itemDtoOut.getName());
    }

    @Test
    void getDescription() {
        assertEquals("description", itemDtoOut.getDescription());
    }

    @Test
    void getAvailable() {
        assertTrue(itemDtoOut.getAvailable());
    }

    @Test
    void getLastBooking() {
        assertEquals(lastBooking, itemDtoOut.getLastBooking());
    }

    @Test
    void getNextBooking() {
        assertEquals(nextBooking, itemDtoOut.getNextBooking());
    }

    @Test
    void getComments() {
        assertEquals(comments, itemDtoOut.getComments());
    }

    @Test
    void setId() {
        itemDtoOut.setId(2L);
        assertEquals(2L, itemDtoOut.getId());
    }

    @Test
    void setName() {
        itemDtoOut.setName("newName");
        assertEquals("newName", itemDtoOut.getName());
    }

    @Test
    void setDescription() {
        itemDtoOut.setDescription("newDescription");
        assertEquals("newDescription", itemDtoOut.getDescription());
    }

    @Test
    void setAvailable() {
        itemDtoOut.setAvailable(false);
        assertFalse(itemDtoOut.getAvailable());
    }

    @Test
    void setLastBooking() {
        itemDtoOut.setLastBooking(null);
        assertNull(itemDtoOut.getLastBooking());
    }

    @Test
    void setNextBooking() {
        itemDtoOut.setNextBooking(null);
        assertNull(itemDtoOut.getNextBooking());
    }

    @Test
    void setComments() {
        itemDtoOut.setComments(null);
        assertNull(itemDtoOut.getComments());
    }

    @Test
    void testEquals() {
        ItemDtoOut newItemDtoOut = new ItemDtoOut(1L, "name", "description", true, lastBooking, nextBooking, comments);
        assertEquals(newItemDtoOut, itemDtoOut);
    }

    @Test
    void testHashCode() {
        ItemDtoOut newItemDtoOut = new ItemDtoOut(1L, "name", "description", true, lastBooking, nextBooking, comments);
        assertEquals(newItemDtoOut.hashCode(), itemDtoOut.hashCode());
    }

    @Test
    void testToString() {
        String targetString = "ItemDtoOut(id=1, name=name, description=description, available=true, " +
                "lastBooking=BookingDtoOutShort(id=1, bookerId=1), " +
                "nextBooking=BookingDtoOutShort(id=2, bookerId=2), " +
                "comments=[])";
        assertEquals(targetString, itemDtoOut.toString());
    }

    @Test
    void builder() {
        ItemDtoOut newItemDtoOut = ItemDtoOut.builder()
                .id(1L)
                .name("name")
                .description("description")
                .lastBooking(lastBooking)
                .nextBooking(nextBooking)
                .comments(comments)
                .available(true)
                .build();
        assertEquals(newItemDtoOut, itemDtoOut);
    }

}