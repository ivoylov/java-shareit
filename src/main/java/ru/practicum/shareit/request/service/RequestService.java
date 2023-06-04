package ru.practicum.shareit.request.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.EntityValidationException;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.dto.RequestDtoMapper;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.request.storage.InDbRequestStorage;
import ru.practicum.shareit.user.service.UserService;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RequestService implements CrudOperations<RequestDto> {

    private final Integer MIN_FROM = 0;
    private final Integer MIN_SIZE = 1;
    private final InDbRequestStorage inDbRequestStorage;
    private final UserService userService;

    @Override
    public RequestDto create(RequestDto requestDto) {
        checkRequestDto(requestDto);
        Request request = RequestDtoMapper.toRequest(requestDto);
        request.setCreatedDate(LocalDateTime.now(Clock.systemDefaultZone()));
        log.info(RequestService.class + " create, requestDto={}", requestDto);
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.create(request));
    }

    @Override
    public RequestDto update(RequestDto requestDto) {
        log.info(RequestService.class + " update, requestDto={}", requestDto);
        Request request = RequestDtoMapper.toRequest(requestDto);
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.update(request));
    }

    @Override
    public Boolean isExist(Long id) {
        log.info(RequestService.class + " isExist, requestId={}", id);
        return inDbRequestStorage.isExist(id);
    }

    @Override
    public Boolean isExist(RequestDto requestDto) {
        log.info(RequestService.class + " get, requestDto={}", requestDto);
        return inDbRequestStorage.isExist(RequestDtoMapper.toRequest(requestDto));
    }

    @Override
    public RequestDto get(Long id) {
        log.info(RequestService.class + " get, requestId={}", id);
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.get(id));
    }

    @Override
    public List<RequestDto> getAll() {
        log.info(RequestService.class + " get all");
        return RequestDtoMapper.toRequestDtoList(inDbRequestStorage.getAll());
    }

    @Override
    public RequestDto delete(Long id) {
        log.info(RequestService.class + " delete, userId={}", id);
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.delete(id));
    }

    public List<RequestDto> getOwn(Long requestorId) {
        log.info(RequestService.class + " get own, requestorId={}", requestorId);
        checkUser(requestorId);
        return RequestDtoMapper.toRequestDtoList(inDbRequestStorage.getOwn(requestorId));
    }

    public List<RequestDto> getAll(Long requestorId, Integer from, Integer size) {
        log.info(RequestService.class + " get all");
        checkParam(requestorId, from, size);
        return RequestDtoMapper.toRequestDtoList(inDbRequestStorage.getAll(from, size));
    }

    private void checkParam(Long requestorId, Integer from, Integer size) {
        log.info(RequestService.class + " check param");
        checkUser(requestorId);
        checkFrom(from);
        checkSize(size);
    }

    private void checkSize(Integer size) {
        log.info(RequestService.class + "check size={}", size);
        if (size < MIN_SIZE) {
            throw new EntityValidationException("size < 1");
        }
    }

    private void checkFrom(Integer from) {
        log.info(RequestService.class + "check from={}", from);
        if (from < MIN_FROM) {
            throw new EntityValidationException("size < 1");
        }
    }

    private void checkUser(Long requestorId) {
        log.info(RequestService.class + "check userId={}", requestorId);
        if (!userService.isExist(requestorId)) {
            throw new EntityNotFoundException("не найден userId=" + requestorId);
        }
    }

    private void checkRequestDto(RequestDto requestDto) {
        log.info(RequestService.class + "check requestDto={}", requestDto);
        checkUser(requestDto.getRequestorId());
    }

}
