package ru.practicum.shareit.request.storage;

import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.request.model.Request;

import java.util.List;

public interface RequestStorage extends CrudOperations<Request> {
    List<Request> getAllByAnotherUser(Long requestorId, Integer from, Integer size);
}
