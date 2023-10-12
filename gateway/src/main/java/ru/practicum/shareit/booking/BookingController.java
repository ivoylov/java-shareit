package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
@Slf4j
public class BookingController {

    private final BookingClient bookingClient;

    @PostMapping
    public ResponseEntity<Object> create(@Validated(Create.class) @RequestBody BookingDtoIn bookingDtoIn,
                                         @RequestHeader("X-Sharer-User-Id") @Min(1) Long bookerId) {
        log.info("{}; POST; /bookings; {}, bookerId={}", this.getClass(), bookingDtoIn, bookerId);
        return bookingClient.createBooking(bookingDtoIn, bookerId);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> approved(@PathVariable @Min(1) Long bookingId,
                                  @RequestParam Boolean approved,
                                  @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        log.info("{}; PATCH; /bookings/{bookingId}; userId= {}, bookingId={}, approved={}",
                this.getClass(), userId, bookingId, approved);
        return bookingClient.updateBookingStatus(bookingId, approved, userId);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> get(@PathVariable @Min(1) Long bookingId,
                             @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        log.info("{}; GET; /bookings/{bookingId}; bookingId={}, userId={}", this.getClass(), bookingId, userId);
        return bookingClient.getBookingDetails(bookingId, userId);
    }

    @GetMapping
    public ResponseEntity<Object>  getAllForBooker(@RequestParam(defaultValue = "ALL") String state,
                                               @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId,
                                               @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                               @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        log.info("{}; GET; /bookings/; bookerId={}", this.getClass(), userId);
        return bookingClient.findBookingUsers(state, userId, from, size);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getAllForOwner(@RequestParam(defaultValue = "ALL") String state,
                                              @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId,
                                              @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                              @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        log.info("{}; GET; /bookings/owner; ownerId={}", this.getClass(), userId);
        return bookingClient.getOwnerBookings(userId, state, from, size);
    }

}
