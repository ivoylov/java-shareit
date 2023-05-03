package ru.practicum.shareit.item.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
@Validated
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public Item create(@Valid @RequestBody Item item) {
        return itemService.create(item);
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
