package ru.practicum.shareit.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {

    public static final int MAX_NAME_LENGTH = 20;
    public static final int MIN_NAME_LENGTH = 3;

    private Long id;
    private String name;
    private String email;

}
