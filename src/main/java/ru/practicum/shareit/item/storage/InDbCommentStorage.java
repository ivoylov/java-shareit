package ru.practicum.shareit.item.storage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Comment;

import java.util.List;

@Component
@AllArgsConstructor
public class InDbCommentStorage implements CommentStorage {

    private final CommentRepository commentRepository;

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        return null;
    }

    @Override
    public Boolean isExist(Long id) {
        return null;
    }

    @Override
    public Boolean isExist(Comment comment) {
        return null;
    }

    @Override
    public Comment get(Long id) {
        return null;
    }

    @Override
    public List<Comment> getAll() {
        return null;
    }

    @Override
    public Comment delete(Long id) {
        return null;
    }

}
