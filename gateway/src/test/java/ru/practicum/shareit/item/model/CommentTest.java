package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        author = new User(1L, "name", "email@mail.ru", items, bookings, new ArrayList<>());
        item = new Item(1L, "name", "description", true, author, bookings, comments, null);
        comment = new Comment(1L, author, item, createdDate, text);
    }

    @Test
    void getId_whenExpected1() {
        assertEquals(1L, comment.getId());
    }

    @Test
    void getAuthor_whenExpectedEqualsWithAuthorVariable() {
        assertEquals(author, comment.getAuthor());
    }

    @Test
    void getItem_whenExpectedEqualsWithItemVariable() {
        assertEquals(item, comment.getItem());
    }

    @Test
    void getCreatedDate_whenExpectedEqualsWithCreatedDateVariable() {
        assertEquals(createdDate, comment.getCreatedDate());
    }

    @Test
    void getText_whenExpectedEqualsWithTextVariable() {
        assertEquals(text, comment.getText());
    }

    @Test
    void setId_whenExpected2() {
        comment.setId(2L);
        assertEquals(2L, comment.getId());
    }

    @Test
    void setAuthor_whenExpectedEqualsWithNewAuthor() {
        User newAuthor = new User(2L, "newName", "newEmail@mail.ru", items, bookings, new ArrayList<>());
        comment.setAuthor(newAuthor);
        assertEquals(newAuthor, comment.getAuthor());
    }

    @Test
    void setItem_whenExpectedEqualsWithNewItem() {
        Item newItem = new Item(2L, "newName", "newDescription", false, author, bookings, comments, null);
        comment.setItem(newItem);
        assertEquals(newItem, comment.getItem());
    }

    @Test
    void setCreatedDate_whenExpectedEqualsWithCurrentDatePlus2Hours() {
        LocalDateTime newDate = LocalDateTime.now().plusHours(2);
        comment.setCreatedDate(newDate);
        assertEquals(newDate, comment.getCreatedDate());
    }

    @Test
    void setText_whenExpectedNewTextAsString() {
        String newText = "newText";
        comment.setText(newText);
        assertEquals(newText, comment.getText());
    }

    @Test
    void testHashCode_whenExpectedEqualsHashCodeWithNewComment() {
        Comment newComment = new Comment(1L, author, item, createdDate, text);
        assertEquals(newComment.hashCode(), comment.hashCode());
    }

    @Test
    void testToString_whenExpectedTargetString() {
        String targetString = "id=1, " +
                "author=id=1, name=name, email=email@mail.ru, " +
                "item=id=1, name=name, description=description, available=true, " +
                "owner=id=1, name=name, email=email@mail.ru, " +
                "createdDate=" + comment.getCreatedDate() + ", " +
                "text=text";
        assertEquals(targetString, comment.toString());
    }

    @Test
    void testBuilder_whenExpectedSameFieldsWithNewComment() {
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