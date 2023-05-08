package ru.practicum.shareit.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
//@Component
@Builder
public class ItemRequestDto {
    private Long id;
    private String description;
    private String requestor;
    private LocalDateTime created;
}
