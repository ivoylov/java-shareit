package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.model.UserDto;
import ru.practicum.shareit.user.model.UserDtoMapper;

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
        // TODO спихнуть всё в маппер
        User user = UserDtoMapper.toUser(userDto);
        user.setId(id);
        return UserDtoMapper.toUserDto(userService.update(user));
    }

    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable @Min(1) Long id) {
        return UserDtoMapper.toUserDto(userService.delete(id));
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable @Min(1) Long id) {
        return UserDtoMapper.toUserDto(userService.get(id));
    }

    @GetMapping
    public Collection<UserDto> getAll() {
        return UserDtoMapper.toUserDtoList(userService.getAll());
    }

}
