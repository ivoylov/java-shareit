package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShortItemDtoOut {
    private Long id;
    private String name;
}