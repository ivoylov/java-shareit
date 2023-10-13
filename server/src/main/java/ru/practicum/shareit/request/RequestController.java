package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.request.model.RequestDtoIn;
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
    private final String userIdHeader = "X-Sharer-User-Id";
    private final int minUserId = 1;

    @PostMapping
    RequestDtoOut created(@Validated(Create.class) @RequestBody RequestDtoIn requestDtoIn,
                          @RequestHeader(userIdHeader) @Min(minUserId) Long userId) {
        Request request = RequestMapper.toRequest(requestDtoIn);
        return RequestMapper.toRequestDtoOut(requestService.create(request, userId));
    }

    @GetMapping
    List<RequestDtoOut> getAllUserRequests(@RequestHeader(userIdHeader) @Min(minUserId) Long userId) {
        return RequestMapper.toListRequestDtoOut(requestService.getUserRequests(userId));
    }

    @GetMapping("/all")
    List<RequestDtoOut> getAllByAnotherUsers(@RequestParam(defaultValue = "0") @Min(0) Integer from,
                            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size,
                            @RequestHeader(userIdHeader) @Min(minUserId) Long userId) {
        return RequestMapper.toListRequestDtoOut(requestService.getWithPagination(from, size, userId));
    }

    @GetMapping("/{requestId}")
    RequestDtoOut get(@PathVariable Long requestId,
                      @RequestHeader(userIdHeader) @Min(minUserId) Long userId) {
        return RequestMapper.toRequestDtoOut(requestService.get(requestId, userId));
    }

}
