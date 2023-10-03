package ru.practicum.shareit.booking.model;

import ru.practicum.shareit.item.model.ItemMapper;
import ru.practicum.shareit.user.model.UserMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {

    public static BookingDtoOutShort toBookingDtoOutShort(Booking booking) {
        return booking != null ? BookingDtoOutShort.builder()
                .id(booking.getId())
                .bookerId(booking.getBooker().getId())
                .build() : null;
    }

    public static Booking toBooking(BookingDtoIn bookingDtoIn) {
        return Booking.builder()
                .start(bookingDtoIn.getStart())
                .end(bookingDtoIn.getEnd())
                .build();
    }

    public static BookingDtoOut toBookingDtoOut(Booking booking) {
        return BookingDtoOut.builder()
                .id(booking.getId())
                .status(booking.getStatus())
                .start(booking.getStart())
                .end(booking.getEnd())
                .booker(UserMapper.toShortUserDtoOut(booking.getBooker()))
                .item(ItemMapper.toItemDtoOutShort(booking.getItem()))
                .build();
    }

    public static List<BookingDtoOut> toBookingDtoOutList (List<Booking> bookingList) {
        if (bookingList == null || bookingList.size() == 0) return Collections.emptyList();
        return bookingList.stream()
                .map(BookingMapper::toBookingDtoOut)
                .collect(Collectors.toList());
    }

}
