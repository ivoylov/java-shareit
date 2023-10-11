package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDtoOutShort {
    private Long id;
    private String name;
    private Long requestId;
    private String description;
    private Boolean available;
}