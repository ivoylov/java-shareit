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
public class BookingDto {
    private Long id;
    @NotNull(groups = Create.class)
    @Future(groups = Create.class)
    private LocalDateTime start;
    @NotNull(groups = Create.class)
    @Future(groups = Create.class)
    private LocalDateTime end;
    @NotNull(groups = Create.class)
    private Long itemId;
    private Item item;
    @JsonIgnore
    private Long ownerId;
    @JsonIgnore
    private User owner;
    private Long bookerId;
    private User booker;
    private Status status;

    private boolean isEndBeforeStart() {
        return end.isBefore(start);
    }

    private boolean isStartEqualEnd() {
        return start.equals(end);
    }

    public boolean isBookingTimeValid() {
        if (isEndBeforeStart()) return false;
        if (isStartEqualEnd()) return false;
        return true;
    }

}