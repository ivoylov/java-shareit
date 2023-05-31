package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.State;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.exception.EntityValidationException;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.CommentDtoMapper;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.storage.InDbCommentStorage;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService implements CrudOperations<CommentDto> {

    private final InDbCommentStorage inDbCommentStorage;
    private final BookingService bookingService;
    private final UserService userService;

    @Override
    public CommentDto create(CommentDto commentDto) {
        checkCommentDto(commentDto);
        Comment comment = CommentDtoMapper.toComment(commentDto);
        comment.setCreatedDate(LocalDateTime.now());
        return CommentDtoMapper.toCommentDto(inDbCommentStorage.create(comment), userService.get(commentDto.getAuthorId()));
    }

    @Override
    public CommentDto update(CommentDto commentDto) {
        User user = userService.get(commentDto.getAuthorId());
        return CommentDtoMapper.toCommentDto(inDbCommentStorage.update(CommentDtoMapper.toComment(commentDto)), user);
    }

    @Override
    public Boolean isExist(Long id) {
        return inDbCommentStorage.isExist(id);
    }

    @Override
    public Boolean isExist(CommentDto comment) {
        return inDbCommentStorage.isExist(CommentDtoMapper.toComment(comment));
    }

    @Override
    public CommentDto get(Long id) {
        return get(id);
    }

    @Override
    public List<CommentDto> getAll() {
        return toCommentDtoList(inDbCommentStorage.getAll());
    }

    @Override
    public CommentDto delete(Long id) {
        Comment deletedComment = inDbCommentStorage.delete(id);
        User user = userService.get(deletedComment.getAuthorId());
        return CommentDtoMapper.toCommentDto(deletedComment, user);
    }

    private void checkCommentDto(CommentDto comment) {
        if (bookingService.getAllForBooker(comment.getAuthorId(), State.PAST.toString()).isEmpty() ||
            bookingService.getAllForBooker(comment.getAuthorId(), State.CURRENT.toString()).isEmpty()) {
            throw new EntityValidationException(comment.getAuthorId());
        }
    }

    private List<CommentDto> toCommentDtoList(List<Comment> comments) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : comments) {
            User user = userService.get(comment.getAuthorId());
            commentDtoList.add(CommentDtoMapper.toCommentDto(comment, user));
        }
        return commentDtoList;
    }

}
