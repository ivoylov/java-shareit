package ru.practicum.shareit.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class RequestDto {
    @NotNull (groups = {Update.class, Create.class}, message = "описание не может быть пустым")
    @NotBlank (groups = {Update.class, Create.class}, message = "должно быть описание")
    private String description;
    @NotNull (groups = {Create.class, Update.class}, message = "Необходим id пользователя")
    private String requestor;
    private LocalDateTime created;
}
