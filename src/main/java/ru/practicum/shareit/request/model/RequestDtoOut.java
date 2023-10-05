package ru.practicum.shareit.request.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.model.ItemDtoOutShort;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
<<<<<<<< HEAD:src/main/java/ru/practicum/shareit/request/model/RequestDtoOut.java
@AllArgsConstructor
@NoArgsConstructor
public class RequestDtoOut {
========
public class Request {
>>>>>>>> main:src/main/java/ru/practicum/shareit/request/model/Request.java
    private Long id;
    private String description;
    private LocalDateTime created;
    private List<ItemDtoOutShort> items;
}