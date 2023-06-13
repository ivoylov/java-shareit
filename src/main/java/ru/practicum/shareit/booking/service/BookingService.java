package ru.practicum.shareit.booking.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.State;
import ru.practicum.shareit.booking.storage.InDbBookingStorage;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.EntityValidationException;
import ru.practicum.shareit.exception.ItemAvailableException;
import ru.practicum.shareit.item.dto.ItemDtoMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.practicum.shareit.booking.model.Status.WAITING;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService implements CrudOperations<BookingDto> {

    private final InDbBookingStorage bookingStorage;
    private final ItemService itemService;
    private final UserService userService;

    @Override
    public BookingDto create(BookingDto bookingDto) {

        checkCreatingBookingDto(bookingDto);

        User user = userService.get(bookingDto.getBookerId());
        Item item = ItemDtoMapper.toItem(itemService.get(bookingDto.getItemId()));
        User owner = userService.get(item.getOwnerId());

        Booking booking = BookingDtoMapper.toBooking(bookingDto);
        booking.setOwnerId(owner.getId());
        booking.setStatus(WAITING);

        log.info(BookingService.class + " create " + booking);
        Booking createdBooking = bookingStorage.create(booking);
        return BookingDtoMapper.toBookingDto(createdBooking, item, user);

    }

    @Override
    public BookingDto update(BookingDto bookingDto) {

        Booking bookingToUpdate = bookingStorage.get(bookingDto.getId());

        checkUpdatingBooking(bookingDto, bookingToUpdate);
        bookingToUpdate.setStatus(bookingDto.getStatus());

        log.info(BookingService.class + " update " + bookingToUpdate);
        Booking updatedBooking = bookingStorage.update(bookingToUpdate);
        User user = userService.get(updatedBooking.getBookerId());
        Item item = ItemDtoMapper.toItem(itemService.get(updatedBooking.getItemId()));
        return BookingDtoMapper.toBookingDto(updatedBooking, item, user);

    }

    @Override
    public Boolean isExist(Long id) {
        return bookingStorage.isExist(id);
    }

    @Override
    public Boolean isExist(BookingDto bookingDto) {
        return bookingStorage.isExist(BookingDtoMapper.toBooking(bookingDto));
    }

    @Override
    public BookingDto get(Long id) {
        log.info(BookingService.class + " getBooking " + " bookingId " + id);
        Booking booking = bookingStorage.get(id);
        if (booking == null) throw new EntityNotFoundException(id);
        User user = userService.get(booking.getBookerId());
        Item item = ItemDtoMapper.toItem(itemService.get(booking.getItemId()));
        return BookingDtoMapper.toBookingDto(booking, item, user);
    }

    public BookingDto get(Long bookingId, Long userId) {
        log.info(BookingService.class + " GET booking by booker" + " userId=" + userId + " bookingId=" + bookingId);
        Booking booking = bookingStorage.get(bookingId);
        if (booking == null) {
            throw new EntityNotFoundException("bookingId=" + bookingId);
        } else {
            checkUserToGet(booking, userId);
        }
        return get(bookingId);
    }

    private void checkUserToGet(Booking booking, Long userId) {
        log.info(BookingService.class + " checkUserToGet, booking=" + booking + ", userId=" + userId);
        if (!booking.getBookerId().equals(userId) &&
            !booking.getOwnerId().equals(userId)) {
            log.info("Запрос инфо о бронировании от не пользователя и не владельца");
            throw new EntityNotFoundException("userId=" + userId);
        }
    }

    @Override
    public List<BookingDto> getAll() {
        return BookingDtoMapper.toBookingDtoList(bookingStorage.getAll(), itemService, userService);
    }

    @Override
    public BookingDto delete(Long id) {
        Booking booking = BookingDtoMapper.toBooking(get(id));
        User user = userService.get(booking.getBookerId());
        Item item = ItemDtoMapper.toItem(itemService.get(booking.getItemId()));
        return BookingDtoMapper.toBookingDto(booking, item, user);
    }

    public List<BookingDto> getAllForBooker(Long bookerId, String stateString) {
        log.info(BookingService.class + " get " + "bookerId=" + bookerId + " state=" + stateString);
        State state = State.valueOf(stateString);
        if (!userService.isExist(bookerId)) {
            throw new EntityNotFoundException(bookerId);
        }
        ArrayList<Booking> bookingsList;
        switch (state) {
            case ALL:
                log.info(BookingService.class + " get all bookings for bookerId=" + bookerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllBookingsForBooker(bookerId));
                break;
            case CURRENT:
                log.info(BookingService.class + " get all current bookings for bookerId=" + bookerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllCurrentBookingsForBooker(bookerId));
                break;
            case PAST:
                log.info(BookingService.class + " get all past bookings for bookerId=" + bookerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllPastBookingsForBooker(bookerId));
                break;
            case FUTURE:
                log.info(BookingService.class + " get all future bookings for bookerId=" + bookerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllFutureBookingsForBooker(bookerId));
                break;
            case REJECTED:
                log.info(BookingService.class + " get all rejected bookings for bookerId=" + bookerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllRejectedBookingsForBooker(bookerId));
                break;
            case WAITING:
                log.info(BookingService.class + " get all waiting bookings for bookerId=" + bookerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllWaitingBookingsForBooker(bookerId));
                break;
            default:
                throw new EntityValidationException(state, "Unknown state: UNSUPPORTED_STATUS");
        }
        LocalDateTime now = LocalDateTime.now(Clock.systemDefaultZone());
        log.info("текущее время=" + now);
        logBookingList(bookingsList);
        return BookingDtoMapper.toBookingDtoList(bookingsList, itemService, userService);
    }

    private void logBookingList(List<Booking> bookingsList) {
        if (bookingsList.isEmpty()) {
            log.info("список бронирований пуст");
        } else {
            log.info("найденные бронирования");
            for (Booking booking : bookingsList) {
                log.info(booking.toString());
            }
        }
    }

    public List<BookingDto> getAllForOwner(Long ownerId, String stateString) {
        log.info(BookingService.class + " get " + "ownerId " + ownerId + " state " + stateString);
        State state = State.valueOf(stateString);
        if (!userService.isExist(ownerId)) {
            throw new EntityNotFoundException(ownerId);
        }
        ArrayList<Booking> bookingsList;
        switch (state) {
            case ALL:
                log.info(BookingService.class + " get all bookings for ownerId=" + ownerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllBookingsForOwner(ownerId));
                break;
            case CURRENT:
                log.info(BookingService.class + " get all current bookings for ownerId=" + ownerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllCurrentBookingsForOwner(ownerId));
                break;
            case PAST:
                log.info(BookingService.class + " get all past bookings for ownerId=" + ownerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllPastBookingsForOwner(ownerId));
                break;
            case FUTURE:
                log.info(BookingService.class + " get all future bookings for ownerId=" + ownerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllFutureBookingsForOwner(ownerId));
                break;
            case REJECTED:
                log.info(BookingService.class + " get all rejected bookings for ownerId=" + ownerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllRejectedBookingsForOwner(ownerId));
                break;
            case WAITING:
                log.info(BookingService.class + " get all waiting bookings for ownerId=" + ownerId);
                bookingsList = new ArrayList<>(bookingStorage.getAllWaitingBookingsForOwner(ownerId));
                break;
            default:
                throw new EntityValidationException(state, "Unknown state: UNSUPPORTED_STATUS");
        }
        LocalDateTime now = LocalDateTime.now(Clock.systemDefaultZone());
        log.info("текущее время=" + now);
        logBookingList(bookingsList);
        return BookingDtoMapper.toBookingDtoList(bookingsList, itemService, userService);
    }

    public boolean checkCreatingBookingDto(BookingDto bookingDto) {
        Item item = ItemDtoMapper.toItem(itemService.get(bookingDto.getItemId()));
        log.info(BookingService.class + " check bookingDto " + bookingDto);
        if (!item.getAvailable()) {
            log.info("вещь недоступна");
            throw new ItemAvailableException(item);
        }
        if (!userService.isExist(bookingDto.getBookerId())) {
            log.info("пользователь не найден");
            throw new EntityNotFoundException("userId=" + userService.get(bookingDto.getBookerId()));
        }
        if (!bookingDto.isBookingTimeValid()) {
            log.info("некорректное время");
            throw new EntityValidationException(bookingDto);
        }
        if (!isItemAvailableOnRequestDate(bookingDto)) {
            log.info("вещь недоступна на указанное время");
            throw new EntityValidationException(bookingDto);
        }
        if (bookingDto.getBookerId().equals(item.getOwnerId())) {
            log.info("бронировать владельцу запрещено");
            throw new EntityNotFoundException(bookingDto);
        }
        return true;
    }

    private boolean isItemAvailableOnRequestDate(BookingDto bookingDto) {
        LocalDateTime bookingStart = bookingDto.getStart();
        LocalDateTime bookingEnd = bookingDto.getEnd();
        ArrayList<Booking> bookings = new ArrayList<>(bookingStorage.getAllFutureBookingsForItem(bookingDto.getItemId()));
        for (Booking booking : bookings) {
            if ((bookingStart.isAfter(booking.getStart()) && bookingStart.isBefore(booking.getEnd())) ||
                (bookingEnd.isAfter(booking.getStart()) && bookingEnd.isBefore(booking.getEnd()))) {
                return false;
            }
        }
        return true;
    }

    private void checkUpdatingBooking(BookingDto bookingDto, Booking booking) {
        log.info(BookingService.class + " check updatingBooking " + bookingDto);
        if (booking.getStatus() != WAITING) {
            log.info(BookingService.class + " booking.getStatus() != WAITING " + bookingDto);
            throw new EntityValidationException("ошибка при смене статуса: бронирование не находится в ожидании");
        }
        if (bookingDto.getOwnerId().equals(booking.getBookerId())) {
            log.info(BookingService.class + " bookingDto.getOwnerId().equals(booking.getBookerId() " + bookingDto);
            throw new EntityNotFoundException("ошибка при смене статуса: пользователь не является владельцем");
        }
    }

}
