package ru.practicum.shareit.item.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.user.model.User;


@UtilityClass
public class CommentDtoMapper {

    public Comment toComment(CommentDto commentDto) {
        return Comment.builder()
                .text(commentDto.getText())
                .authorId(commentDto.getAuthorId())
                .itemId(commentDto.getItemId())
                .build();
    }

    public CommentDto toCommentDto(Comment comment, User user) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorName(user.getName())
                .created(comment.getCreatedDate())
                .build();
    }

}
