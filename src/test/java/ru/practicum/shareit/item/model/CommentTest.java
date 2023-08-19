package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    private Comment comment;
    private User author;
    private Item item;
    private LocalDateTime createdDate;
    private String text;
    private List<Item> items;
    private List<Booking> bookings;
    private List<Comment> comments;

    @BeforeEach
    void setUp() {
        text = "text";
        items = new ArrayList<>();
        bookings = new ArrayList<>();
        comments = new ArrayList<>();
        createdDate = LocalDateTime.now();
        author = new User(1L, "name", "email@mail.ru", items, bookings);
        item = new Item(1L, "name", "description", true, author, bookings, comments);
        comment = new Comment(1L, author, item, createdDate, text);
    }

    @Test
    void getId() {
        assertEquals(1L, comment.getId());
    }

    @Test
    void getAuthor() {
        assertEquals(author, comment.getAuthor());
    }

    @Test
    void getItem() {
        assertEquals(item, comment.getItem());
    }

    @Test
    void getCreatedDate() {
        assertEquals(createdDate, comment.getCreatedDate());
    }

    @Test
    void getText() {
        assertEquals(text, comment.getText());
    }

    @Test
    void setId() {
        comment.setId(2L);
        assertEquals(2L, comment.getId());
    }

    @Test
    void setAuthor() {
        User newAuthor = new User(2L, "newName", "newEmail@mail.ru", items, bookings);
        comment.setAuthor(newAuthor);
        assertEquals(newAuthor, comment.getAuthor());
    }

    @Test
    void setItem() {
        Item newItem = new Item(2L, "newName", "newDescription", false, author, bookings, comments);
        comment.setItem(newItem);
        assertEquals(newItem, comment.getItem());
    }

    @Test
    void setCreatedDate() {
        LocalDateTime newDate = LocalDateTime.now().plusHours(2);
        comment.setCreatedDate(newDate);
        assertEquals(newDate, comment.getCreatedDate());
    }

    @Test
    void setText() {
        String newText = "newText";
        comment.setText(newText);
        assertEquals(newText, comment.getText());
    }

    @Test
    void testHashCode() {
        Comment newComment = new Comment(1L, author, item, createdDate, text);
        assertEquals(newComment.hashCode(), comment.hashCode());
    }

    @Test
    void testToString() {
        Comment newComment = new Comment(1L, author, item, createdDate, text);
        assertEquals(newComment.toString(), comment.toString());
    }

    @Test
    void builder() {
        Comment newComment = Comment.builder()
                .id(1L)
                .author(author)
                .item(item)
                .createdDate(createdDate)
                .text(text)
                .build();
        assertEquals(newComment, comment);
    }

}