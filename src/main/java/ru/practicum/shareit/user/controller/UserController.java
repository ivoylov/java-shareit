package ru.practicum.shareit.user.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.userException.UserValidationException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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
        if (!isValid(user)) throw new UserValidationException();
        return userService.create(user);
    }

    @PatchMapping("/{id}")
    public User update(@Valid @RequestBody User user, @PathVariable @Min(1) Long id) {
        if (!isValid(user)) throw new UserValidationException();
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable @Min(1) Long id) {
        return userService.delete(id);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable @Min(1) Long id) {
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
        if (email == null) return false;
        return email.contains("@") && email.contains(".");
    }

    private boolean isCorrectName(String name) {
        if (name == null) return false;
        return !name.isBlank() && !name.isEmpty() && !name.contains(" ");
    }

}
