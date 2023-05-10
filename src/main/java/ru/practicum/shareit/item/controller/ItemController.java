package ru.practicum.shareit.item.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.EntityValidationException;
import ru.practicum.shareit.exception.ItemValidationException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
@Validated
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final UserService userService;

    @PostMapping
    public ItemDto create(@Valid @RequestBody ItemDto itemDto, HttpServletRequest request) {
        Long ownerId = getOwnerId(request);
        if (ownerId == null) throw new EntityValidationException(request);
        Item item = ItemDtoMapper.toItem(itemDto);
        item.setOwnerId(ownerId);
        checkItem(item);
        return ItemDtoMapper.toItemDto(itemService.create(item));
    }

    @PatchMapping("/{id}")
    public ItemDto update(@RequestBody ItemDto itemDto, @PathVariable Long id, HttpServletRequest request) {
        Long ownerId = getOwnerId(request);
        if (ownerId == null) throw new EntityValidationException(request);
        Item item = ItemDtoMapper.toItem(itemDto);
        item.setOwnerId(ownerId);
        item.setId(id);
        return ItemDtoMapper.toItemDto(itemService.update(item));
    }

    @DeleteMapping("/{id}")
    public ItemDto delete(@PathVariable Long id) {
        return ItemDtoMapper.toItemDto(itemService.delete(id));
    }

    @GetMapping
    public List<ItemDto> getOwnerItems(HttpServletRequest request) {
        ArrayList<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : itemService.getOwnerItems(getOwnerId(request))) {
            itemsDto.add(ItemDtoMapper.toItemDto(item));
        }
        return itemsDto;
    }

    @GetMapping("/{id}")
    public ItemDto get(@PathVariable Long id) {
        return ItemDtoMapper.toItemDto(itemService.get(id));
    }

    @GetMapping("/search")
    public List<Item> search(@RequestParam String text) {
        if (text.isBlank()) return new ArrayList<>();
        return itemService.search(text.toLowerCase());
    }

    private Long getOwnerId(HttpServletRequest request) {
        try {
            return Long.parseLong(request.getHeader("X-Sharer-User-Id"));
        } catch (Exception e) {
            return null;
        }
    }

    private void checkItem(Item item) {
        if (!userService.isExist(item.getOwnerId())) throw new EntityNotFoundException(item);
        if (item.getAvailable() == null) throw new ItemValidationException();
        if (item.getDescription() == null) throw new ItemValidationException();
        if (item.getName() == null) throw new ItemValidationException();
        if (item.getName().isBlank()) throw new ItemValidationException();
    }

}
