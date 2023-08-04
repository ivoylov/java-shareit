package ru.practicum.shareit.item.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentMapper {

    public Comment toComment(CommentDtoOut commentDto) {
        return Comment.builder()
                .text(commentDto.getText())
                .build();
    }

    public CommentDtoOut toCommentDtoOut(Comment comment) {
        return CommentDtoOut.builder()
                .text(comment.getText())
                .authorName(comment.getAuthor().getName())
                .created(comment.getCreatedDate())
                .build();
    }

}
