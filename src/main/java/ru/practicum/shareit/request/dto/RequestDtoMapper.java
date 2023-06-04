package ru.practicum.shareit.request.dto;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.request.model.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class RequestDtoMapper {

    public RequestDto toRequestDto(Request request, ItemService itemService) {
        return RequestDto.builder()
                .id(request.getId())
                .created(request.getCreatedDate())
                .description(request.getDescription())
                .requestorId(request.getRequestorId())
                .items(itemService.getItemsForRequest(request.getId()))
                .build();
    }

    public Request toRequest(RequestDto requestDto) {
        return Request.builder()
                .id(requestDto.getId())
                .createdDate(requestDto.getCreated())
                .description(requestDto.getDescription())
                .requestorId(requestDto.getRequestorId())
                .build();
    }

    public List<RequestDto> toRequestDtoList(List<Request> requestsList, ItemService itemService) {
        if (requestsList.isEmpty()) return Collections.emptyList();
        List<RequestDto> requestsDtoList = new ArrayList<>();
        for (Request request : requestsList) {
            requestsDtoList.add(toRequestDto(request, itemService));
        }
        return requestsDtoList;
    }

}
