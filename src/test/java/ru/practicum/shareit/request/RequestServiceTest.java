package ru.practicum.shareit.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.request.model.RequestDtoIn;
import ru.practicum.shareit.user.model.User;

import javax.security.auth.message.callback.SecretKeyCallback;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RequestServiceTest {

    @Mock
    private RequestRepository requestRepository;
    @InjectMocks
    private RequestService requestService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() {
        List<Item> items = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();
        LocalDateTime created = LocalDateTime.now();
        User user = new User(1L, "name", "mail@email.ru", items, bookings);
        Request request = new Request(1L, "description", user, created);
        Request requestToCreate = new Request(null, "description", new User(), null);
        Mockito.when(requestRepository.save(any())).thenReturn(request);
        assertEquals(requestService.create(requestToCreate, 1L), request);
    }

}