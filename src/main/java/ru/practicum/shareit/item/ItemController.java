
package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.item.model.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemDtoOut create(@Validated(Create.class) @RequestBody ItemDtoIn itemDto,
                            @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        Item item = ItemMapper.toItem(itemDto);
        return ItemMapper.toItemDtoOut(itemService.create(item, ownerId));
    }

    @PatchMapping("/{id}")
    public ItemDtoOut update(@RequestBody @Validated(Update.class) ItemDtoIn itemDto,
                            @PathVariable Long id,
                            @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        Item item = ItemMapper.toItem(itemDto);
        return ItemMapper.toItemDtoOut(itemService.update(item, id, ownerId));
    }

    @DeleteMapping("/{id}")
    public ItemDtoOut delete(@PathVariable Long id) {
        return ItemMapper.toItemDtoOut(itemService.delete(id));
    }

    @GetMapping
    public List<ItemDtoOut> getOwnerItems(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return ItemMapper.toItemDtoOutList(itemService.getOwnerItems(ownerId));
    }

    @GetMapping("/{id}")
    public ItemDtoOut get(@PathVariable Long id,
                          @RequestHeader("X-Sharer-User-Id") Long userId) {
        return ItemMapper.toItemDtoOut(itemService.get(id, userId));
    }

    @GetMapping("/search")
    public List<ItemDtoOut> searchByNameOrDescription(@RequestParam String text) {
        if (text.isBlank()) return Collections.emptyList();
        return ItemMapper.toItemDtoOutList(itemService.searchByNameOrDescription(text));
    }

    @PostMapping("/{itemId}/comment")
    public CommentDtoOut createComment (@RequestBody @Validated(Create.class) CommentDtoIn commentDtoIn,
                                  @RequestHeader("X-Sharer-User-Id") Long bookerId,
                                  @PathVariable Long itemId) {
        Comment comment = CommentMapper.toComment(commentDtoIn);
        return CommentMapper.toCommentDtoOut(itemService.createComment(comment, bookerId, itemId));
    }

}
