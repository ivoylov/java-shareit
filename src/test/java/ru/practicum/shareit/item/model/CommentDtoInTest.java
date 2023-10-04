package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommentDtoInTest {

    private CommentDtoIn commentDtoIn;
    private String text;

    @BeforeEach
    void setUp() {
        text = "text";
        commentDtoIn = new CommentDtoIn("text");
    }

    @Test
    void getText() {
        assertEquals(text, commentDtoIn.getText());
    }

    @Test
    void setText() {
        String newText = "newText";
        commentDtoIn.setText(newText);
        assertEquals(newText, commentDtoIn.getText());
    }

    @Test
    void testEquals() {
        CommentDtoIn newCommentDtoIn = new CommentDtoIn("text");
        assertEquals(newCommentDtoIn, commentDtoIn);
    }

    @Test
    void testHashCode() {
        CommentDtoIn newCommentDtoIn = new CommentDtoIn("text");
        assertEquals(newCommentDtoIn.hashCode(), commentDtoIn.hashCode());
    }

    @Test
    void testToString() {
        CommentDtoIn newCommentDtoIn = new CommentDtoIn("text");
        assertEquals(newCommentDtoIn.toString(), commentDtoIn.toString());
    }

    @Test
    void builder() {
        CommentDtoIn newCommentDtoIn = CommentDtoIn.builder()
                .text(text)
                .build();
        assertEquals(newCommentDtoIn, commentDtoIn);
    }

    @Test
    void noArgsConstructor() {
        CommentDtoIn newCommentDtoIn = new CommentDtoIn();
        assertNull(newCommentDtoIn.getText());
    }

}