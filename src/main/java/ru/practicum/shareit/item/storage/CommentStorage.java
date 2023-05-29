package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.item.model.Comment;

import java.util.List;

public interface CommentStorage extends CrudOperations<Comment> {
    List<Comment> getAllForItem(Long id);
}
