package ru.practicum.shareit.item.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;
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
        return itemService.create(itemDto);
    }

    @PatchMapping("/{id}")
    public ItemDto update(@RequestBody ItemDto itemDto, @PathVariable Long id, @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        itemDto.setOwnerId(ownerId);
        itemDto.setId(id);
        return itemService.update(itemDto);
    }

    @DeleteMapping("/{id}")
    public ItemDto delete(@PathVariable Long id) {
        return itemService.delete(id);
    }

    @GetMapping
    public List<ItemDto> getOwnerItems(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemService.getOwnerItems(ownerId);
    }

    @GetMapping("/{itemId}")
    public ItemDto get(@PathVariable Long itemId,
                       @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return itemService.get(itemId);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam String text) {
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
