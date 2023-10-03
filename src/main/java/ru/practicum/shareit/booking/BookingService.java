package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.State;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.exception.booking.BookingAlreadyApprovedException;
import ru.practicum.shareit.exception.booking.BookingAvailableException;
import ru.practicum.shareit.exception.booking.BookingTimeException;
import ru.practicum.shareit.exception.entity.EntityNotFoundException;
import ru.practicum.shareit.exception.item.ItemAvailableException;
import ru.practicum.shareit.exception.item.UnsupportedItemStatusException;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.Role;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.shareit.user.model.Role.BOOKER;

@Service
@AllArgsConstructor
@Slf4j
@Validated
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ItemService itemService;
    private final UserService userService;

    public Booking create(Booking booking, Long bookerId, Long itemId) {
        log.info("{}; create; {}", this.getClass(), booking);
        booking.setItem(itemService.get(itemId, bookerId));
        booking.setBooker(userService.get(bookerId));
        check(booking);
        booking.setStatus(Status.WAITING);
        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking approved(Long ownerId, Long bookingId, Boolean approved) {
        log.info("{}; update; ownerId={}, bookingId={}, approved={}", this.getClass(), ownerId, bookingId, approved);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Не найдено бронировнаие  с номером " + bookingId));
        if (!booking.getItem().getOwner().getId().equals(ownerId)) {
            throw new EntityNotFoundException("Изменение статуса производит не владелец вещи");
        }
        Status newStatus = approved ? Status.APPROVED : Status.REJECTED;
        if (approved && booking.getStatus() == Status.APPROVED) {
            throw new BookingAlreadyApprovedException("Бронирование уже подтверждено");
        }
        booking.setStatus(newStatus);
        return booking;
    }

    public Booking get(Long bookingId, Long userId) {
        log.info("{}; get; bookingId={}, userId={}", this.getClass(), bookingId, userId);
        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Не найдено бронирование с bookingId=%d", bookingId)));
        if (!booking.getBooker().getId().equals(userId) && !booking.getItem().getOwner().getId().equals(userId)) {
            throw new EntityNotFoundException("Статус по вещи запрашивает не владелец и не пользователь");
        }
        return booking;
    }

    public List<Booking> getAll(String stateString, Long userId, Role role, Integer from, Integer size) {
        log.info("{}; getAll; state={}, userId={}, role={}", this.getClass(), stateString, userId, role);
        State state = State.valueOf(stateString.toUpperCase());
        if (state == State.UNSUPPORTED_STATUS) {
            throw new UnsupportedItemStatusException("Unknown state: UNSUPPORTED_STATUS");
        }
        userService.get(userId);
        List<Booking> bookings = new ArrayList<>();
        if (role == BOOKER) {
            bookings.addAll(bookingRepository.findAllByBookerId(userId, PageRequest.of(from, size).withSort(Sort.Direction.DESC, "id")));
            while (bookings.size() == 0 && from > -1) {
                from--;
                bookings.addAll(bookingRepository.findAllByBookerId(userId, PageRequest.of(from, size).withSort(Sort.Direction.DESC, "id")));
            }
        } else {
            List<Item> items = itemService.getOwnerItems(userId, PageRequest.of(from, size));
            for (Item item : items) {
                bookings.addAll(bookingRepository.findAllByItem(item, PageRequest.of(from,size).withSort(Sort.Direction.DESC, "id")));
            }
            Collections.sort(bookings);
        }
        switch (state) {
            case CURRENT:
                 List<Booking> newList = bookings.stream()
                         .filter(booking -> booking.getState() == State.CURRENT)
                         .collect(Collectors.toList());
                 Collections.reverse(newList);
                 return newList;
            case PAST:
                return bookings.stream()
                        .filter(booking -> booking.getState() == State.PAST)
                        .collect(Collectors.toList());
            case FUTURE:
                return bookings.stream()
                        .filter(booking -> booking.getState() == State.FUTURE)
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
