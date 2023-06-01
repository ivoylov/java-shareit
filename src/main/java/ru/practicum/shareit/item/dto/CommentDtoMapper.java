package ru.practicum.shareit.item.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

    public List<CommentDto> toCommentDtoList(List<Comment> commentList, UserService userService) {
        if (commentList.isEmpty()) return Collections.emptyList();
        ArrayList<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentDtoList.add(CommentDtoMapper.toCommentDto(comment, userService.get(comment.getAuthorId())));
        }
        return commentDtoList;
    }

}
