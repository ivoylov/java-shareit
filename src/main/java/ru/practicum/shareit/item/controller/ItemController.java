package ru.practicum.shareit.item.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.EntityValidationException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
@Validated
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemDto create(@Valid @RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        if (ownerId == null) throw new EntityValidationException(ownerId);
        Item item = ItemDtoMapper.toItem(itemDto);
        item.setOwnerId(ownerId);
        itemService.checkItem(item);
        return ItemDtoMapper.toItemDto(itemService.create(item));
    }

    @PatchMapping("/{id}")
    public ItemDto update(@RequestBody ItemDto itemDto, @PathVariable Long id, @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        if (ownerId == null) throw new EntityValidationException(ownerId);
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
    public List<ItemDto> getOwnerItems(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        ArrayList<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : itemService.getOwnerItems(ownerId)) {
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
        if (text.isBlank()) return Collections.emptyList();
        return itemService.search(text.toLowerCase());
    }

}
