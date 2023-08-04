package ru.practicum.shareit.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class UserDtoOut {
    private Long id;
    private String name;
    private String email;
}
