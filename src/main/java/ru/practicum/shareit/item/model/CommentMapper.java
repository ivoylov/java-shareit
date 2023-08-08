package ru.practicum.shareit.item.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentMapper {

    public Comment toComment(CommentDtoIn commentDtoIn) {
        return Comment.builder()
                .text(commentDtoIn.getText())
                .build();
    }

    public CommentDtoOut toCommentDtoOut(Comment comment) {
        return CommentDtoOut.builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorName(comment.getAuthor().getName())
                .created(comment.getCreatedDate())
                .build();
    }

}
