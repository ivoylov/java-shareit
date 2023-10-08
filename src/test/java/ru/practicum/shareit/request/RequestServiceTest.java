package ru.practicum.shareit.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RequestServiceTest {

    @Mock
    private RequestRepository requestRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private RequestService requestService;
    private Request request;
    private User user;
    private List<Item> items;
    private List<Booking> bookings;
    private LocalDateTime created;


    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        bookings = new ArrayList<>();
        created = LocalDateTime.now();
        user = new User(1L, "name", "mail@email.ru", items, bookings, new ArrayList<>());
        request = new Request(1L, "description", user, created, new ArrayList<>());
    }

    @Test
    void create_thenExpectedEqualsRequestVariable() {
        Request requestToCreate = new Request(null, "description", new User(), null, new ArrayList<>());
        Mockito.when(requestRepository.save(any())).thenReturn(request);
        Mockito.when(userService.get(any())).thenReturn(user);
        assertEquals(requestService.create(requestToCreate, 1L), request);
    }

    @Test
    void getUserRequests_thenExpectedEqualsWithListOfRequest() {
        Mockito.when(requestRepository.getRequestByUserId(1L)).thenReturn(List.of(request));
        assertEquals(List.of(request), requestService.getUserRequests(1L));
    }

    @Test
    void getWithPagination_thenExpectedEmptyList() {
        Mockito.when(requestRepository.findAll(PageRequest.of(0,1))).thenReturn(Page.empty());
        assertEquals(Collections.emptyList(), requestService.getWithPagination(0, 1, 1L));
    }

    @Test
    void get_thenExpectedEqualsWithRequest() {
        Mockito.when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
        assertEquals(requestService.get(1L, 1L), request);
    }

}