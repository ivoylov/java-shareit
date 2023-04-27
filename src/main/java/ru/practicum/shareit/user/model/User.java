package ru.practicum.shareit.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * TODO Sprint add-controllers.
 */
@Data
@AllArgsConstructor
@Component
public class User {
    private Long id;
    private String name;
    private String email;
}
