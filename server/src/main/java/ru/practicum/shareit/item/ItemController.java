
package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;
import ru.practicum.shareit.item.model.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final String userIdHeader = "X-Sharer-User-Id";
    private final String idPath = "/{id}";

    @PostMapping
    public ItemDtoOut create(@Validated(Create.class) @RequestBody ItemDtoIn itemDtoIn,
                            @RequestHeader(userIdHeader) Long ownerId) {
        Item item = ItemMapper.toItem(itemDtoIn);
        return ItemMapper.toItemDtoOut(itemService.create(item, ownerId, itemDtoIn.getRequestId()));
    }

    @PatchMapping(idPath)
    public ItemDtoOut update(@RequestBody @Validated(Update.class) ItemDtoIn itemDto,
                            @PathVariable Long id,
                            @RequestHeader(userIdHeader) Long ownerId) {
        Item item = ItemMapper.toItem(itemDto);
        return ItemMapper.toItemDtoOut(itemService.update(item, id, ownerId));
    }

    @DeleteMapping(idPath)
    public ItemDtoOut delete(@PathVariable Long id) {
        return ItemMapper.toItemDtoOut(itemService.delete(id));
    }

    @GetMapping
    public List<ItemDtoOut> getItems(@RequestHeader(userIdHeader) Long ownerId,
                                     @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                     @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        return ItemMapper.toListItemDtoOut(itemService.getOwnerItems(ownerId, PageRequest.of(from, size)));
    }

    @GetMapping(idPath)
    public ItemDtoOut get(@PathVariable Long id,
                          @RequestHeader(userIdHeader) Long userId) {
        return ItemMapper.toItemDtoOut(itemService.get(id, userId));
    }

    @GetMapping("/search")
    public List<ItemDtoOut> searchByNameOrDescription(@RequestParam String text) {
        if (text.isBlank()) return Collections.emptyList();
        return ItemMapper.toListItemDtoOut(itemService.searchByNameOrDescription(text));
    }

    @PostMapping("/{itemId}/comment")
    public CommentDtoOut createComment(@RequestBody @Validated(Create.class) CommentDtoIn commentDtoIn,
                                  @RequestHeader(userIdHeader) Long bookerId,
                                  @PathVariable Long itemId) {
        Comment comment = CommentMapper.toComment(commentDtoIn);
        return CommentMapper.toCommentDtoOut(itemService.createComment(comment, bookerId, itemId));
    }

}
