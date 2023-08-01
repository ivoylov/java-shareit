package ru.practicum.shareit.item.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentDtoMapper {

    public Comment toComment(CommentDto commentDto) {
        return Comment.builder()
                .text(commentDto.getText())
                .build();
    }

}
