package ru.practicum.shareit.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BookingDto {
    private Long id;
    @NotNull(message = "Бронимарование должно содержать время начала")
    private LocalDateTime start;
    @NotNull(message = "бронирование должно содердать время конца")
    private LocalDateTime end;
    private Status status;
    private User booker;
    private Item item;
}
