package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.exception.entity.EntityNotFoundException;
import ru.practicum.shareit.request.model.RequestDtoIn;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.request.model.RequestDtoOut;
import ru.practicum.shareit.request.model.RequestMapper;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@Validated
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    RequestDtoOut created(@Validated(Create.class) @RequestBody RequestDtoIn requestDtoIn,
                          @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        Request request = RequestMapper.toRequest(requestDtoIn);
        return RequestMapper.toRequestDtoOut(requestService.create(request, userId));
    }

    // GET /requests — получить список СВОИХ запросов вместе с данными об ответах на них.
    @GetMapping
    List<RequestDtoOut> getAllUserRequests(@RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        return RequestMapper.toListRequestDtoOut(requestService.getUserRequests(userId));
    }

    // GET /requests/all?from={from}&size={size} — получить список запросов, созданных другими пользователями.
    @GetMapping("/all")
    List<RequestDtoOut> getAllByAnotherUsers(@RequestParam(defaultValue = "0") @Min(0) Integer from,
                            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size,
                            @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        return RequestMapper.toListRequestDtoOut(requestService.getWithPagination(from, size, userId));
    }

    // GET /requests/{requestId} получить данные об одном конкретном запросе вместе с данными об ответах на нег
    @GetMapping("/{requestId}")
    RequestDtoOut get(@PathVariable Long requestId,
                      @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        return RequestMapper.toRequestDtoOut(requestService.get(requestId, userId));
    }

}
