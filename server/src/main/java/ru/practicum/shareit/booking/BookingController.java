package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingDtoIn;
import ru.practicum.shareit.booking.model.BookingDtoOut;
import ru.practicum.shareit.booking.model.BookingMapper;
import ru.practicum.shareit.user.model.Role;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
@Slf4j
public class BookingController {

    private final BookingService bookingService;
    private final String userHeader = "X-Sharer-User-Id";
    private final int minBookerId = 1;
    private final int minBookingId = 1;
    private final String bookingIdPath = "/{bookingId}";

    @PostMapping
    public BookingDtoOut create(@Validated(Create.class) @RequestBody BookingDtoIn bookingDtoIn,
                                @RequestHeader(userHeader) @Min(minBookerId) Long bookerId) {
        log.info("{}; POST; /bookings; {}, bookerId={}", this.getClass(), bookingDtoIn, bookerId);
        Booking booking = BookingMapper.toBooking(bookingDtoIn);
        Long itemId = bookingDtoIn.getItemId();
        return BookingMapper.toBookingDtoOut(bookingService.create(booking, bookerId, itemId));
    }

    @PatchMapping(bookingIdPath)
    public BookingDtoOut approved(@PathVariable @Min(minBookingId) Long bookingId,
                                  @RequestParam Boolean approved,
                                  @RequestHeader(userHeader) @Min(minBookerId) Long userId) {
        log.info("{}; PATCH; /bookings/{bookingId}; userId= {}, bookingId={}, approved={}",
                this.getClass(), userId, bookingId, approved);
        return BookingMapper.toBookingDtoOut(bookingService.approved(userId, bookingId, approved));
    }

    @GetMapping(bookingIdPath)
    public BookingDtoOut get(@PathVariable @Min(minBookingId) Long bookingId,
                             @RequestHeader(userHeader) @Min(minBookerId) Long userId) {
        log.info("{}; GET; /bookings/{bookingId}; bookingId={}, userId={}", this.getClass(), bookingId, userId);
        return BookingMapper.toBookingDtoOut(bookingService.get(bookingId, userId));
    }

    @GetMapping
    public List<BookingDtoOut> getAllForBooker(@RequestParam(defaultValue = "ALL") String state,
                                               @RequestHeader(userHeader) @Min(minBookerId) Long userId,
                                               @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                               @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        log.info("{}; GET; /bookings/; bookerId={}", this.getClass(), userId);
        return BookingMapper.toBookingDtoOutList(bookingService.getAll(state, userId, Role.BOOKER, from, size));
    }

    @GetMapping("/owner")
    public List<BookingDtoOut> getAllForOwner(@RequestParam(defaultValue = "ALL") String state,
                                              @RequestHeader(userHeader) @Min(minBookerId) Long userId,
                                              @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                              @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        log.info("{}; GET; /bookings/owner; ownerId={}", this.getClass(), userId);
        return BookingMapper.toBookingDtoOutList(bookingService.getAll(state, userId, Role.OWNER, from, size));
    }

}
