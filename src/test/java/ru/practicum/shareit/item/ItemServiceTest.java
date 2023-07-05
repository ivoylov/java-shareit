package ru.practicum.shareit.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.InMemoryItemStorage;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;
    @Mock
    private InMemoryItemStorage itemStorage;
    @Mock
    private UserService userService;
    private Item item;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "name", "user@email.ru", new ArrayList<>());
        item = Item.builder()
                .owner(user)
                .name("name")
                .description("description")
                .available(true)
                .build();
    }

    @Test
    void create() {
        Item itemToCreate = Item.builder()
                .name("name")
                .description("description")
                .available(true)
                .owner(user)
                .build();
        Item createdItem = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(user)
                .build();
        when(itemStorage.create(any())).thenReturn(createdItem);
        when(userService.isExist(1L)).thenReturn(true);
        assertEquals(createdItem, itemService.create(itemToCreate));
    }

    @Test
    void update() {
        Item itemToUpdate = Item.builder()
                .owner(user)
                .name("name")
                .description("description")
                .available(true)
                .build();
        Item updatedItem = Item.builder()
                .owner(user)
                .name("newName")
                .description("newDescription")
                .available(true)
                .build();
        when(itemStorage.get(itemToUpdate.getId())).thenReturn(itemToUpdate);
        when(itemStorage.update(itemToUpdate)).thenReturn(updatedItem);
        assertEquals(updatedItem, itemService.update(itemToUpdate));
    }

    @Test
    void get() {
        when(itemStorage.get(item.getId())).thenReturn(item);
        assertEquals(item, itemService.get(item.getId()));
    }

    @Test
    void isExist() {
        when(itemStorage.isExist(item.getId())).thenReturn(true);
        assertEquals(true, itemService.isExist(item.getId()));
    }

    @Test
    void getAll() {
        ArrayList<Item> itemList = new ArrayList<>(List.of(item));
        when(itemStorage.getAll()).thenReturn(itemList);
        assertEquals(itemList, itemService.getAll());
    }

    @Test
    void delete() {
        when(itemStorage.delete(item.getId())).thenReturn(item);
        assertEquals(item, itemService.delete(item.getId()));
    }

    @Test
    void search() {
        when(itemStorage.searchByNameOrDescription(item.getDescription())).thenReturn(new ArrayList<>(List.of(item)));
        assertEquals(new ArrayList<>(List.of(item)), itemService.searchByNameOrDescription(item.getDescription()));
    }

    @Test
    void getOwnerItems() {
        when(itemStorage.getOwnerItems(item.getOwner().getId())).thenReturn(new ArrayList<>(List.of(item)));
        assertEquals(new ArrayList<>(List.of(item)), itemService.getOwnerItems(item.getOwner().getId()));
    }

}