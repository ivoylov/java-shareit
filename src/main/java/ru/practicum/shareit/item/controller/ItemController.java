package ru.practicum.shareit.item.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.itemException.ItemValidationException;
import ru.practicum.shareit.exception.userException.UserNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
@Validated
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final UserService userService;

    @PostMapping
    public Item create(@Valid @RequestBody Item item, HttpServletRequest request) {
        try {
            Long ownerId = Long.parseLong(request.getHeader("X-Sharer-User-Id"));
            if (!userService.isExist(ownerId)) throw new UserNotFoundException();
            if (item.getAvailable() == null) throw new ItemValidationException();
            if (item.getDescription() == null) throw new ItemValidationException();
            if (item.getName() == null) throw new ItemValidationException();
            item.setOwnerId(ownerId);
            return itemService.create(item);
        } catch (Exception e) {
            throw new ItemValidationException();
        }
    }

    @PutMapping("/{id}")
    public Item update(@Valid @RequestBody Item item, @PathVariable Long id) {
        item.setId(id);
        return itemService.update(item);
    }

    @DeleteMapping("/{id}")
    public Item delete(@PathVariable Long id) {
        return itemService.delete(id);
    }

    @GetMapping("/{id}")
    public Item get(@PathVariable Long id) {
        return itemService.get(id);
    }

    @GetMapping
    public Collection<Item> getAll() {
        return itemService.getAll();
    }

}
