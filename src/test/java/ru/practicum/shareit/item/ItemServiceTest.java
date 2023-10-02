package ru.practicum.shareit.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.RequestService;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    UserService userService;
    @Mock
    RequestService requestService;
    @Mock
    ItemRepository itemRepository;
    @InjectMocks
    private ItemService itemService;
    private Item itemToCreate;
    private User owner;
    private List<Booking> bookings;
    private List<Comment> comments;
    private List<Item> items;

    @BeforeEach
    void setUp() {
        bookings = new ArrayList<>();
        comments = new ArrayList<>();
        items = new ArrayList<>();
        owner = new User(1L, "name", "mail@mail.ru", items, bookings, new ArrayList<>());
        itemToCreate = new Item(null, "name", "description", null, null, null, null, null);
    }

    @Test
    void create() {
        Item createdItem = new Item(1L, "name", "description", true, owner, bookings, comments, null);
        Mockito.when(itemRepository.save(any())).thenReturn(createdItem);
        Mockito.when(requestService.get(any(), any())).thenReturn(null);
        assertEquals(itemService.create(itemToCreate, 1L, 1L), createdItem);
    }

    @Test
    void update() {
        itemToCreate.setDescription("newDescription");
        itemToCreate.setName("newName");
        Item createdItem = new Item(1L, "newName", "newDescription", true, owner, bookings, comments, null);
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(createdItem));
        assertEquals(itemService.update(itemToCreate, 1L, 1L), createdItem);
    }

    @Test
    void get() {
    }

    @Test
    void isExist() {
    }

    @Test
    void delete() {
    }

    @Test
    void searchByNameOrDescription() {
    }

    @Test
    void getOwnerItems() {
    }

    @Test
    void createComment() {
    }
}