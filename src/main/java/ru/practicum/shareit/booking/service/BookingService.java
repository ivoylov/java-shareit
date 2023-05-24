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
import java.util.Objects;

import static ru.practicum.shareit.booking.model.Status.APPROVED;
import static ru.practicum.shareit.booking.model.Status.WAITING;

@Service
@AllArgsConstructor
public class BookingService implements CrudOperations<BookingDto> {

    private final InDbBookingStorage bookingStorage;
    private final ItemService itemService;
    private final UserService userService;

    @Override
    public BookingDto create(BookingDto bookingDto) {
        if (!itemService.get(bookingDto.getItemId()).getAvailable()) throw new ItemAvailableException(bookingDto);
        if (!userService.isExist(bookingDto.getBookerId())) throw new EntityNotFoundException(userService.get(bookingDto.getBookerId()));
        if (!bookingDto.isBookingTimeValid()) throw new EntityValidationException(bookingDto);
        Booking booking = bookingStorage.getByItemId(bookingDto.getItemId());
        if (booking != null && booking.getStatus() == APPROVED) throw new EntityValidationException(bookingDto);
        User user = userService.get(bookingDto.getBookerId());
        Item item = itemService.get(bookingDto.getItemId());
        User owner = userService.get(item.getOwnerId());
        bookingDto.setOwner(owner);
        Booking createBooking = bookingStorage.create(BookingDtoMapper.toBooking(bookingDto));
        return BookingDtoMapper.toBookingDto(createBooking, item, user);
    }

    @Override
    public BookingDto update(BookingDto booking) {
        return null;
    }

    @Override
    public Boolean isExist(Long id) {
        return null;
    }

    @Override
    public Boolean isExist(BookingDto booking) {
        return null;
    }

    public BookingDto getForBooker(Long id, Long bookerId) {
        Booking booking = bookingStorage.getForBooker(id, bookerId);
        if (booking == null) throw new EntityNotFoundException(id);
        User user = userService.get(booking.getBookerId());
        Item item = itemService.get(booking.getItemId());
        return BookingDtoMapper.toBookingDto(booking, item, user);
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

    public BookingDto updateBooking(Long bookerId, Long bookingId, boolean approved) {
        Booking booking = bookingStorage.get(bookingId);
        if (booking.getStatus() == APPROVED) throw new EntityValidationException(booking);
        if (Objects.equals(booking.getBookerId(), bookerId)) throw new EntityNotFoundException(booking);
        Integer statusId = approved ? APPROVED.getId() : WAITING.getId();
        bookingStorage.updateBooking(bookingId, statusId);
        Booking updatedBooking = bookingStorage.get(bookingId);
        User user = userService.get(updatedBooking.getBookerId());
        Item item = itemService.get(updatedBooking.getItemId());
        return BookingDtoMapper.toBookingDto(updatedBooking, item, user);
    }

    public List<BookingDto> getAllForUser(Long bookersId, String state) {
        if (state.equals("UNSUPPORTED_STATUS")) throw new EntityValidationException(state, "Unknown state: UNSUPPORTED_STATUS");
        if (!userService.isExist(bookersId)) throw new EntityNotFoundException(userService.get(bookersId));
        List<Booking> bookingsList = bookingStorage.getAllForBooker(bookersId, state);
        List<BookingDto> bookingDtoList = new ArrayList<>();
        for (Booking booking : bookingsList) {
            BookingDto bookingDto = BookingDtoMapper.toBookingDto(
                    booking,
                    itemService.get(booking.getItemId()),
                    userService.get(booking.getBookerId()));
            bookingDtoList.add(bookingDto);
        }
        return bookingDtoList;
    }

    public List<BookingDto> getAllForOwner(Long ownerId, String state) {
        if (state.equals("UNSUPPORTED_STATUS")) throw new EntityValidationException(state, "Unknown state: UNSUPPORTED_STATUS");
        if (!userService.isExist(ownerId)) throw new EntityNotFoundException(userService.get(ownerId));
        List<Booking> bookingsList = bookingStorage.getAllForOwner(ownerId, state);
        List<BookingDto> bookingDtoList = new ArrayList<>();
        for (Booking booking : bookingsList) {
            BookingDto bookingDto = BookingDtoMapper.toBookingDto(
                    booking,
                    itemService.get(booking.getItemId()),
                    userService.get(booking.getBookerId()));
            bookingDtoList.add(bookingDto);
        }
        return bookingDtoList;
    }

}
