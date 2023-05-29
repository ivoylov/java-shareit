package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {
    private static final int MIN_COMMENT_LENGTH = 1;
    private static final int MAX_COMMENT_LENGTH = 255;
    private long id;
    @NotBlank(groups = {Create.class, Update.class}, message = "комментарий должен содержать символы")
    @Size(groups = {Create.class, Update.class}, min = MIN_COMMENT_LENGTH, max = MAX_COMMENT_LENGTH, message = "некорректная длина комментария")
    private String text;
    private LocalDateTime created;
    @JsonIgnore
    private Long authorId;
    private String authorName;
    @JsonIgnore
    private Long itemId;
}
