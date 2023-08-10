package ru.practicum.shareit.item.model;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<CommentDtoOut> toCommentDtoOutList (List<Comment> commentList) {
        if (commentList == null || commentList.isEmpty()) return Collections.emptyList();
        return commentList.stream().map(CommentMapper::toCommentDtoOut).collect(Collectors.toList());
    }

}
