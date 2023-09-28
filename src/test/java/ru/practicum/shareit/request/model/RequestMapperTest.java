package ru.practicum.shareit.request.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestMapperTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void toRequest() {
        RequestDtoIn requestDtoIn = new RequestDtoIn("какое-то описание");
        Request request = RequestMapper.toRequest(requestDtoIn);
        assertNull(request.getId());
        assertNull(request.getCreated());
        assertNull(request.getUser());
        assertEquals("какое-то описание", request.getDescription());
    }

    @Test
    void toRequestDtoOut() {
        List<Item> items = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();
        LocalDateTime created = LocalDateTime.now();
        User user = new User(1L, "name", "enail@email.ru", items, bookings);
        Request request = new Request(1L, "description", user, created);
        RequestDtoOut requestDtoOut = RequestMapper.toRequestDtoOut(request);
        assertEquals(requestDtoOut.getId(), request.getId());
        assertEquals(requestDtoOut.getDescription(), request.getDescription());
        assertEquals(requestDtoOut.getCreated(), request.getCreated());
    }

    @Test
    void toRequestDtoOutList() {
        List<RequestDtoOut> requestList = new ArrayList<>();
        assertEquals(requestList, RequestMapper.toListRequestDtoOut(new ArrayList<>()));
    }

}