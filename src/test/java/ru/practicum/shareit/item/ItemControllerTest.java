package ru.practicum.shareit.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import ru.practicum.shareit.item.model.*;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    @Mock
    private ItemService itemService;
    @InjectMocks
    private ItemController itemController;
    private Item itemToCreate;
    private Item createdItem;
    private User owner;
    private ItemDtoIn itemDtoIn;
    private ItemDtoOut itemDtoOut;

    @BeforeEach
    void setUp() {
        itemDtoIn = new ItemDtoIn("name", "description", null, 1L);
        itemDtoOut = new ItemDtoOut(1L, "name", "description", true, null, null, new ArrayList<>(), null);
        owner = new User(1L, "name", "mail@email.ru", null, null, new ArrayList<>());
        itemToCreate = new Item(null, "name", "description", null, null, null, null, null);
        createdItem = new Item(1L, "name", "description", true, owner, null, null, null);
    }

    @Test
    void create() {
        Mockito.when(itemService.create(itemToCreate, 1L, 1L)).thenReturn(createdItem);
        assertEquals(itemController.create(itemDtoIn, 1L), itemDtoOut);
    }

    @Test
    void update() {
        Item newItem = new Item(1L, "newName", "newDescription", true, owner, null, null, null);
        itemDtoIn.setName("newName");
        itemDtoIn.setDescription("newDescription");
        itemDtoOut.setName("newName");
        itemDtoOut.setDescription("newDescription");
        Mockito.when(itemService.update(any(), anyLong(), anyLong())).thenReturn(newItem);
        assertEquals(itemController.update(itemDtoIn, 1L, 1L), itemDtoOut);
    }

    @Test
    void delete() {
        Mockito.when(itemService.delete(1L)).thenReturn(createdItem);
        assertEquals(itemController.delete(1L), itemDtoOut);
    }

    @Test
    void getItems() {
        Mockito.when(itemService.getOwnerItems(1L, PageRequest.of(0, 1))).thenReturn(List.of(createdItem));
        assertEquals(itemController.getItems(1L, 0, 1), List.of(itemDtoOut));
    }

    @Test
    void get() {
        Mockito.when(itemService.get(1L, 1L)).thenReturn(createdItem);
        assertEquals(itemController.get(1L, 1L), itemDtoOut);
    }

    @Test
    void searchByNameOrDescription() {
        Mockito.when(itemService.searchByNameOrDescription("description")).thenReturn(List.of(createdItem));
        assertEquals(itemController.searchByNameOrDescription("description"), List.of(itemDtoOut));
    }

    @Test
    void createComment() {
        LocalDateTime created = LocalDateTime.now();
        CommentDtoIn commentDtoIn = new CommentDtoIn("commentText");
        CommentDtoOut commentDtoOut = new CommentDtoOut(1L, "commentText", "bookerName", created);
        User booker = new User(2L, "bookerName", "bookerMail@mail.ru", null, null, new ArrayList<>());
        Comment comment = new Comment(1L, booker, createdItem, created, "commentText");
        Mockito.when(itemService.createComment(any(), anyLong(), anyLong())).thenReturn(comment);
        assertEquals(itemController.createComment(commentDtoIn, 1L, 1L), commentDtoOut);
    }

}