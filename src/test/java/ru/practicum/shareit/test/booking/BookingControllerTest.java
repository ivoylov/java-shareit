package ru.practicum.shareit.test.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.shareit.booking.controller.BookingController;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.TestBookingDto;
import ru.practicum.shareit.booking.service.BookingService;

import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;
    @Mock
    private BookingService bookingService;
    private MockMvc mvc;
    private BookingDto bookingDto;
    private TestBookingDto testBookingDto;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        LocalDateTime start = LocalDateTime.now(Clock.systemDefaultZone());
        LocalDateTime end = LocalDateTime.now(Clock.systemDefaultZone()).plusHours(1);

        String startString = start.toString();
        String endString = end.toString();

        Long bookerId = 1L;
        Long itemId = 1L;

        mvc = MockMvcBuilders
                .standaloneSetup(bookingController)
                .build();

        bookingDto = BookingDto.builder()
                .itemId(1L)
                .start(start)
                .end(end)
                .bookerId(1L)
                .build();

        testBookingDto = TestBookingDto.builder()
                .itemId(itemId)
                .start(startString)
                .end(endString)
                .bookerId(bookerId)
                .build();

    }

    @Test
    void create() throws Exception {

        Mockito
                .when(bookingService.create(any()))
                .thenReturn(bookingDto);

        mvc.perform(post("/bookings")
                        .content(mapper.writeValueAsString(testBookingDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.id").value("1"));
        //.andExpect(jsonPath("$.id", is(userDto.getId()), Long.class))
          //      .andExpect(jsonPath("$.firstName", is(userDto.getFirstName())))
            //    .andExpect(jsonPath("$.lastName", is(userDto.getLastName())))
             //   .andExpect(jsonPath("$.email", is(userDto.getEmail())));

                /*
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.version").value("0.1"))
                .andDo(MockMvcResultHandlers.print());
                */

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