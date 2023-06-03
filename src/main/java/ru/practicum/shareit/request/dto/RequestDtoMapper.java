package ru.practicum.shareit.request.dto;

import ru.practicum.shareit.request.model.Request;

public class RequestDtoMapper {

    public static RequestDto toItemRequestDto(Request itemRequest) {
        return RequestDto.builder()
                .id(itemRequest.getId())
                .created(itemRequest.getCreated())
                .description(itemRequest.getDescription())
                .requestor(itemRequest.getRequestor())
                .build();
    }

    public static Request toItemRequest(RequestDto itemRequestDto) {
        return Request.builder()
                .id(itemRequestDto.getId())
                .created(itemRequestDto.getCreated())
                .description(itemRequestDto.getDescription())
                .requestor(itemRequestDto.getRequestor())
                .build();
    }

}
