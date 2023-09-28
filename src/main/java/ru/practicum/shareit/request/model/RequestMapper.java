package ru.practicum.shareit.request.model;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.model.ItemMapper;
import ru.practicum.shareit.user.model.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RequestMapper {

    public static Request toRequest(RequestDtoIn requestDtoIn) {
        return Request.builder()
                .description(requestDtoIn.getDescription())
                .user(new User())
                .build();
    }

    public static RequestDtoOut toRequestDtoOut(Request request) {
        return RequestDtoOut.builder()
                .id(request.getId())
                .description(request.getDescription())
                .created(request.getCreatedDate())
                .items(ItemMapper.toListItemDtoOutShort(request.getItems()))
                .build();
    }

    public static List<RequestDtoOut> toListRequestDtoOut(List<Request> requestList) {
        if (requestList == null || requestList.size() == 0) return Collections.emptyList();
        return requestList.stream()
                .map(RequestMapper::toRequestDtoOut)
                .collect(Collectors.toList());
    }

}
