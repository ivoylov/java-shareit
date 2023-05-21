package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.model.Item;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BookingDto {
    private Long id;
    @NotNull(groups = Create.class)
    @Future(groups = Create.class)
    private LocalDateTime start;
    @NotNull(groups = Create.class)
    @Future(groups = Create.class)
    private LocalDateTime end;
    @NotNull(groups = Create.class)
    private Item item;
    private Long bookerId;
    private Status status;
}