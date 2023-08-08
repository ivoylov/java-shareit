package ru.practicum.shareit.booking.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.item.model.ShortItemDtoOut;
import ru.practicum.shareit.user.model.ShortUserDtoOut;

import java.time.LocalDateTime;

@Builder
@Data
public class BookingDtoOut {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Status status;
    private ShortUserDtoOut booker;
    private ShortItemDtoOut item;
}
