package ru.practicum.shareit.item.model;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingMapper;
import ru.practicum.shareit.user.model.UserDtoMapper;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ItemDtoMapper {

    public ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .owner(item.getOwner())
                .bookings(item.getBookings())
                .lastBooking(item.getLastBooking())
                .nextBooking(item.getNextBooking())
                .build();
    }

    public Item toItem(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .available(itemDto.getAvailable())
                .owner(itemDto.getOwner())
                .bookings(itemDto.getBookings())
                .build();
    }

    public List<ItemDto> toItemDtoList(List<Item> items) {
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            itemsDto.add(toItemDto(item));
        }
        return itemsDto;
    }

}
