package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.model.BookingDto;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    public BookingDto create(@Validated(Create.class) @RequestBody BookingDto bookingDto,
                             @RequestHeader Long bookerId) {
        return null;
    }

}
