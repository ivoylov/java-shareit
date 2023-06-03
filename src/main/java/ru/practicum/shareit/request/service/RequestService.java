package ru.practicum.shareit.request.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.dto.RequestDtoMapper;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.request.storage.InDbRequestStorage;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RequestService implements CrudOperations<RequestDto> {

    private final InDbRequestStorage inDbRequestStorage;

    @Override
    public RequestDto create(RequestDto requestDto) {
        Request request = RequestDtoMapper.toRequest(requestDto);
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.create(request));
    }

    @Override
    public RequestDto update(RequestDto requestDto) {
        Request request = RequestDtoMapper.toRequest(requestDto);
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.update(request));
    }

    @Override
    public Boolean isExist(Long id) {
        return inDbRequestStorage.isExist(id);
    }

    @Override
    public Boolean isExist(RequestDto requestDto) {
        return inDbRequestStorage.isExist(RequestDtoMapper.toRequest(requestDto));
    }

    @Override
    public RequestDto get(Long id) {
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.get(id));
    }

    @Override
    public List<RequestDto> getAll() {
        return RequestDtoMapper.toRequestDtoList(inDbRequestStorage.getAll());
    }

    @Override
    public RequestDto delete(Long id) {
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.delete(id));
    }

    public List<RequestDto> search() {
        return RequestDtoMapper.toRequestDtoList(inDbRequestStorage.search());
    }

}
