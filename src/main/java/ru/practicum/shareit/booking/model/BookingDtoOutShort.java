package ru.practicum.shareit.booking.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookingDtoOutShort {
    private Long id;
    private Long bookerId;
}