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
    private final String userIdHeader = "X-Sharer-User-Id";
    private final int minUserId = 1;

    @PostMapping
    public ResponseEntity<Object> created(@Validated(Create.class) @RequestBody RequestDtoIn requestDtoIn,
                                   @RequestHeader(userIdHeader) @Min(minUserId) Long userId) {
        return requestClient.createRequest(requestDtoIn, userId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUserRequests(@RequestHeader(userIdHeader) @Min(minUserId) Long userId) {
        return requestClient.getAllItemRequest(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllByAnotherUsers(@RequestParam(defaultValue = "0") @Min(0) Integer from,
                            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size,
                            @RequestHeader(userIdHeader) @Min(minUserId) Long userId) {
        return requestClient.getAllRequests(userId, from, size);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> get(@PathVariable Long requestId,
                      @RequestHeader(userIdHeader) @Min(minUserId) Long userId) {
        return requestClient.getItemRequestById(userId, requestId);
    }

}
