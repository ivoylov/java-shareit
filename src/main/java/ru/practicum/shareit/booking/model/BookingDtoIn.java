package ru.practicum.shareit.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BookingDtoIn {
    @NotNull(message = "Должен быть указан id вещи для бронирования")
    private Long itemId;
    @NotNull(groups = Create.class, message = "Бронимарование должно содержать время начала")
    @Future(groups = Create.class, message = "Время начала бронирования должно быть в будущем")
    private LocalDateTime start;
    @NotNull(groups = Create.class, message = "бронирование должно содердать время конца")
    @Future(groups = Create.class, message = "Время конца бронирования должно быть в будущем")
    private LocalDateTime end;
}

