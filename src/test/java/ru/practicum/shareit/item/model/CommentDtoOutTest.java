package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommentDtoOutTest {

    private CommentDtoOut commentDtoOut;
    private LocalDateTime createdDate;

    @BeforeEach
    void setUp() {
        createdDate = LocalDateTime.now();
        commentDtoOut = new CommentDtoOut(1L, "text", "author", createdDate);
    }

    @Test
    void getId() {
        assertEquals(1L, commentDtoOut.getId());
    }

    @Test
    void getText() {
        assertEquals("text", commentDtoOut.getText());
    }

    @Test
    void getAuthorName() {
        assertEquals("author", commentDtoOut.getAuthorName());
    }

    @Test
    void getCreated() {
        assertEquals(createdDate, commentDtoOut.getCreated());
    }

    @Test
    void setId() {
        commentDtoOut.setId(2L);
        assertEquals(2L, commentDtoOut.getId());
    }

    @Test
    void setText() {
        commentDtoOut.setText("newText");
        assertEquals("newText", commentDtoOut.getText());
    }

    @Test
    void setAuthorName() {
        commentDtoOut.setAuthorName("newAuthor");
        assertEquals("newAuthor", commentDtoOut.getAuthorName());
    }

    @Test
    void setCreated() {
        LocalDateTime newDate = LocalDateTime.now().plusHours(1);
        commentDtoOut.setCreated(newDate);
        assertEquals(newDate, commentDtoOut.getCreated());
    }

    @Test
    void testEquals() {
        CommentDtoOut  newCommentDtoOut = new CommentDtoOut(1L, "text", "author", createdDate);
        assertEquals(newCommentDtoOut, commentDtoOut);
    }

    @Test
    void testHashCode() {
        CommentDtoOut  newCommentDtoOut = new CommentDtoOut(1L, "text", "author", createdDate);
        assertEquals(newCommentDtoOut.hashCode(), commentDtoOut.hashCode());
    }

    @Test
    void testToString() {
        CommentDtoOut  newCommentDtoOut = new CommentDtoOut(1L, "text", "author", createdDate);
        assertEquals(newCommentDtoOut.toString(), commentDtoOut.toString());
    }

    @Test
    void builder() {
        CommentDtoOut newCommentDtoOut = CommentDtoOut.builder()
                .id(1L)
                .text("text")
                .authorName("author")
                .created(createdDate)
                .build();
        assertEquals(newCommentDtoOut, commentDtoOut);
    }

    @Test
    void noArgsConstructor() {
        CommentDtoOut newCommentDtoOut = new CommentDtoOut();
        assertNull(newCommentDtoOut.getCreated());
        assertNull(newCommentDtoOut.getText());
        assertNull(newCommentDtoOut.getId());
        assertNull(newCommentDtoOut.getAuthorName());
    }

}