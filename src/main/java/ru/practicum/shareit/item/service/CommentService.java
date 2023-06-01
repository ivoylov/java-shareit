package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.State;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.exception.EntityValidationException;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.CommentDtoMapper;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.storage.InDbCommentStorage;
import ru.practicum.shareit.user.service.UserService;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommentService {

    private final InDbCommentStorage inDbCommentStorage;
    private final BookingService bookingService;
    private final UserService userService;

    public CommentDto create(CommentDto commentDto) {
        checkCommentDto(commentDto);
        Comment comment = CommentDtoMapper.toComment(commentDto);
        comment.setCreatedDate(LocalDateTime.now());
        return CommentDtoMapper.toCommentDto(inDbCommentStorage.create(comment), userService.get(commentDto.getAuthorId()));
    }

    private void checkCommentDto(CommentDto comment) {
        if (bookingService.getAllForBooker(comment.getAuthorId(), State.PAST.toString()).isEmpty() ||
            bookingService.getAllForBooker(comment.getAuthorId(), State.CURRENT.toString()).isEmpty()) {
            throw new EntityValidationException(comment.getAuthorId());
        }
    }

}
