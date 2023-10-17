package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.Create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDtoIn {
    @NotNull(groups = Create.class, message = "В комментарии должен быть текст")
    @NotBlank(groups = Create.class, message = "Текст комментария не может быть пустым")
    private String text;
}
