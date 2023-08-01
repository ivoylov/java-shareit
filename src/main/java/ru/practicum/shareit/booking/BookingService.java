package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.State;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.exception.*;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.Role;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.shareit.booking.model.State.*;
import static ru.practicum.shareit.user.model.Role.*;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ItemService itemService;
    private final UserService userService;

    public Booking create(Booking booking) {
        //TODO переделать сигнатуру функции
        log.info("{}; create; {}", this.getClass(), booking);
        booking.setItem(itemService.get(booking.getItem().getId()));
        booking.setBooker(userService.get(booking.getBooker().getId()));
        check(booking);
        booking.setStatus(Status.WAITING);
        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking update(Long ownerId, Long bookingId, Boolean approved) {
        log.info("{}; update; ownerId={}, bookingId={}, approved={}", this.getClass(), ownerId, bookingId, approved);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Не найдено бронировнаие  с номером " + bookingId));
        if (!booking.getItem().getOwner().getId().equals(ownerId)) {
            throw new RequestValidationException("Изменение статуса производит не владелец вещи");
        }
        Status newStatus = approved ? Status.APPROVED : Status.REJECTED;
        if (approved && booking.getStatus() == Status.APPROVED) {
            throw new RequestValidationException("Бронирование уже подтверждено");
        }
        booking.setStatus(newStatus);
        return booking;
    }

    public Booking get(Long bookingId, Long userId) {
        log.info("{}; get; bookingId={}, userId={}", this.getClass(), bookingId, userId);
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException(String.format("Не найдено бронирование с bookingId=%d", bookingId)));
        if (!booking.getBooker().getId().equals(userId) && !booking.getItem().getOwner().getId().equals(userId)) {
            throw new RequestValidationException("Статус по вещи запрашивает не владелец и не пользователь");
        }
        return booking;
    }

    public List<Booking> getAll(String stateString, Long userId, Role role) {
        log.info("{}; getAll; state={}, userId={}, role={}", this.getClass(), stateString, userId, role);
        State state = State.valueOf(stateString.toUpperCase());
        if (state == UNSUPPORTED_STATUS) {
            throw new EntityNotFoundException("Unknown state: UNSUPPORTED_STATUS");
        }
        userService.get(userId);
        List<Booking> bookings = new ArrayList<>();
        if (role == BOOKER) {
            bookings.addAll(bookingRepository.findAllByBookerId(userId).stream()
                    .sorted()
                    .collect(Collectors.toList()));
        } else {
            List<Item> items = itemService.getOwnerItems(userId);
            for (Item item : items) {
                bookings.addAll(item.getBookings());
            }
        }
        switch (state) {
            case CURRENT:
                return bookings.stream()
                        .filter(booking -> booking.getStart().isBefore(LocalDateTime.now()) && booking.getEnd().isAfter(LocalDateTime.now()))
                        .collect(Collectors.toList());
            case PAST:
                return bookings.stream()
                        .filter(booking -> booking.getEnd().isBefore(LocalDateTime.now()))
                        .collect(Collectors.toList());
            case FUTURE:
                return bookings.stream()
                        .filter(booking -> booking.getStart().isAfter(LocalDateTime.now()))
                        .collect(Collectors.toList());
            case WAITING:
                return bookings.stream()
                        .filter(booking -> booking.getStatus() == Status.WAITING)
                        .collect(Collectors.toList());
            case REJECTED:
                return bookings.stream()
                        .filter(booking -> booking.getStatus() == Status.REJECTED)
                        .collect(Collectors.toList());
            default:
                return bookings;
        }
    }

    public Booking delete(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Не найдено бронирование с id=%d", id)));
        bookingRepository.deleteById(id);
        return booking;
    }

    private void check(Booking booking) {
        log.info("{}; check; {}", this.getClass(), booking);
        if (!booking.getItem().getAvailable()) {
            throw new ItemAvailableException(booking.getItem());
        }
        if (!booking.isBookingTimeValid()) {
            throw new BookingTimeException(booking);
        }
        if (!booking.getItem().isAvailableOnRequestDate(booking.getStart(), booking.getEnd())) {
            throw new BookingAvailableException(booking);
        }
        if (booking.getBooker().getId().equals(booking.getItem().getOwner().getId())) {
            throw new EntityNotFoundException(booking);
        }
    }

}
