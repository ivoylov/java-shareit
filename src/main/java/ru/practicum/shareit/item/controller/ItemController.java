package ru.practicum.shareit.item.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.itemException.ItemValidationException;
import ru.practicum.shareit.exception.requestException.RequestValidationException;
import ru.practicum.shareit.exception.userException.UserNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
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
        Long ownerId = getOwnerId(request);
        if (ownerId == null) throw new RequestValidationException();
        item.setOwnerId(ownerId);
        checkItem(item);
        return itemService.create(item);
    }

    @PatchMapping("/{id}")
    public Item update(@RequestBody Item item, @PathVariable Long id, HttpServletRequest request) {
        Long ownerId = getOwnerId(request);
        if (ownerId == null) throw new RequestValidationException();
        item.setOwnerId(ownerId);
        item.setId(id);
        return itemService.update(item);
    }

    @DeleteMapping("/{id}")
    public Item delete(@PathVariable Long id) {
        return itemService.delete(id);
    }

    @GetMapping
    public Collection<Item> getOwnerItems(HttpServletRequest request) {
        return itemService.getOwnerItems(getOwnerId(request));
    }

    @GetMapping("/{id}")
    public Item get(@PathVariable Long id) {
        return itemService.get(id);
    }

    @GetMapping("/search")
    public Collection<Item> search(@RequestParam String text, HttpServletRequest request) {
        if (text.isBlank() || text.isEmpty()) return new ArrayList<>();
        Long ownerId = getOwnerId(request);
        //if (ownerId == null) {
            return itemService.search(text.toLowerCase());
        //}
        //return itemService.search(text.toLowerCase(), ownerId);
    }

    private Long getOwnerId(HttpServletRequest request) {
        try {
            return Long.parseLong(request.getHeader("X-Sharer-User-Id"));
        } catch (Exception e) {
            return null;
        }
    }

    private void checkItem(Item item) {
        if (!userService.isExist(item.getOwnerId())) throw new UserNotFoundException();
        if (item.getAvailable() == null) throw new ItemValidationException();
        if (item.getDescription() == null) throw new ItemValidationException();
        if (item.getName() == null) throw new ItemValidationException();
        if (item.getName().isEmpty() || item.getName().isBlank()) throw new ItemValidationException();
    }

}
