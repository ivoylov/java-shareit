package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public static final int MAX_NAME_LENGTH = 20;
    public static final int MIN_NAME_LENGTH = 3;

    private Long id;
    @NotBlank(groups = Create.class, message = "имя должно содержать символы")
    @Size(groups = {Create.class, Update.class}, min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = "некорректная длина имени")
    private String name;
    @NotEmpty(groups = Create.class)
    @Email(groups = {Create.class, Update.class})
    private String email;

}
