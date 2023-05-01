package ru.practicum.shareit.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;
import javax.validation.constraints.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Component
public class User {

    public static final int MAX_NAME_LENGTH = 20;
    public static final int MIN_NAME_LENGTH = 3;

    private Long id;
    @NotNull
    @NotEmpty(message = "имя не должно быть пустым")
    @NotBlank(message = "имя должно содержать символы")
    @Length(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = "некорректная длина имени")
    private String name;
    @Email
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
