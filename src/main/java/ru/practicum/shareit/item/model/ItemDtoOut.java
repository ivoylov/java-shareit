package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.booking.model.BookingDtoOutShort;

import java.util.List;

@Builder
@Data
public class ItemDtoOut {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private BookingDtoOutShort lastBooking;
    private BookingDtoOutShort nextBooking;
    private List<CommentDtoOut> comments;
}