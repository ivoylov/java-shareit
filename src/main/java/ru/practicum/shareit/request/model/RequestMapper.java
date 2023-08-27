package ru.practicum.shareit.request.model;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;
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
                .created(request.getCreated())
                .build();
    }

    public static List<RequestDtoOut> toRequestDtoOutList(List<Request> requestList) {
        return requestList.stream().map(RequestMapper::toRequestDtoOut).collect(Collectors.toList());
    }

}
