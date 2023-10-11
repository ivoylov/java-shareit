
package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
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

    private final ItemClient itemClient;

    @PostMapping
    public ResponseEntity<Object> create(@Validated(Create.class) @RequestBody ItemDtoIn itemDtoIn,
                                         @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        Item item = ItemMapper.toItem(itemDtoIn);
        return itemClient.createItem(itemDtoIn, ownerId);
        //return ItemMapper.toItemDtoOut(itemService.create(item, ownerId, itemDtoIn.getRequestId()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Validated(Update.class) ItemDtoIn itemDtoIn,
                             @PathVariable Long id,
                             @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        Item item = ItemMapper.toItem(itemDtoIn);
        return itemClient.updateItem(ownerId, id, itemDtoIn);
        //return ItemMapper.toItemDtoOut(itemService.update(item, id, ownerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return itemClient.delete(id);
        //return ItemMapper.toItemDtoOut(itemService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Object> getItems(@RequestHeader("X-Sharer-User-Id") Long ownerId,
                                     @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                     @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        return itemClient.getAllItems(ownerId, from, size);
        //return ItemMapper.toListItemDtoOut(itemService.getOwnerItems(ownerId, PageRequest.of(from, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id,
                          @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemClient.findItemById(userId, id);
        //return ItemMapper.toItemDtoOut(itemService.get(id, userId));
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchByNameOrDescription(@RequestParam String text) {
        return itemClient.searchForItemByDescription(text);
        //return ItemMapper.toListItemDtoOut(itemService.searchByNameOrDescription(text));
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> createComment(@RequestBody @Validated(Create.class) CommentDtoIn commentDtoIn,
                                  @RequestHeader("X-Sharer-User-Id") Long bookerId,
                                  @PathVariable Long itemId) {
        Comment comment = CommentMapper.toComment(commentDtoIn);
        return itemClient.createComment(commentDtoIn, itemId, bookerId);
        //return CommentMapper.toCommentDtoOut(itemService.createComment(comment, bookerId, itemId));
    }

}
