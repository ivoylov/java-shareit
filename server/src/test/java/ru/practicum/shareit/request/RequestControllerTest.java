package ru.practicum.shareit.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.request.model.RequestDtoIn;
import ru.practicum.shareit.request.model.RequestDtoOut;
import ru.practicum.shareit.request.model.RequestMapper;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RequestControllerTest {

    @InjectMocks
    private RequestController requestController;
    @Mock
    private RequestService requestService;
    private Request request;
    private RequestDtoIn requestDtoIn;
    private RequestDtoOut requestDtoOut;

    @BeforeEach
    void setUp() {
        LocalDateTime created = LocalDateTime.now();
        requestDtoIn = new RequestDtoIn("description");
        requestDtoOut = new RequestDtoOut(1L, "description", created, new ArrayList<>());
        request = new Request(1L, "description", new User(), created, new ArrayList<>());
    }

    @Test
    void created_whenExpectedEqualsWithRequestDtoOut() {
        Mockito.when(requestService.create(RequestMapper.toRequest(requestDtoIn), 1L)).thenReturn(request);
        assertEquals(requestController.created(requestDtoIn, 1L), requestDtoOut);
    }

    @Test
    void getAllUserRequests_whenExpectedEqualsWithListOfRequestDtoOut() {
        Mockito.when(requestService.getUserRequests(any())).thenReturn(List.of(request));
        assertEquals(requestController.getAllUserRequests(1L), List.of(requestDtoOut));
    }

    @Test
    void getAllByAnotherUsers_whenExpectedEqualsWithListOfRequestDtoOut() {
        Mockito.when(requestService.getWithPagination(any(), any(), any())).thenReturn(List.of(request));
        assertEquals(requestController.getAllByAnotherUsers(0,1,1L), List.of(requestDtoOut));
    }

    @Test
    void get_whenExpectedRequestDtoOut() {
        Mockito.when(requestService.get(any(), any())).thenReturn(request);
        assertEquals(requestController.get(1L, 1L), requestDtoOut);
    }

}