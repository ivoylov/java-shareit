package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

@AllArgsConstructor
public class BookingDtoMapper {

    public static BookingDto toBookingDto(Booking booking, Item item, User user) {
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

    public static Booking toBooking(BookingDto bookingDto) {
        return Booking.builder()
                .id(bookingDto.getId())
                .bookerId(bookingDto.getBookerId())
                .ownerId(bookingDto.getOwner().getId())
                .start(bookingDto.getStart())
                .end(bookingDto.getEnd())
                .itemId(bookingDto.getItemId())
                .status(bookingDto.getStatus())
                .build();
    }

}

