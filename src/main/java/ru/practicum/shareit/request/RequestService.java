package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.entity.EntityNotFoundException;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.user.UserService;

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
        request.setUser(userService.get(userId));
        request.setCreatedDate(LocalDateTime.now());
        return requestRepository.save(request);
    }

    public List<Request> getUserRequests(Long userId) {
        userService.get(userId);
        return requestRepository.getRequestByUserId(userId);
    }

    public List<Request> getWithPagination(Integer from, Integer size, Long userId) {
        List<Request> requests;
        try {
            requests = requestRepository.findAll(PageRequest.of(from, size)).toList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
        return requests.stream()
                .filter(r -> !r.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public Request get(Long requestId, Long userId) {
        userService.get(userId);
        return requestRepository.findById(requestId).orElseThrow(() -> new EntityNotFoundException("requestId=" + requestId));
    }

}
