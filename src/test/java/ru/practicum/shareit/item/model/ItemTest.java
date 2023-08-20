package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item;
    private User owner;
    private List<Booking> bookings;
    private List<Comment> comments;
    private List<Item> items;

    @BeforeEach
    void setUp() {
        bookings = new ArrayList<>();
        comments = new ArrayList<>();
        items = new ArrayList<>();
        owner = new User(1L, "name", "mail@email.ru", items, bookings);
        item = new Item(1L, "name", "description", true, owner, bookings, comments);
    }

    @Test
    void update() {
        Item newItem = item = new Item(1L, "newName", "newDescription", false, owner, bookings, comments);
        item.update(newItem);
        assertFalse(item.getAvailable());
        assertEquals("newName", item.getName());
        assertEquals("newDescription", item.getDescription());
    }

    @Test
    void isAvailableOnRequestDate() {
        LocalDateTime start = LocalDateTime.now().plusHours(1);
        LocalDateTime end = start.plusHours(2);
        assertTrue(item.isAvailableOnRequestDate(start, end));
    }

    @Test
    void getLastBooking() {
        assertNull(item.getLastBooking());
    }

    @Test
    void getNextBooking() {
        assertNull(item.getNextBooking());
    }

    @Test
    void compareTo() {
        Item newItem = new Item(2L, "name", "description", true, owner, bookings, comments);
        assertEquals(-1, item.compareTo(newItem));
    }

    @Test
    void testToString() {
        String targetString = "id=1, name=name, description=description, available=true, owner=id=1, name=name, email=mail@email.ru";
        assertEquals(targetString, item.toString());
    }

    @Test
    void getId() {
        assertEquals(1L, item.getId());
    }

    @Test
    void getName() {
        assertEquals("name", item.getName());
    }

    @Test
    void getDescription() {
        assertEquals("description", item.getDescription());
    }

    @Test
    void getAvailable() {
        assertTrue(item.getAvailable());
    }

    @Test
    void getOwner() {
        assertEquals(owner, item.getOwner());
    }

    @Test
    void getBookings() {
        assertEquals(new ArrayList<>(), item.getBookings());
    }

    @Test
    void getComments() {
        assertEquals(new ArrayList<>(), item.getComments());
    }

    @Test
    void setId() {
        item.setId(2L);
        assertEquals(2L, item.getId());
    }

    @Test
    void setName() {
        item.setName("newName");
        assertEquals("newName", item.getName());
    }

    @Test
    void setDescription() {
        item.setDescription("newDescription");
        assertEquals("newDescription", item.getDescription());
    }

    @Test
    void setAvailable() {
        item.setAvailable(false);
        assertFalse(item.getAvailable());
    }

    @Test
    void setOwner() {
        User newOwner = new User(2L, "newName", "newMail@email.ru", items, bookings);
        item.setOwner(newOwner);
        assertEquals(newOwner, item.getOwner());
    }

    @Test
    void setBookings() {
        item.setBookings(null);
        assertNull(item.getBookings());
    }

    @Test
    void setComments() {
        item.setComments(null);
        assertNull(item.getComments());
    }

    @Test
    void testEquals() {
        Item newItem = new Item(1L, "name", "description", true, owner, bookings, comments);
        assertEquals(item, newItem);
    }

    @Test
    void testHashCode() {
        Item newItem = new Item(1L, "name", "description", true, owner, bookings, comments);
        assertEquals(newItem.hashCode(), item.hashCode());
    }

    @Test
    void builder() {
        Item newItem = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .bookings(new ArrayList<>())
                .comments(new ArrayList<>())
                .owner(owner)
                .build();
        assertEquals(newItem, item);
    }

}