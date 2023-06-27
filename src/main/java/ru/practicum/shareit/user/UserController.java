package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.user.model.UserDto;
import ru.practicum.shareit.user.model.UserDtoMapper;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.Min;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto create(@Validated(Create.class) @RequestBody UserDto userDto) {
        User user = UserDtoMapper.toUser(userDto);
        return UserDtoMapper.toUserDto(userService.create(user));
    }

    @PatchMapping("/{id}")
    public UserDto update(@Validated(Update.class) @RequestBody UserDto userDto, @PathVariable @Min(1) Long id) {
        User user = UserDtoMapper.toUser(userDto);
        user.setId(id);
        return UserDtoMapper.toUserDto(userService.update(user));
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

}
