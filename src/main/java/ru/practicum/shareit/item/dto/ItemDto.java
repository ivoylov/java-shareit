package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    @NotBlank
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private Boolean available;
    @JsonIgnore
    private Long ownerId;
    private Long requestId;
}
