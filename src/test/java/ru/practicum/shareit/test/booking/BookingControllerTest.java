package ru.practicum.shareit.test.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.shareit.booking.controller.BookingController;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.TestBookingDto;
import ru.practicum.shareit.booking.service.BookingPageableService;
import ru.practicum.shareit.booking.service.BookingService;

import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;
    @Mock
    private BookingPageableService bookingService;
    private MockMvc mvc;
    private BookingDto bookingDto;
    private Long userId;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {

        mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

        LocalDateTime start = LocalDateTime.now(Clock.systemDefaultZone()).plusHours(1);
        LocalDateTime end = LocalDateTime.now(Clock.systemDefaultZone()).plusHours(2);

        userId = 1L;

        mvc = MockMvcBuilders
                .standaloneSetup(bookingController)
                .build();

        bookingDto = BookingDto.builder()
                .itemId(1L)
                .start(start)
                .end(end)
                .bookerId(userId)
                .build();

    }

    @Test
    void create() throws Exception {

        when(bookingService.create(any()))
                .thenReturn(bookingDto);

        mvc.perform(MockMvcRequestBuilders.post("/bookings")
                        .content(mapper.writeValueAsString(bookingDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Sharer-User-Id", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemId", is(bookingDto.getId()), Long.class))
                .andExpect(jsonPath("$.bookerId", is(bookingDto.getBookerId())))
                .andExpect(jsonPath("$.start", is(bookingDto.getStart())))
                .andExpect(jsonPath("$.end", is(bookingDto.getEnd())));

    }

    @Test
    void approved() {
    }

    @Test
    void get() {
    }

    @Test
    void getAllForBooker() {
    }

    @Test
    void testGet() {
    }
}