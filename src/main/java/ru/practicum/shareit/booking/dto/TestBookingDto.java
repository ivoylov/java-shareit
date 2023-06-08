package ru.practicum.shareit.booking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class TestBookingDto {
    private Long id;
    @NotNull(groups = Create.class)
    @Future(groups = Create.class)
    private String start;
    @NotNull(groups = Create.class)
    @Future(groups = Create.class)
    private String end;
    @NotNull(groups = Create.class)
    private Long itemId;
    private Long ownerId;
    private Long bookerId;

}