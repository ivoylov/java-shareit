package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommentDtoOutTest {

    private CommentDtoOut commentDtoOut;
    private LocalDateTime createdDate;

    @BeforeEach
    void setUp() {
        createdDate = LocalDateTime.now();
        commentDtoOut = new CommentDtoOut(1L, "text", "author", createdDate);
    }

    @Test
    void getId_whenExpected1() {
        assertEquals(1L, commentDtoOut.getId());
    }

    @Test
    void getText_whenExpectedTextAsString() {
        assertEquals("text", commentDtoOut.getText());
    }

    @Test
    void getAuthorName_whenExpectedAuthorAsString() {
        assertEquals("author", commentDtoOut.getAuthorName());
    }

    @Test
    void getCreated_whenExpectedCreatedDateVariable() {
        assertEquals(createdDate, commentDtoOut.getCreated());
    }

    @Test
    void setId_whenExpected2() {
        commentDtoOut.setId(2L);
        assertEquals(2L, commentDtoOut.getId());
    }

    @Test
    void setText_whenExpectedNewTextAsString() {
        commentDtoOut.setText("newText");
        assertEquals("newText", commentDtoOut.getText());
    }

    @Test
    void setAuthorName_whenExpectedNewAuthorAsString() {
        commentDtoOut.setAuthorName("newAuthor");
        assertEquals("newAuthor", commentDtoOut.getAuthorName());
    }

    @Test
    void setCreated_whenExpectedCreatedDateVariablePlus2Hours() {
        LocalDateTime newDate = LocalDateTime.now().plusHours(1);
        commentDtoOut.setCreated(newDate);
        assertEquals(newDate, commentDtoOut.getCreated());
    }

    @Test
    void testEquals_whenExpectedEqualNewCommentDtoOut() {
        CommentDtoOut  newCommentDtoOut = new CommentDtoOut(1L, "text", "author", createdDate);
        assertEquals(newCommentDtoOut, commentDtoOut);
    }

    @Test
    void testHashCode_whenExpectedSameHashCodeWithNewCommentDtoOut() {
        CommentDtoOut  newCommentDtoOut = new CommentDtoOut(1L, "text", "author", createdDate);
        assertEquals(newCommentDtoOut.hashCode(), commentDtoOut.hashCode());
    }

    @Test
    void testToString_whenExpected() {
        String targetString = "CommentDtoOut(id=1, text=text, authorName=author, created=" + commentDtoOut.getCreated() + ")";
        assertEquals(targetString, commentDtoOut.toString());
    }

    @Test
    void testBuilder_whenExpectedEqualsWithNewCommentDtoOut() {
        CommentDtoOut newCommentDtoOut = CommentDtoOut.builder()
                .id(1L)
                .text("text")
                .authorName("author")
                .created(createdDate)
                .build();
        assertEquals(newCommentDtoOut, commentDtoOut);
    }

    @Test
    void noArgsConstructor_whenExpectedNullFields() {
        CommentDtoOut newCommentDtoOut = new CommentDtoOut();
        assertNull(newCommentDtoOut.getCreated());
        assertNull(newCommentDtoOut.getText());
        assertNull(newCommentDtoOut.getId());
        assertNull(newCommentDtoOut.getAuthorName());
    }

}