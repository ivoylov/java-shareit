package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.entity.EntityNotFoundException;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.user.UserService;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RequestService {

    private RequestRepository requestRepository;
    private UserService userService;

    public Request create(Request request, Long userId) {
        log.info("{}, create; {}, userId={}", this.getClass(), request, userId);
        request.getUser().setId(userId);
        request.setCreatedDate(LocalDateTime.now());
        return requestRepository.save(request);
    }

    public List<Request> getUserRequests(Long userId) {
        if (!userService.isExist(userId)) throw new EntityNotFoundException("userId=" + userId);
        List<Request> requests = requestRepository.getRequestByUserId(userId);
        return requests;
    }

    public List<Request> getRequests(Integer from, Integer size, Long userId) {
        List<Request> requests;
        try {
            requests = requestRepository.findAll(PageRequest.of(from, size)).toList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
        return requests.stream()
                .filter(r->!r.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public Request getRequest(Long requestId) {
        return requestRepository.findById(requestId).orElseThrow(() -> new EntityNotFoundException("requestId="+requestId));
    }

}
