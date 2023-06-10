package ru.practicum.shareit.test.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.shareit.booking.controller.BookingController;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingPageableService;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;
    @Mock
    private BookingPageableService bookingService;
    private BookingDto bookingDto;
    private List<BookingDto> bookingDtoList;
    private Long id;

    @BeforeEach
    void setUp() {

        LocalDateTime start = LocalDateTime.now(Clock.systemDefaultZone()).plusHours(1);
        LocalDateTime end = LocalDateTime.now(Clock.systemDefaultZone()).plusHours(2);
        id = 1L;

        bookingDto = BookingDto.builder()
                .itemId(id)
                .start(start)
                .end(end)
                .bookerId(id)
                .build();
        bookingDtoList = new ArrayList<>();
        bookingDtoList.add(bookingDto);

    }

    @Test
    void create() {
        when(bookingService.create(any())).thenReturn(bookingDto);
        assertEquals(bookingController.create(bookingDto, id), bookingDto);
    }

    @Test
    void approved() {
        when(bookingService.update(any())).thenReturn(bookingDto);
        assertEquals(bookingController.approved(id,id,true), bookingDto);
    }

    @Test
    void get() {
        when(bookingService.get(id,id)).thenReturn(bookingDto);
        assertEquals(bookingController.get(id,id), bookingDto);
    }

    @Test
    void getAllForBooker() {
        when(bookingService.getAllForBooker(id, "FUTURE", 1, 1)).thenReturn(bookingDtoList);
        assertEquals(bookingController.getAllForBooker(id,"FUTURE",1,1), bookingDtoList);
    }

    @Test
    void getAllForOwner() {
        when(bookingService.getAllForOwner(id,"FUTURE", 1, 1)).thenReturn(bookingDtoList);
        assertEquals(bookingController.get(id,"FUTURE",1,1), bookingDtoList);
    }

}