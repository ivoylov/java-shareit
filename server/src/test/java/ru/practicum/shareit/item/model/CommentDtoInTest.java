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
    void getText_whenExpectedTextVariable() {
        assertEquals(text, commentDtoIn.getText());
    }

    @Test
    void setText_whenExpectedNewTextAsString() {
        String newText = "newText";
        commentDtoIn.setText(newText);
        assertEquals(newText, commentDtoIn.getText());
    }

    @Test
    void testEquals_whenExpectedObjectsEquals() {
        CommentDtoIn newCommentDtoIn = new CommentDtoIn("text");
        assertEquals(newCommentDtoIn, commentDtoIn);
    }

    @Test
    void testHashCode_whenExpectedSameHashCode() {
        CommentDtoIn newCommentDtoIn = new CommentDtoIn("text");
        assertEquals(newCommentDtoIn.hashCode(), commentDtoIn.hashCode());
    }

    @Test
    void testToString_whenExpectedCommentDtoInTextText() {
        assertEquals("CommentDtoIn(text=text)", commentDtoIn.toString());
    }

    @Test
    void testBuilder_whenExpectedCorrectFields() {
        CommentDtoIn newCommentDtoIn = CommentDtoIn.builder()
                .text(text)
                .build();
        assertEquals(newCommentDtoIn, commentDtoIn);
    }

    @Test
    void testNoArgsConstructor_whenExpectedAllNullFields() {
        CommentDtoIn newCommentDtoIn = new CommentDtoIn();
        assertNull(newCommentDtoIn.getText());
    }

}