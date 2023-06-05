package ru.practicum.shareit.booking.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingPageableService;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.booking.storage.BookingPageableStorage;

import javax.validation.constraints.Min;
import java.util.List;

import static ru.practicum.shareit.booking.model.Status.APPROVED;
import static ru.practicum.shareit.booking.model.Status.REJECTED;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
@Slf4j
public class BookingController {

    private final BookingPageableService bookingService;

    /*
    добавление нового запроса на бронирование
    Запрос может быть создан любым пользователем,
    а затем подтверждён владельцем вещи.
     */
    @PostMapping
    public BookingDto create(@Validated(Create.class) @RequestBody BookingDto bookingDto,
                             @RequestHeader("X-Sharer-User-Id") Long userId) {
        bookingDto.setBookerId(userId);
        log.info(BookingController.class + " POST/ " + bookingDto);
        return bookingService.create(bookingDto);
    }

    /*Подтверждение или отклонение запроса на бронирование.
    Может быть выполнено только владельцем вещи.
    Затем статус бронирования становится либо APPROVED, либо REJECTED.
     */
    @PatchMapping("/{bookingId}")
    public BookingDto approved(@RequestHeader("X-Sharer-User-Id") Long ownerId,
                               @PathVariable Long bookingId,
                               @RequestParam Boolean approved) {
        BookingDto bookingDto = BookingDto.builder()
                .id(bookingId)
                .ownerId(ownerId)
                .status(approved ? APPROVED : REJECTED)
                .build();
        log.info(BookingController.class + " PATCH/ " + bookingDto);
        return bookingService.update(bookingDto);
    }

    /*
    Получение данных о конкретном бронировании (включая его статус).
    Может быть выполнено либо автором бронирования, либо владельцем вещи,
    к которой относится бронирование.
     */
    @GetMapping("/{bookingId}")
    public BookingDto get(@RequestHeader("X-Sharer-User-Id") Long userId,
                          @PathVariable Long bookingId) {
        log.info(BookingController.class + " GET/ " + " userId=" + userId + " bookingId=" + bookingId);
        return bookingService.get(bookingId, userId);
    }

    //Получение списка всех бронирований текущего пользователя.
    @GetMapping
    public List<BookingDto> getAllForBooker(@RequestHeader("X-Sharer-User-Id") @Min(1) Long bookerId,
                                            @RequestParam(defaultValue = "ALL") String state,
                                            @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                            @RequestParam(defaultValue = "20") @Min(1) Integer size) {
        log.info(BookingController.class + " GET/ bookerId={}, state={}, from={}, size={}", bookerId, state, from, size);
        return bookingService.getAllForBooker(bookerId, state, from, size);
    }

    //Получение списка бронирований для всех вещей текущего пользователя.
    @GetMapping("/owner")
    public List<BookingDto> get(@RequestHeader("X-Sharer-User-Id") Long ownerId,
                                @RequestParam(defaultValue = "ALL") String state,
                                @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                @RequestParam(defaultValue = "20") @Min(1) Integer size) {
        log.info(BookingController.class + " GET/ " + " ownerId= " + ownerId + " state= " + state);
        return bookingService.getAllForOwner(ownerId, state, from, size);
    }

}
