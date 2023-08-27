package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.request.model.RequestDtoOut;
import ru.practicum.shareit.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RequestService {

    private RequestRepository requestRepository;
    private UserService userService;

    public Request create(Request request, Long userId) {
        request.getUser().setId(userId);
        return requestRepository.save(request);
    }

    public List<Request> getUserRequests(Long userId) {
        return requestRepository.findAllByUser(userService.get(userId));
    }

    public List<Request> getRequests(Long userId) {
        return requestRepository.findAll().stream()
                .filter(request -> !request.getId().equals(userId))
                .collect(Collectors.toList());
    }
}
