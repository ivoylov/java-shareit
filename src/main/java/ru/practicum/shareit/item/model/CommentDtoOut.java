package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
<<<<<<<< HEAD:src/main/java/ru/practicum/shareit/item/model/CommentDtoOut.java
@NoArgsConstructor
@AllArgsConstructor
public class CommentDtoOut {
========
public class RequestDto {
>>>>>>>> main:src/main/java/ru/practicum/shareit/item/model/RequestDto.java
    private Long id;
    private String text;
    private String authorName;
    private LocalDateTime created;
}
