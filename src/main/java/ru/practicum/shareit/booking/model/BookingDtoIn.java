package ru.practicum.shareit.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BookingDtoIn {
    private Long itemId;
    @NotNull(groups = Create.class, message = "Бронимарование должно содержать время начала")
    private LocalDateTime start;
    @NotNull(groups = Create.class, message = "бронирование должно содердать время конца")
    private LocalDateTime end;
}

