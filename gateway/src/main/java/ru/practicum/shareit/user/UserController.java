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
    private final int minUserId = 1;
    private final String pathId = "/{id}";

    @PostMapping
    public ResponseEntity<Object> create(@Validated(Create.class) @RequestBody UserDtoIn userDtoIn) {
        return userClient.createUser(userDtoIn);
    }

    @PatchMapping(pathId)
    public ResponseEntity<Object> update(@Validated(Update.class) @RequestBody UserDtoIn userDtoIn, @PathVariable @Min(minUserId) Long id) {
        return userClient.updateUser(id, userDtoIn);
    }

    @DeleteMapping(pathId)
    public void delete(@PathVariable @Min(minUserId) Long id) {
        userClient.removeUser(id);
    }

    @GetMapping(pathId)
    public ResponseEntity<Object>  get(@PathVariable @Min(minUserId) Long id) {
        return userClient.findUserById(id);
    }

    @GetMapping
    public ResponseEntity<Object>  getAll() {
        return userClient.findAllUsers();
    }

}
