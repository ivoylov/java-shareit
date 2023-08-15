package ru.practicum.shareit.booking.model;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.model.ItemMapper;
import ru.practicum.shareit.user.model.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BookingMapper {

    public BookingDtoOutShort toBookingDtoOutShort(Booking booking) {
        return booking != null ? BookingDtoOutShort.builder()
                .id(booking.getId())
                .bookerId(booking.getBooker().getId())
                .build() : null;
    }

    public Booking toBooking(BookingDtoIn bookingDtoIn) {
        return Booking.builder()
                .start(bookingDtoIn.getStart())
                .end(bookingDtoIn.getEnd())
                .build();
    }

    public BookingDtoOut toBookingDtoOut(Booking booking) {
        return BookingDtoOut.builder()
                .id(booking.getId())
                .status(booking.getStatus())
                .start(booking.getStart())
                .end(booking.getEnd())
                .booker(UserMapper.toShortUserDtoOut(booking.getBooker()))
                .item(ItemMapper.toShortItemDtoMapper(booking.getItem()))
                .build();
    }

    public List<BookingDtoOut> toBookingDtoOutList (List<Booking> bookingList) {
        return bookingList.stream()
                .map(BookingMapper::toBookingDtoOut)
                .collect(Collectors.toList());
    }

}
