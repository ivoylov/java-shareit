package ru.practicum.shareit.request.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.EntityValidationException;
import ru.practicum.shareit.item.service.ItemService;
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
    private final ItemService itemService;

    @Override
    public RequestDto create(RequestDto requestDto) {
        checkUser(requestDto.getRequestorId());
        Request request = RequestDtoMapper.toRequest(requestDto);
        request.setCreatedDate(LocalDateTime.now(Clock.systemDefaultZone()));
        log.info(RequestService.class + " create, requestDto={}", requestDto);
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.create(request), itemService);
    }

    @Override
    public RequestDto update(RequestDto requestDto) {
        log.info(RequestService.class + " update, requestDto={}", requestDto);
        Request request = RequestDtoMapper.toRequest(requestDto);
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.update(request), itemService);
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
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.get(id), itemService);
    }

    @Override
    public List<RequestDto> getAll() {
        log.info(RequestService.class + " get all");
        return RequestDtoMapper.toRequestDtoList(inDbRequestStorage.getAll(), itemService);
    }

    @Override
    public RequestDto delete(Long id) {
        log.info(RequestService.class + " delete, userId={}", id);
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.delete(id), itemService);
    }

    public RequestDto get(RequestDto requestDto) {
        checkRequestDto(requestDto);
        return RequestDtoMapper.toRequestDto(inDbRequestStorage.get(requestDto.getId()), itemService);
    }

    public List<RequestDto> getOwn(Long requestorId) {
        log.info(RequestService.class + " get own, requestorId={}", requestorId);
        checkUser(requestorId);
        return RequestDtoMapper.toRequestDtoList(inDbRequestStorage.getOwn(requestorId), itemService);
    }

    public List<RequestDto> getAll(Long requestorId, Integer from, Integer size) {
        log.info(RequestService.class + " get all");
        checkParam(requestorId, from, size);
        return RequestDtoMapper.toRequestDtoList(inDbRequestStorage.getAll(from, size), itemService);
    }

    public List<RequestDto> getAllByAnotherUser(Long requestorId, Integer from, Integer size) {
        log.info(RequestService.class + " get all by another user");
        checkParam(requestorId, from, size);
        return RequestDtoMapper.toRequestDtoList(inDbRequestStorage.getAllByAnotherUser(requestorId, from, size), itemService);
    }

    private void checkParam(Long requestorId, Integer from, Integer size) {
        log.info(RequestService.class + " check param");
        checkUser(requestorId);
        checkFrom(from);
        checkSize(size);
    }

    private void checkSize(Integer size) {
        log.info(RequestService.class + " check page size={}", size);
        if (size < MIN_SIZE) {
            throw new EntityValidationException("page size < " + MIN_SIZE);
        }
    }

    private void checkFrom(Integer from) {
        log.info(RequestService.class + " check page from={}", from);
        if (from < MIN_FROM) {
            throw new EntityValidationException("page from < " + MIN_FROM);
        }
    }

    private void checkUser(Long requestorId) {
        log.info(RequestService.class + " check userId={}", requestorId);
        if (!userService.isExist(requestorId)) {
            throw new EntityNotFoundException("не найден userId=" + requestorId);
        }
    }

    private void checkRequestId(Long requestId) {
        log.info(RequestService.class + " check requestId={}", requestId);
        if (!isExist(requestId)) {
            throw new EntityNotFoundException("не найден requestId=" + requestId);
        }
    }

    private void checkRequestDto(RequestDto requestDto) {
        log.info(RequestService.class + " check requestDto={}", requestDto);
        checkUser(requestDto.getRequestorId());
        checkRequestId(requestDto.getId());
    }

}
