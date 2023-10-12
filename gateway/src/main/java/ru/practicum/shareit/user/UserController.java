package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserClient userClient;

    @PostMapping
    public ResponseEntity<Object> create(@Validated(Create.class) @RequestBody UserDtoIn userDtoIn) {
        return userClient.createUser(userDtoIn);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@Validated(Update.class) @RequestBody UserDtoIn userDtoIn, @PathVariable @Min(1) Long userId) {
        return userClient.updateUser(userId, userDtoIn);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) Long id) {
        userClient.removeUser(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object>  get(@PathVariable @Min(1) Long id) {
        return userClient.findUserById(id);
    }

    @GetMapping
    public ResponseEntity<Object>  getAll() {
        return userClient.findAllUsers();
    }

}
