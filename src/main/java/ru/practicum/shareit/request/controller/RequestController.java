package ru.practicum.shareit.request.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.service.RequestService;
import ru.practicum.shareit.request.dto.RequestDto;

import java.util.ArrayList;
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
    public RequestDto create(@RequestBody RequestDto requestDto) {
        return requestService.create(requestDto);
    }

    @PatchMapping
    public RequestDto update(@RequestBody RequestDto requestDto) {
        return requestService.update(requestDto);
    }

    //GET /requests/{requestId} — получить данные об одном конкретном запросе вместе с данными об ответах на него в том же формате, что и в эндпоинте GET /requests.
    // Посмотреть данные об отдельном запросе может любой пользователь.
    @GetMapping
    public RequestDto getRequest(@RequestBody RequestDto requestDto) {
        return requestService.get(requestDto.getId());
    }

    //GET /requests — получить список своих запросов вместе с данными об ответах на них.
    // Для каждого запроса должны указываться описание, дата и время создания и список ответов в формате: id вещи, название, id владельца.
    // Так в дальнейшем, используя указанные id вещей, можно будет получить подробную информацию о каждой вещи.
    // Запросы должны возвращаться в отсортированном порядке от более новых к более старым.
    @GetMapping
    public List<RequestDto> getAll() {
        return requestService.getAll();
    }

    //GET /requests/all?from={from}&size={size} — получить список запросов, созданных другими пользователями.
    // С помощью этого эндпоинта пользователи смогут просматривать существующие запросы, на которые они могли бы ответить.
    // Запросы сортируются по дате создания: от более новых к более старым.
    // Результаты должны возвращаться постранично.
    // Для этого нужно передать два параметра: from — индекс первого элемента, начиная с 0, и size — количество элементов для отображения.
    @GetMapping
    public List<RequestDto> searchRequests() {
        return requestService.search();
    }

}
