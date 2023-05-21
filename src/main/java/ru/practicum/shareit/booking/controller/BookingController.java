package ru.practicum.shareit.booking.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.service.BookingService;
import static ru.practicum.shareit.booking.model.Status.*;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    @PostMapping
    public BookingDto create (@Validated(Create.class) @RequestBody BookingDto bookingDto, @RequestHeader("X-Sharer-User-Id") Long userId) {
        bookingDto.setBookerId(userId);
        bookingDto.setStatus(WAITING);
        return BookingDtoMapper.toBookingDto(bookingService.create(BookingDtoMapper.toBooking(bookingDto)));
    }
}
