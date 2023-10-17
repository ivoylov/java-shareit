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
        itemDtoOut = new ItemDtoOut(1L, "name", "description", true, lastBooking, nextBooking, comments, 1L);
    }

    @Test
    void getId_whenExpected1() {
        assertEquals(1L, itemDtoOut.getId());
    }

    @Test
    void getName_whenExpectedNameAsString() {
        assertEquals("name", itemDtoOut.getName());
    }

    @Test
    void getDescription_whenExpectedDescriptionAsString() {
        assertEquals("description", itemDtoOut.getDescription());
    }

    @Test
    void getAvailable_whenExpectedTrue() {
        assertTrue(itemDtoOut.getAvailable());
    }

    @Test
    void getLastBooking_whenExpectedEqualsWithLastBookingVariable() {
        assertEquals(lastBooking, itemDtoOut.getLastBooking());
    }

    @Test
    void getNextBooking_whenExpectedEqualsWithNextBookingVariable() {
        assertEquals(nextBooking, itemDtoOut.getNextBooking());
    }

    @Test
    void getComments_whenExpectedEqualsWithCommentsVariable() {
        assertEquals(comments, itemDtoOut.getComments());
    }

    @Test
    void setId_whenExpected2() {
        itemDtoOut.setId(2L);
        assertEquals(2L, itemDtoOut.getId());
    }

    @Test
    void setName_whenExpectedNewNameAsString() {
        itemDtoOut.setName("newName");
        assertEquals("newName", itemDtoOut.getName());
    }

    @Test
    void setDescription_whenExpectedNewDescriptionAsString() {
        itemDtoOut.setDescription("newDescription");
        assertEquals("newDescription", itemDtoOut.getDescription());
    }

    @Test
    void setAvailable_whenExpectedFalse() {
        itemDtoOut.setAvailable(false);
        assertFalse(itemDtoOut.getAvailable());
    }

    @Test
    void setLastBooking_whenExpectedNull() {
        itemDtoOut.setLastBooking(null);
        assertNull(itemDtoOut.getLastBooking());
    }

    @Test
    void setNextBooking_whenExpectedNull() {
        itemDtoOut.setNextBooking(null);
        assertNull(itemDtoOut.getNextBooking());
    }

    @Test
    void setComments_whenExpectedNull() {
        itemDtoOut.setComments(null);
        assertNull(itemDtoOut.getComments());
    }

    @Test
    void testEquals_whenExpectedEqualsWithNewItemDtoOut() {
        ItemDtoOut newItemDtoOut = new ItemDtoOut(1L, "name", "description", true, lastBooking, nextBooking, comments, 1L);
        assertEquals(newItemDtoOut, itemDtoOut);
    }

    @Test
    void testHashCode_whenExpectedEqualsWithNewItemDtoOut() {
        ItemDtoOut newItemDtoOut = new ItemDtoOut(1L, "name", "description", true, lastBooking, nextBooking, comments, 1L);
        assertEquals(newItemDtoOut.hashCode(), itemDtoOut.hashCode());
    }

    @Test
    void testToString_whenExpectedTargetString() {
        String targetString = "ItemDtoOut(id=1, name=name, description=description, available=true, " +
                "lastBooking=BookingDtoOutShort(id=1, bookerId=1), " +
                "nextBooking=BookingDtoOutShort(id=2, bookerId=2), " +
                "comments=[], " +
                "requestId=1)";
        assertEquals(targetString, itemDtoOut.toString());
    }

    @Test
    void testBuilder_whenExpectedEqualsWithNewItemDtoOut() {
        ItemDtoOut newItemDtoOut = ItemDtoOut.builder()
                .id(1L)
                .name("name")
                .description("description")
                .lastBooking(lastBooking)
                .nextBooking(nextBooking)
                .comments(comments)
                .available(true)
                .requestId(1L)
                .build();
        assertEquals(newItemDtoOut, itemDtoOut);
    }

    @Test
    void testNoArgsConstructor_whenExpectedEqualsWithNewItemDtoOut() {
        ItemDtoOut newItemDtoOut = new ItemDtoOut();
        assertNull(newItemDtoOut.getComments());
        assertNull(newItemDtoOut.getAvailable());
        assertNull(newItemDtoOut.getId());
        assertNull(newItemDtoOut.getLastBooking());
        assertNull(newItemDtoOut.getNextBooking());
        assertNull(newItemDtoOut.getName());
        assertNull(newItemDtoOut.getRequestId());
        assertNull(newItemDtoOut.getDescription());
    }

}