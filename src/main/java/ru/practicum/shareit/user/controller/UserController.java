package ru.practicum.shareit.user.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.userException.UserValidationException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;
import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Validated
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        if (!isValid(user)) throw new UserValidationException(user + " не прошёл валидацию");
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public User update(@Valid @RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping
    public Collection<User> getAll() {
        return userService.getAll();
    }

    private boolean isValid(User user) {
        return isEmail(user.getEmail()) && isCorrectName(user.getName());
    }

    private boolean isEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    private boolean isCorrectName(String name) {
        return name != null && !name.isBlank() && !name.isEmpty() && !name.contains(" ");
    }

}
