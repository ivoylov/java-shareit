package ru.practicum.shareit.request.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-item-requests.
 */
@Data
@AllArgsConstructor
@Component
public class ItemRequest {
    private Long id;
    private String description;
    private String requestor;
    private LocalDateTime created;
}
