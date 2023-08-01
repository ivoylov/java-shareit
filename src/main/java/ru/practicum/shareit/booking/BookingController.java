package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.booking.model.*;
import ru.practicum.shareit.user.model.Role;

import javax.validation.constraints.Min;
import java.util.List;

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
    public BookingDtoOut approved(@PathVariable @Min(1) Long bookingId,
                                  @RequestParam Boolean approved,
                                  @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        log.info("{}; PATCH; /bookings/{bookingId}; userId= {}, bookingId={}, approved={}",
                this.getClass(), userId, bookingId, approved);
        return BookingMapper.toBookingDtoOut(bookingService.update(userId, bookingId, approved));
    }

    @GetMapping("/{bookingId}")
    public BookingDtoOut get(@PathVariable @Min(1) Long bookingId,
                             @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        log.info("{}; GET; /bookings/{bookingId}; bookingId={}, userId={}", this.getClass(), bookingId, userId);
        return BookingMapper.toBookingDtoOut(bookingService.get(bookingId, userId));
    }

    @GetMapping()
    public List<BookingDtoOut> getAllForBooker(@RequestParam(defaultValue = "ALL") String state,
                                               @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        log.info("{}; GET; /bookings/; bookerId={}", this.getClass(), userId);
        return BookingMapper.toBookingDtoOutList(bookingService.getAll(state, userId, Role.BOOKER));
    }

    @GetMapping("/owner")
    public List<BookingDtoOut> getAllForOwner(@RequestParam(defaultValue = "ALL") String state,
                                              @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        log.info("{}; GET; /bookings/owner; ownerId={}", this.getClass(), userId);
        return BookingMapper.toBookingDtoOutList(bookingService.getAll(state, userId, Role.OWNER));
    }

}
