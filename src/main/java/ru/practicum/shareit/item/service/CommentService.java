package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.State;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.EntityValidationException;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.storage.InDbCommentStorage;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService implements CrudOperations<Comment> {

    private final InDbCommentStorage inDbCommentStorage;
    private final BookingService bookingService;

    @Override
    public Comment create(Comment comment) {
        checkComment(comment);
        return inDbCommentStorage.create(comment);
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

    private void checkComment(Comment comment) {
        if (bookingService.getAllForBooker(comment.getAuthorId(), State.PAST.toString()).isEmpty() ||
            bookingService.getAllForBooker(comment.getAuthorId(), State.CURRENT.toString()).isEmpty()) {
            throw new EntityValidationException(comment.getAuthorId());
        }
    }

}
