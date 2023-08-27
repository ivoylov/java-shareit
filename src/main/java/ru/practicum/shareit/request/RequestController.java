package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.model.RequestDtoIn;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.request.model.RequestDtoOut;
import ru.practicum.shareit.request.model.RequestMapper;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/requests")
@Slf4j
public class RequestController {

    private RequestService requestService;

    @PostMapping
    RequestDtoOut created(@NotNull RequestDtoIn requestDtoIn,
                          @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        Request request = RequestMapper.toRequest(requestDtoIn);
        return RequestMapper.toRequestDtoOut(requestService.create(request, userId));
    }


    // GET /requests — получить список СВОИХ запросов вместе с данными об ответах на них.
    @GetMapping
    List<RequestDtoOut> get(@RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        return RequestMapper.toRequestDtoOutList(requestService.getUserRequests(userId));
    }


    // GET /requests/all?from={from}&size={size} — получить список запросов, созданных другими пользователями.
    @GetMapping("/all")
    List<RequestDtoOut> get(@RequestParam(defaultValue = "10") Long from,
                            @RequestParam(defaultValue = "10") Long size,
                            @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        return RequestMapper.toRequestDtoOutList(requestService.getRequests(userId));
    }


    // GET /requests/{requestId} получить данные об одном конкретном запросе вместе с данными об ответах на нег


}
