package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.booking.model.*;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.Min;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingDtoOut create(@Validated(Create.class) @RequestBody BookingDtoIn bookingDtoIn,
                                @RequestHeader("X-Sharer-User-Id") @Min(1) Long bookerId) {
        log.info("{}; POST; /bookings; bookingDtoIn={}, bookerId={}", this.getClass(), bookingDtoIn, bookerId);
        Booking booking = BookingMapper.toBooking(bookingDtoIn);
        booking.getBooker().setId(bookerId);
        return BookingMapper.toBookingDtoOut(bookingService.create(booking));
    }

    @PatchMapping("/{bookingId}")
    public BookingDtoOut approved(@PathVariable @Min(1) Long bookingId, @RequestParam Boolean approved) {
        log.info("{}, PATCH; /bookings/{bookingId}; bookingId={}, approved={}", this.getClass(), bookingId, approved);
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setStatus(approved ? Status.APPROVED : Status.REJECTED);
        return BookingMapper.toBookingDtoOut(bookingService.update(booking));
    }

}
