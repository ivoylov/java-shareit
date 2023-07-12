package ru.practicum.shareit.booking.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BookingDtoMapper {
    public Booking toBooking(BookingDto bookingDto) {
        return Booking.builder()
                .id(bookingDto.getId())
                .item(bookingDto.getItem())
                .booker(bookingDto.getBooker())
                .status(bookingDto.getStatus())
                .start(bookingDto.getStart())
                .end(bookingDto.getEnd())
                .build();
    }

    public BookingDto toBookingDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .item(booking.getItem())
                .booker(booking.getBooker())
                .status(booking.getStatus())
                .start(booking.getStart())
                .end(booking.getEnd())
                .build();
    }

}
