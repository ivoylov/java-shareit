package ru.practicum.shareit.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.model.UserDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BookingDtoOut {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Status status;
    private UserDto booker;
    private ItemDto item;
}
