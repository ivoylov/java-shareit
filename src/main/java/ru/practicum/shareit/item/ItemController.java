package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemDto;
import ru.practicum.shareit.item.model.ItemDtoMapper;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemDto create(@Validated(Create.class) @RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        Item item = ItemDtoMapper.toItem(itemDto);
        item.setOwnerId(ownerId);
        return ItemDtoMapper.toItemDto(itemService.create(item));
    }

    @PatchMapping("/{id}")
    public ItemDto update(@RequestBody ItemDto itemDto, @PathVariable Long id, @RequestHeader("X-Sharer-User-Id") Long ownerId) {
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
        return ItemDtoMapper.toItemDtoList(itemService.getOwnerItems(ownerId));
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
