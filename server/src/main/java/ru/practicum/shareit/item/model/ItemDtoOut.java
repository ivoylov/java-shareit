package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.model.BookingDtoOutShort;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDtoOut {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private BookingDtoOutShort lastBooking;
    private BookingDtoOutShort nextBooking;
    private List<CommentDtoOut> comments;
    private Long requestId;
}