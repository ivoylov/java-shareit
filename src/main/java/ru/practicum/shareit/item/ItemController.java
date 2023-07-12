
package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemDto;
import ru.practicum.shareit.item.model.ItemDtoMapper;
import ru.practicum.shareit.user.model.User;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemDto create(@Validated(Create.class) @RequestBody ItemDto itemDto,
                          @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        Item item = ItemDtoMapper.toItem(itemDto);
        User user = new User();
        user.setId(ownerId);
        item.setOwner(user);
        return ItemDtoMapper.toItemDto(itemService.create(item));
    }

    @PatchMapping("/{id}")
    public ItemDto update(@RequestBody @Validated(Update.class) ItemDto itemDto,
                          @PathVariable Long id,
                          @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        Item item = ItemDtoMapper.toItem(itemDto);
        User user = new User();
        user.setId(ownerId);
        item.setOwner(user);
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
    public List<ItemDto> searchByNameOrDescription(@RequestParam String text) {
        if (text.isBlank()) return Collections.emptyList();
        return ItemDtoMapper.toItemDtoList(itemService.searchByNameOrDescription(text));
    }

}
