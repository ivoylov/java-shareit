package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final UserClient userClient;

    @PostMapping
    public ResponseEntity<Object> create(@Validated(Create.class) @RequestBody UserDtoIn userDtoIn) {
        User user = UserMapper.toUser(userDtoIn);
        return userClient.createUser(userDtoIn);
        //return UserMapper.toUserDtoOut(userService.create(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@Validated(Update.class) @RequestBody UserDtoIn userDtoIn, @PathVariable @Min(1) Long userId) {
        User user = UserMapper.toUser(userDtoIn);
        return userClient.updateUser(userId, userDtoIn);
        //return UserMapper.toUserDtoOut(userService.update(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable @Min(1) Long id) {
        return userClient.removeUser(id);
        //return UserMapper.toUserDtoOut(userService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object>  get(@PathVariable @Min(1) Long id) {
        return userClient.findUserById(id);
        //return UserMapper.toUserDtoOut(userService.get(id));
    }

    @GetMapping
    public ResponseEntity<Object>  getAll() {
        return userClient.findAllUsers();
        //return UserMapper.toUserDtoOutList(userService.getAll());
    }

}
