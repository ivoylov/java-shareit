package ru.practicum.shareit.item.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoMapper;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.CommentService;
import ru.practicum.shareit.item.service.ItemService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CommentService commentService;

    @PostMapping
    public ItemDto create(@Validated(Create.class) @RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        itemDto.setOwnerId(ownerId);
        itemService.checkItemDtoOwner(itemDto);
        Item item = ItemDtoMapper.toItem(itemDto);
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

    @PostMapping ("/{itemId}/comment")
    public Comment createComment(@RequestHeader("X-Sharer-User-Id") Long authorId,
                                 @PathVariable Long itemId,
                                 @Validated(Create.class) @RequestBody Comment comment) {
        comment.setItemId(itemId);
        comment.setAuthorId(authorId);
        return commentService.create(comment);
    }

}
