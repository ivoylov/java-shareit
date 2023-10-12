package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@AllArgsConstructor
@Slf4j
@Validated
@RequestMapping("/requests")
public class RequestController {

    private final RequestClient requestClient;

    @PostMapping
    public ResponseEntity<Object> created(@Validated(Create.class) @RequestBody RequestDtoIn requestDtoIn,
                                   @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        return requestClient.createRequest(requestDtoIn, userId);
    }

    // GET /requests — получить список СВОИХ запросов вместе с данными об ответах на них.
    @GetMapping
    public ResponseEntity<Object> getAllUserRequests(@RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        return requestClient.getAllItemRequest(userId);
    }

    // GET /requests/all?from={from}&size={size} — получить список запросов, созданных другими пользователями.
    @GetMapping("/all")
    public ResponseEntity<Object> getAllByAnotherUsers(@RequestParam(defaultValue = "0") @Min(0) Integer from,
                            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size,
                            @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        return requestClient.getAllRequests(userId, from, size);
    }

    // GET /requests/{requestId} получить данные об одном конкретном запросе вместе с данными об ответах на нег
    @GetMapping("/{requestId}")
    public ResponseEntity<Object> get(@PathVariable Long requestId,
                      @RequestHeader("X-Sharer-User-Id") @Min(1) Long userId) {
        return requestClient.getItemRequestById(userId, requestId);
    }

}
