package ru.practicum.shareit.request;

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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RequestControllerTest {

    @InjectMocks
    private RequestController requestController;
    @Mock
    private RequestService requestService;

    @Test
    void created() {
        LocalDateTime created = LocalDateTime.now();
        RequestDtoIn requestDtoIn = new RequestDtoIn("description");
        RequestDtoOut requestDtoOut = new RequestDtoOut(1L, "description", created, new ArrayList<>());
        Request request = new Request(1L, "description", new User(), created, new ArrayList<>());
        Mockito.when(requestService.create(RequestMapper.toRequest(requestDtoIn), 1L)).thenReturn(request);
        assertEquals(requestController.created(requestDtoIn, 1L), requestDtoOut);
    }


}