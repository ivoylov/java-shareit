package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.model.UserDtoIn;
import ru.practicum.shareit.user.model.UserDtoOut;
import ru.practicum.shareit.user.model.UserMapper;

import javax.validation.constraints.Min;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDtoOut create(@Validated(Create.class) @RequestBody UserDtoIn userDto) {
        User user = UserMapper.toUser(userDto);
        return UserMapper.toUserDtoOut(userService.create(user));
    }

    @PatchMapping("/{id}")
    public UserDtoOut update(@Validated(Update.class) @RequestBody UserDtoIn userDto, @PathVariable @Min(1) Long id) {
        User user = UserMapper.toUser(userDto);
        return UserMapper.toUserDtoOut(userService.update(user, id));
    }

    @DeleteMapping("/{id}")
    public UserDtoOut delete(@PathVariable @Min(1) Long id) {
        return UserMapper.toUserDtoOut(userService.delete(id));
    }

    @GetMapping("/{id}")
    public UserDtoOut get(@PathVariable @Min(1) Long id) {
        return UserMapper.toUserDtoOut(userService.get(id));
    }

    @GetMapping
    public Collection<UserDtoOut> getAll() {
        return UserMapper.toUserDtoOutList(userService.getAll());
    }

}
