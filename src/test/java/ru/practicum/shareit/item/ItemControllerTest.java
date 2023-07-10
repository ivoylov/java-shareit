package ru.practicum.shareit.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemDto;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    @Mock
    private ItemService itemService;
    @InjectMocks
    private ItemController itemController;
    private ItemDto itemDtoToCreate;
    private ItemDto createdItemDto;
    private Item createdItem;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "name", "user@email.ru", new ArrayList<>(), new ArrayList<>());
        itemDtoToCreate = ItemDto.builder()
                .name("name")
                .description("description")
                .available(true)
                .owner(user)
                .build();
        createdItem = Item.builder()
                .name("name")
                .description("description")
                .available(true)
                .id(1L)
                .owner(user)
                .build();
        createdItemDto = ItemDto.builder()
                .name("name")
                .description("description")
                .available(true)
                .id(1L)
                .owner(user)
                .build();
    }

    @Test
    void create() {
        when(itemService.create(any())).thenReturn(createdItem);
        assertEquals(createdItemDto, itemController.create(itemDtoToCreate, 1L));
    }

    @Test
    void update() {
        createdItem.setDescription("newDescription");
        createdItemDto.setDescription("newDescription");
        when(itemService.update(any())).thenReturn(createdItem);
        assertEquals(createdItemDto, itemController.update(itemDtoToCreate, 1L, 1L));
    }

    @Test
    void delete() {
        when(itemService.delete(1L)).thenReturn(createdItem);
        assertEquals(createdItemDto, itemController.delete(1L));
    }

    @Test
    void getOwnerItems() {
        when(itemService.getOwnerItems(1L)).thenReturn(new ArrayList<>(List.of(createdItem)));
        assertEquals(new ArrayList<>(List.of(createdItemDto)), itemController.getOwnerItems(1L));
    }

    @Test
    void get() {
        when(itemService.get(1L)).thenReturn(createdItem);
        assertEquals(createdItemDto, itemController.get(1L));
    }

    @Test
    void search() {
        when(itemService.searchByNameOrDescription("name")).thenReturn(new ArrayList<>(List.of(createdItem)));
        assertEquals(new ArrayList<>(List.of(createdItemDto)), itemController.searchByNameOrDescription("name"));
    }
}