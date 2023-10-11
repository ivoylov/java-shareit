package ru.practicum.shareit.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.model.ItemDtoOutShort;
import ru.practicum.shareit.user.model.UserDtoOutShort;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDtoOut {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Status status;
    private UserDtoOutShort booker;
    private ItemDtoOutShort item;
}
