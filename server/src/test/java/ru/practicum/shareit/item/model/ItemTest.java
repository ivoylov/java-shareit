package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
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
        owner = new User(1L, "name", "mail@email.ru", items, bookings, new ArrayList<>());
        item = new Item(1L, "name", "description", true, owner, bookings, comments, null);
    }

    @Test
    void update_whenExpectedAsStringNewNameAndNewDescription() {
        Item newItem = item = new Item(1L, "newName", "newDescription", false, owner, bookings, comments, null);
        item.update(newItem);
        assertFalse(item.getAvailable());
        assertEquals("newName", item.getName());
        assertEquals("newDescription", item.getDescription());
    }

    @Test
    void isAvailableOnRequestDate_whenExpectedTrue() {
        LocalDateTime start = LocalDateTime.now().plusHours(1);
        LocalDateTime end = start.plusHours(2);
        assertTrue(item.isAvailableOnRequestDate(start, end));
    }

    @Test
    void isAvailableOnRequestDate_thenFalse() {
        LocalDateTime start = LocalDateTime.now().plusHours(1);
        LocalDateTime end = start.plusHours(2);
        User booker = new User(5L, "name", "newBooker@mail.ru", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Booking newBooking = new Booking(3L, LocalDateTime.now(), LocalDateTime.now().plusHours(5), Status.APPROVED, booker, item);
        item.setBookings(List.of(newBooking));
        assertFalse(item.isAvailableOnRequestDate(start, end));
    }

    @Test
    void getLastBooking_thenExpectedNull() {
        assertNull(item.getLastBooking());
    }

    @Test
    void getNextBooking_thenExpectedNull() {
        assertNull(item.getNextBooking());
    }

    @Test
    void compareTo_thenExpectedLessThenZero() {
        Item newItem = new Item(2L, "name", "description", true, owner, bookings, comments, null);
        assertEquals(-1, item.compareTo(newItem));
    }

    @Test
    void testToString_thenExpectedTargetString() {
        String targetString = "id=1, name=name, description=description, available=true, owner=id=1, name=name, email=mail@email.ru";
        assertEquals(targetString, item.toString());
    }

    @Test
    void getId_whenExpected1() {
        assertEquals(1L, item.getId());
    }

    @Test
    void getName_whenExpectedNameAsString() {
        assertEquals("name", item.getName());
    }

    @Test
    void getDescription_whenExpectedDescriptionAsString() {
        assertEquals("description", item.getDescription());
    }

    @Test
    void getAvailable_thenTrue() {
        assertTrue(item.getAvailable());
    }

    @Test
    void getOwner_thenExpectedEqualsWithOwnerVariable() {
        assertEquals(owner, item.getOwner());
    }

    @Test
    void getBookings_whenExpectedEmptyList() {
        assertEquals(new ArrayList<>(), item.getBookings());
    }

    @Test
    void getComments_whenExpectedEmptyList() {
        assertEquals(new ArrayList<>(), item.getComments());
    }

    @Test
    void setId_whenExpected2() {
        item.setId(2L);
        assertEquals(2L, item.getId());
    }

    @Test
    void setName_whenExpectedNewNameAsString() {
        item.setName("newName");
        assertEquals("newName", item.getName());
    }

    @Test
    void setDescription_whenExpectedNewDescriptionAsString() {
        item.setDescription("newDescription");
        assertEquals("newDescription", item.getDescription());
    }

    @Test
    void setAvailable_whenFalse() {
        item.setAvailable(false);
        assertFalse(item.getAvailable());
    }

    @Test
    void setOwner_whenExpectedEqualsWithNewOwner() {
        User newOwner = new User(2L, "newName", "newMail@email.ru", items, bookings, new ArrayList<>());
        item.setOwner(newOwner);
        assertEquals(newOwner, item.getOwner());
    }

    @Test
    void setBookings_whenExpectedNull() {
        item.setBookings(null);
        assertNull(item.getBookings());
    }

    @Test
    void setComments_whenExpectedNull() {
        item.setComments(null);
        assertNull(item.getComments());
    }

    @Test
    void testEquals_whenExpectedNull() {
        Item newItem = new Item(1L, "name", "description", true, owner, bookings, comments, null);
        assertEquals(item, newItem);
    }

    @Test
    void testHashCode_thenExpectedEqualsWithNewItem() {
        Item newItem = new Item(1L, "name", "description", true, owner, bookings, comments, null);
        assertEquals(newItem.hashCode(), item.hashCode());
    }

    @Test
    void testBuilder_thenExpectedEqualsFieldsWithNewItem() {
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