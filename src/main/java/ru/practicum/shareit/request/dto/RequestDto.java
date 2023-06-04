package ru.practicum.shareit.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.item.model.Item;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {
    private Long id;
    @NotNull(groups = {Update.class, Create.class}, message = "должно быть описание")
    @NotBlank(groups = {Update.class, Create.class}, message = "описание не может быть пустым")
    private String description;
    private Long requestorId;
    private LocalDateTime created;
    private List<Item> items;
}
