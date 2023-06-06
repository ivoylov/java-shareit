package ru.practicum.shareit.request.storage;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.model.Request;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class InDbRequestStorage implements RequestStorage {

    private final RequestRepository requestRepository;

    @Override
    public Request create(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Request update(Request request) {
        requestRepository.update(request.getDescription(), request.getId());
        return request;
    }

    @Override
    public Boolean isExist(Long id) {
        return requestRepository.findById(id).isPresent();
    }

    @Override
    public Boolean isExist(Request request) {
        return requestRepository.findById(request.getId()).isPresent();
    }

    @Override
    public Request get(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    @Override
    public Request delete(Long id) {
        Request request = requestRepository.getReferenceById(id);
        requestRepository.delete(request);
        return request;
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> getAllByAnotherUser(Long requestorId, Integer from, Integer size) {
        return requestRepository.findRequestsByRequestorIdIsNot(requestorId, PageRequest.of(from, size));
    }

    public List<Request> getOwn(Long requestorId) {
        return requestRepository.findRequestsByRequestorId(requestorId);
    }

    public List<Request> getAll(int from, int size) {
        return requestRepository.findAll(PageRequest.of(from, size)).getContent();
    }

}
