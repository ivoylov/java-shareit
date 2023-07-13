package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingDto;
import ru.practicum.shareit.booking.model.BookingDtoMapper;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.Min;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingDto create(@Validated(Create.class) @RequestBody BookingDto bookingDto,
                             @RequestHeader("X-Sharer-User-Id") @Min(1) Long bookerId) {
        log.info("{}; POST; /bookings; bookingDto={}, bookerId={}", this.getClass(), bookingDto, bookerId);
        Booking booking = BookingDtoMapper.toBooking(bookingDto);
        booking.setBooker(new User());
        booking.getBooker().setId(bookerId);
        return BookingDtoMapper.toBookingDto(bookingService.create(booking));
    }

    ///{bookingId}?approved={approved}
    @PatchMapping("/{bookingId}")
    public BookingDto approved(@PathVariable @Min(1) Long bookingId, @RequestParam Boolean approved) {
        log.info("{}, PATCH; /bookings/{bookingId}; bookingId={}, approved={}", this.getClass(), bookingId, approved);
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setStatus(approved ? Status.APPROVED : Status.REJECTED);
        return BookingDtoMapper.toBookingDto(bookingService.update(booking));
    }

}
