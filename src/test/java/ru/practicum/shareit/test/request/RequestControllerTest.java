package ru.practicum.shareit.test.request;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.request.controller.RequestController;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.request.service.RequestService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RequestControllerTest {

    @InjectMocks
    private RequestController requestController;
    @Mock
    private RequestService requestService;
    private Long id;
    private RequestDto requestDto;

    @BeforeEach
    void setUp() {
        id = 1L;
        requestDto = RequestDto.builder()
                .id(id)
                .requestorId(id)
                .description("description")
                .build();
    }

    @Test
    void create() {
        Mockito.when(requestService.create(requestDto)).thenReturn(requestDto);
        assertEquals(requestController.create(id, requestDto), requestDto);
    }

    @Test
    void update() {
        Mockito.when(requestService.update(requestDto)).thenReturn(requestDto);
        assertEquals(requestController.update(id, requestDto), requestDto);
    }

    @Test
    void get() {
        Mockito.when(requestService.get(requestDto)).thenReturn(requestDto);
        Mockito.when(requestService.get(id)).thenReturn(requestDto);
        RequestDto newRequestDto = requestController.get(id, id);
        assertEquals(newRequestDto, requestDto);
    }

    @Test
    void getOwn() {
        Mockito.when(requestService.getOwn(id)).thenReturn(new ArrayList<>(List.of(requestDto)));
        assertEquals(requestController.getOwn(id), new ArrayList<>(List.of(requestDto)));
    }

    @Test
    void getAll() {
        Mockito.when(requestService.getAllByAnotherUser(id, 1, 1)).thenReturn(new ArrayList<>(List.of(requestDto)));
        assertEquals(requestController.getAll(id, 1, 1), new ArrayList<>(List.of(requestDto)));
    }

}