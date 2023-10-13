
package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemClient itemClient;
    private final String userIdHeader = "X-Sharer-User-Id";
    private final String idPath = "/{id}";

    @PostMapping
    public ResponseEntity<Object> create(@Validated(Create.class) @RequestBody ItemDtoIn itemDtoIn,
                                         @RequestHeader(userIdHeader) Long ownerId) {
        return itemClient.createItem(itemDtoIn, ownerId);
    }

    @PatchMapping(idPath)
    public ResponseEntity<Object> update(@RequestBody @Validated(Update.class) ItemDtoIn itemDtoIn,
                             @PathVariable Long id,
                             @RequestHeader(userIdHeader) Long ownerId) {
        return itemClient.updateItem(ownerId, id, itemDtoIn);
    }

    @DeleteMapping(idPath)
    public void delete(@PathVariable Long id) {
        itemClient.delete(id);
    }

    @GetMapping
    public ResponseEntity<Object> getItems(@RequestHeader(userIdHeader) Long ownerId,
                                     @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                     @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        return itemClient.getAllItems(ownerId, from, size);
    }

    @GetMapping(idPath)
    public ResponseEntity<Object> get(@PathVariable Long id,
                          @RequestHeader(userIdHeader) Long userId) {
        return itemClient.findItemById(id, userId);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchByNameOrDescription(@RequestParam String text) {
        return itemClient.searchForItemByDescription(text);
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> createComment(@RequestBody @Validated(Create.class) CommentDtoIn commentDtoIn,
                                  @RequestHeader(userIdHeader) Long bookerId,
                                  @PathVariable Long itemId) {
        return itemClient.createComment(commentDtoIn, itemId, bookerId);
    }

}
