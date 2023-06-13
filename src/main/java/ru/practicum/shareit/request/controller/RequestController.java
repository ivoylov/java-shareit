package ru.practicum.shareit.request.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.service.RequestService;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@AllArgsConstructor
@Slf4j
public class RequestController {

    private final RequestService requestService;

    //POST /requests — добавить новый запрос вещи.
    // Основная часть запроса — текст запроса, где пользователь описывает, какая именно вещь ему нужна.
    @PostMapping
    public RequestDto create(@RequestHeader("X-Sharer-User-Id") Long requestorId,
                             @Validated(Create.class) @RequestBody RequestDto requestDto) {
        requestDto.setRequestorId(requestorId);
        log.info(RequestController.class + " POST/ requestDto={}", requestDto);
        return requestService.create(requestDto);
    }

    @PatchMapping
    public RequestDto update(@RequestHeader("X-Sharer-User-Id") Long requestorId,
                             @Validated(Update.class) @RequestBody RequestDto requestDto) {
        requestDto.setRequestorId(requestorId);
        requestDto.setId(requestorId);
        log.info(RequestController.class + " UPDATE/ requestDto={}", requestDto);
        return requestService.update(requestDto);
    }

    //GET /requests/{requestId} — получить данные об одном конкретном запросе вместе с данными об ответах на него в том же формате, что и в эндпоинте GET /requests.
    // Посмотреть данные об отдельном запросе может любой пользователь.
    @GetMapping("/{requestId}")
    public RequestDto get(@RequestHeader("X-Sharer-User-Id") Long requestorId,
                          @PathVariable Long requestId) {
        RequestDto requestDto = new RequestDto();
        requestDto.setRequestorId(requestorId);
        requestDto.setId(requestId);
        log.info(RequestController.class + " get/ requestDto={}", requestDto);
        return requestService.get(requestDto);
    }

    //GET /requests — получить список своих запросов вместе с данными об ответах на них.
    // Для каждого запроса должны указываться описание, дата и время создания и список ответов в формате: id вещи, название, id владельца.
    // Так в дальнейшем, используя указанные id вещей, можно будет получить подробную информацию о каждой вещи.
    // Запросы должны возвращаться в отсортированном порядке от более новых к более старым.
    @GetMapping
    public List<RequestDto> getOwn(@RequestHeader("X-Sharer-User-Id") Long requestorId) {
        log.info(RequestController.class + " getOwn/ requestorId={}", requestorId);
        return requestService.getOwn(requestorId);
    }

    //GET /requests/all?from={from}&size={size} — получить список запросов, созданных другими пользователями.
    // С помощью этого эндпоинта пользователи смогут просматривать существующие запросы, на которые они могли бы ответить.
    // Запросы сортируются по дате создания: от более новых к более старым.
    // Результаты должны возвращаться постранично.
    // Для этого нужно передать два параметра: from — индекс первого элемента, начиная с 0, и size — количество элементов для отображения.
    @GetMapping("/all")
    public List<RequestDto> getAll(@RequestHeader("X-Sharer-User-Id") @Min(1) Long requestorId,
                                   @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                   @RequestParam(defaultValue = "20") @Min(1) Integer size) {
        log.info(RequestController.class + " all/ requestorId={}, from={}, size={}", requestorId, from, size);
        //return requestService.getAll(requestorId, from, size);
        return requestService.getAllByAnotherUser(requestorId, from, size);
    }

}
