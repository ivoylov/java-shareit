package ru.practicum.shareit.item.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentMapperTest {

    private Comment comment;
    private String text;
    CommentDtoOut commentDtoOut;
    User author;
    Item item;
    LocalDateTime created;

    @BeforeEach
    void setUp() {
        text = "text";
        created = LocalDateTime.now();
        commentDtoOut = new CommentDtoOut(1L, text, "author", created);
        author = new User(1L, "author", "author@mail.ru", new ArrayList<>(), new ArrayList<>());
        item = new Item(1L, "itemName", "description", true, author, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void toComment() {
        CommentDtoIn commentDtoIn = new CommentDtoIn(text);
        comment = new Comment(null, null, null, null, text);
        assertEquals(comment, CommentMapper.toComment(commentDtoIn));
    }

    @Test
    void toCommentDtoOut() {
        comment = new Comment(1L, author, item, created, text);
        assertEquals(commentDtoOut, CommentMapper.toCommentDtoOut(comment));
    }

    @Test
    void toCommentDtoOutList() {
        comment = new Comment(1L, author, item, created, text);
        assertEquals(List.of(commentDtoOut), CommentMapper.toCommentDtoOutList(List.of(comment)));
    }
}