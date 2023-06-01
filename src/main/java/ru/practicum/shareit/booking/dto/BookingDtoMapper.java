package ru.practicum.shareit.booking.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.dto.ItemDtoMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class BookingDtoMapper {

    public BookingDto toBookingDto(Booking booking, Item item, User user) {
        return BookingDto.builder()
                .id(booking.getId())
                .bookerId(booking.getBookerId())
                .booker(user)
                .start(booking.getStart())
                .end(booking.getEnd())
                .itemId(booking.getItemId())
                .item(item)
                .status(booking.getStatus())
                .build();
    }

    public Booking toBooking(BookingDto bookingDto) {
        return Booking.builder()
                .id(bookingDto.getId())
                .bookerId(bookingDto.getBookerId())
                .ownerId(bookingDto.getOwnerId())
                .start(bookingDto.getStart())
                .end(bookingDto.getEnd())
                .itemId(bookingDto.getItemId())
                .status(bookingDto.getStatus())
                .build();
    }

    public List<BookingDto> toBookingDtoList(List<Booking> bookingsList, ItemService itemService, UserService userService) {
        List<BookingDto> bookingsDtoList = new ArrayList<>();
        for (Booking booking : bookingsList) {
            BookingDto bookingDto = BookingDtoMapper.toBookingDto(
                    booking,
                    ItemDtoMapper.toItem(itemService.get(booking.getItemId())),
                    userService.get(booking.getBookerId()));
            bookingsDtoList.add(bookingDto);
        }
        return bookingsDtoList;
    }

}

