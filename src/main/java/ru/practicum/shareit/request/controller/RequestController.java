package ru.practicum.shareit.request.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.model.Request;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@AllArgsConstructor
@Slf4j
public class RequestController {

    //POST /requests — добавить новый запрос вещи.
    // Основная часть запроса — текст запроса, где пользователь описывает, какая именно вещь ему нужна.
    @PostMapping
    public Request create(@RequestBody Request request) {
        return request;
    }

    //GET /requests — получить список своих запросов вместе с данными об ответах на них.
    // Для каждого запроса должны указываться описание, дата и время создания и список ответов в формате: id вещи, название, id владельца.
    // Так в дальнейшем, используя указанные id вещей, можно будет получить подробную информацию о каждой вещи.
    // Запросы должны возвращаться в отсортированном порядке от более новых к более старым.
    @GetMapping
    public List<Request> getAll() {
        return new ArrayList<>();
    }

    //GET /requests/all?from={from}&size={size} — получить список запросов, созданных другими пользователями.
    // С помощью этого эндпоинта пользователи смогут просматривать существующие запросы, на которые они могли бы ответить.
    // Запросы сортируются по дате создания: от более новых к более старым.
    // Результаты должны возвращаться постранично.
    // Для этого нужно передать два параметра: from — индекс первого элемента, начиная с 0, и size — количество элементов для отображения.
    @GetMapping
    public List<Request> searchRequests() {
        return new ArrayList<>();
    }

    //GET /requests/{requestId} — получить данные об одном конкретном запросе вместе с данными об ответах на него в том же формате, что и в эндпоинте GET /requests.
    // Посмотреть данные об отдельном запросе может любой пользователь.
    @GetMapping
    public Request request(@RequestBody Request request) {
        return request;
    }

}
