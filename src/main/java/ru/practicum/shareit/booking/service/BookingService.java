package ru.practicum.shareit.booking.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.storage.InDbBookingStorage;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.EntityValidationException;
import ru.practicum.shareit.exception.ItemAvailableException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.practicum.shareit.booking.model.Status.WAITING;

@Service
@AllArgsConstructor
public class BookingService implements CrudOperations<BookingDto> {

    private final InDbBookingStorage bookingStorage;
    private final ItemService itemService;
    private final UserService userService;

    @Override
    public BookingDto create(BookingDto bookingDto) {

        checkCreatingBookingDto(bookingDto);

        User user = userService.get(bookingDto.getBookerId());
        Item item = itemService.get(bookingDto.getItemId());
        User owner = userService.get(item.getOwnerId());

        Booking booking = BookingDtoMapper.toBooking(bookingDto);
        booking.setOwnerId(owner.getId());
        booking.setStatus(WAITING);

        Booking createdBooking = bookingStorage.create(booking);
        return BookingDtoMapper.toBookingDto(createdBooking, item, user);

    }

    @Override
    public BookingDto update(BookingDto bookingDto) {

        Booking bookingToUpdate = bookingStorage.get(bookingDto.getId());

        checkUpdatingBooking(bookingDto, bookingToUpdate);
        bookingToUpdate.setStatus(bookingDto.getStatus());

        Booking updatedBooking = bookingStorage.update(bookingToUpdate);
        User user = userService.get(updatedBooking.getBookerId());
        Item item = itemService.get(updatedBooking.getItemId());
        return BookingDtoMapper.toBookingDto(updatedBooking, item, user);

    }

    @Override
    public Boolean isExist(Long id) {
        return null;
    }

    @Override
    public Boolean isExist(BookingDto booking) {
        return null;
    }

    @Override
    public BookingDto get(Long id) {
        Booking booking = bookingStorage.get(id);
        if (booking == null) throw new EntityNotFoundException(id);
        User user = userService.get(booking.getBookerId());
        Item item = itemService.get(booking.getItemId());
        return BookingDtoMapper.toBookingDto(booking, item, user);
    }

    @Override
    public List<BookingDto> getAll() {
        return null;
    }

    @Override
    public BookingDto delete(Long id) {
        return null;
    }

    public List<BookingDto> getAllForUser(Long userId, String state) {
        if (!userService.isExist(userId)) {
            throw new EntityNotFoundException(userId);
        }
        switch (state) {
            case "ALL":
                return toBookingDtoList(bookingStorage.getAllBookingsForUser(userId));
            case "CURRENT":
                toBookingDtoList(bookingStorage.getAllCurrentBookingsForUser(userId));
                break;
            case "PAST":
                toBookingDtoList(bookingStorage.getAllPastBookingsForUser(userId));
                break;
            case "FUTURE":
                toBookingDtoList(bookingStorage.getAllFutureBookingsForUser(userId));
                break;
            case "WAITING":
                toBookingDtoList(bookingStorage.getAllWaitingBookingsForUser(userId));
                break;
            case "REJECTED":
                toBookingDtoList(bookingStorage.getAllRejectedBookingsForUser(userId));
                break;
            default:
                throw new EntityValidationException(state, "Unknown state: UNSUPPORTED_STATUS");
        }
        return null;
    }

    public List<BookingDto> getAllForOwner(Long userId, String state) {
        if (!userService.isExist(userId)) {
            throw new EntityNotFoundException(userId);
        }
        switch (state) {
            case "ALL":
                return toBookingDtoList(bookingStorage.getAllBookingsForOwner(userId));
            case "CURRENT":
                toBookingDtoList(bookingStorage.getAllCurrentBookingsForOwner(userId));
                break;
            case "PAST":
                toBookingDtoList(bookingStorage.getAllPastBookingsForOwner(userId));
                break;
            case "FUTURE":
                toBookingDtoList(bookingStorage.getAllFutureBookingsForOwner(userId));
                break;
            case "WAITING":
                toBookingDtoList(bookingStorage.getAllWaitingBookingsForOwner(userId));
                break;
            case "REJECTED":
                toBookingDtoList(bookingStorage.getAllRejectedBookingsForOwner(userId));
                break;
            default:
                throw new EntityValidationException(state, "Unknown state: UNSUPPORTED_STATUS");
        }
        return null;
    }

    private List<BookingDto> toBookingDtoList(List<Booking> bookingsList) {
        List<BookingDto> bookingsDtoList = new ArrayList<>();
        for (Booking booking : bookingsList) {
            BookingDto bookingDto = BookingDtoMapper.toBookingDto(
                    booking,
                    itemService.get(booking.getItemId()),
                    userService.get(booking.getBookerId()));
            bookingsDtoList.add(bookingDto);
        }
        return bookingsDtoList;
    }

    private void checkCreatingBookingDto(BookingDto bookingDto) {
        if (!itemService.get(bookingDto.getItemId()).getAvailable()) {
            throw new ItemAvailableException(bookingDto);
        }
        if (!userService.isExist(bookingDto.getBookerId())) {
            throw new EntityNotFoundException(userService.get(bookingDto.getBookerId()));
        }
        if (!bookingDto.isBookingTimeValid()) {
            throw new EntityValidationException(bookingDto);
        }
    }

    private void checkUpdatingBooking(BookingDto bookingDto, Booking booking) {
        if (booking.getStatus() != WAITING) {
            throw new EntityValidationException(bookingDto);
        }
        if (bookingDto.getOwnerId().equals(booking.getBookerId())) {
            throw new EntityNotFoundException(booking);//TODO сделать новую более подходящую ошибку
        }
    }

}
