package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareit.client.BaseClient;
import ru.practicum.shareit.item.model.CommentDtoIn;
import ru.practicum.shareit.item.model.CommentRequestDto;
import ru.practicum.shareit.item.model.ItemDtoIn;
import ru.practicum.shareit.item.model.ItemRequestDto;

import java.util.Map;

@Service
public class ItemClient extends BaseClient {
    private static final String API_PREFIX = "/items";

    @Autowired
    public ItemClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build());
    }

    public ResponseEntity<Object> createItem(ItemDtoIn itemDto, long userId) {
        return post("", userId, itemDto);
    }

    public ResponseEntity<Object> updateItem(long userId, long id, ItemDtoIn itemDtoIn) {
        return patch("/" + userId, id, itemDtoIn);
    }

    public ResponseEntity<Object> delete(long id) {
        return delete("/" + id);
    }

    public ResponseEntity<Object> findItemById(Long userId, Long id) {
        return get("/" + userId, id);
    }

    public ResponseEntity<Object> getAllItems(Long userId, int from, int size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("?from={from}&size={size}", userId, parameters);
    }

    public ResponseEntity<Object> searchForItemByDescription(String text) {
        return get("/search?text=" + text);
    }

    public ResponseEntity<Object> createComment(CommentDtoIn commentDtoIn, long itemId, long userId) {
        return post("/" + itemId + "/comment", userId, commentDtoIn);
    }

}

