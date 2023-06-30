package ru.practicum.shareit.item;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.InMemoryItemStorage;
import ru.practicum.shareit.user.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class ItemServiceTest {

    @InjectMocks
    ItemService itemService;

    @Mock
    InMemoryItemStorage itemStorage;

    @Test
    void create() {
        Item itemToCreate = Item.builder()
                .ownerId(1L)
                .name("name")
                .description("description")
                .available(true)
                .build();
        Item createdItem = Item.builder()
                .ownerId(1L)
                .name("name")
                .description("description")
                .available(true)
                .id(1L)
                .build();
        when(itemStorage.create(itemToCreate)).thenReturn(createdItem);
        assertEquals(createdItem, itemService.create(itemToCreate));
    }

    @Test
    void update() {
    }

    @Test
    void get() {
    }

    @Test
    void isExist() {
    }

    @Test
    void getAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void search() {
    }

    @Test
    void getOwnerItems() {
    }

}