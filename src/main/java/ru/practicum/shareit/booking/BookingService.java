package ru.practicum.shareit.booking;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.matcher.FilterableList;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.State;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.exception.BookingAvailableException;
import ru.practicum.shareit.exception.BookingTimeException;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.ItemAvailableException;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.Role;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.shareit.booking.model.State.*;
import static ru.practicum.shareit.booking.model.State.UNSUPPORTED_STATUS;
import static ru.practicum.shareit.user.model.Role.BOOKER;

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

    public Booking update(Booking booking) {
        //TODO переделать сигнатуру функции
        log.info("{}; update; {}", this.getClass(), booking);
        if (!bookingRepository.existsById(booking.getId())) {
            throw new EntityNotFoundException(booking);
        }
        bookingRepository.update(booking.getStatus().getId(), booking.getId());
        return bookingRepository.findById(booking.getId()).orElse(null);
    }

    public Booking get(Long id) {
        log.info("{}; get; bookingId={}", this.getClass(), id);
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Не найдено бронирование с id=%d", id)));
    }

    public List<Booking> getAll(String stateString, Long userId, Role role) {
        log.info("{}; getAll; state={}, userId={}, role={}", this.getClass(), stateString, userId, role);
        State state = State.valueOf(stateString.toUpperCase());
        if (state == UNSUPPORTED_STATUS) throw new EntityNotFoundException(String.format("Не найден статус %s", state));
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
