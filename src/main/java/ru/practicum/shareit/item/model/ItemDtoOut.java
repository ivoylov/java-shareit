package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.booking.model.ShortBookingDtoOut;

import java.util.List;

@Builder
@Data
public class ItemDtoOut {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private ShortBookingDtoOut lastBooking;
    private ShortBookingDtoOut nextBooking;
    private List<CommentDtoOut> comments;
}