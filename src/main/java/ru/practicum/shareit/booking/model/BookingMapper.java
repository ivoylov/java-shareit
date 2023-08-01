package ru.practicum.shareit.booking.model;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemDtoMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.model.UserDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BookingMapper {

    public Booking toBooking(BookingDtoIn bookingDtoIn) {
        Item item = new Item();
        User user = new User();
        item.setId(bookingDtoIn.getItemId());
        return Booking.builder()
                .item(item)
                .booker(user)
                .start(bookingDtoIn.getStart())
                .end(bookingDtoIn.getEnd())
                .build();
    }

    public BookingDtoOut toBookingDtoOut(Booking booking) {
        return BookingDtoOut.builder()
                .id(booking.getId())
                .item(ItemDtoMapper.toItemDto(booking.getItem()))
                .booker(UserDtoMapper.toUserDto(booking.getBooker()))
                .status(booking.getStatus())
                .start(booking.getStart())
                .end(booking.getEnd())
                .build();
    }

    public List<BookingDtoOut> toBookingDtoOutList (List<Booking> bookingList) {
        return bookingList.stream()
                .map(BookingMapper::toBookingDtoOut)
                .collect(Collectors.toList());
    }

}
