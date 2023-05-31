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
        return commentRepository.save(comment);
    }

    @Override
    public Boolean isExist(Long id) {
        return commentRepository.findById(id).isPresent();
    }

    @Override
    public Boolean isExist(Comment comment) {
        return commentRepository.findById(comment.getId()).isPresent();
    }

    @Override
    public Comment get(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getAllForItem(Long itemId) {
        return commentRepository.findAllByItemId(itemId);
    }

    @Override
    public Comment delete(Long id) {
        Comment comment = get(id);
        commentRepository.delete(get(id));
        return comment;
    }

}
