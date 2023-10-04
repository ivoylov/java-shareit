package ru.practicum.shareit.item.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    public static Comment toComment(CommentDtoIn commentDtoIn) {
        return Comment.builder()
                .text(commentDtoIn.getText())
                .build();
    }

    public static CommentDtoOut toCommentDtoOut(Comment comment) {
        return CommentDtoOut.builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorName(comment.getAuthor().getName())
                .created(comment.getCreatedDate())
                .build();
    }

    public static List<CommentDtoOut> toCommentDtoOutList (List<Comment> commentList) {
        if (commentList == null || commentList.isEmpty()) return Collections.emptyList();
        return commentList.stream().map(CommentMapper::toCommentDtoOut).collect(Collectors.toList());
    }

}
