package ru.practicum.shareit.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.exception.RequestValidationException;
import ru.practicum.shareit.exception.entity.EntityNotFoundException;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.RequestService;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    UserService userService;
    @Mock
    CommentRepository commentRepository;
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
    private Item createdItem = new Item(1L, "newName", "newDescription", true, owner, bookings, comments, null);

    @BeforeEach
    void setUp() {
        bookings = new ArrayList<>();
        comments = new ArrayList<>();
        items = new ArrayList<>();
        owner = new User(1L, "name", "mail@mail.ru", items, bookings, new ArrayList<>());
        itemToCreate = new Item(null, "name", "description", null, null, null, null, null);
        createdItem = new Item(1L, "newName", "newDescription", true, owner, bookings, comments, null);
    }

    @Test
    void create_thenExpectedEqualsWithCreatedItem() {
        Mockito.when(itemRepository.save(any())).thenReturn(createdItem);
        Mockito.when(requestService.get(any(), any())).thenReturn(null);
        assertEquals(itemService.create(itemToCreate, 1L, 1L), createdItem);
    }

    @Test
    void update_thenExpectedEqualsWithCreatedITem() {
        itemToCreate.setDescription("newDescription");
        itemToCreate.setName("newName");
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(createdItem));
        assertEquals(itemService.update(itemToCreate, 1L, 1L), createdItem);
    }

    @Test
    void get_thenExpectedEqualsWithCreatedItem() {
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(createdItem));
        assertEquals(itemService.get(1L, 1L), createdItem);
    }

    @Test
    void get_thenThrowEntityNotFoundException() {
        Mockito.when(itemRepository.findById(2L)).thenThrow(new EntityNotFoundException(new Formatter().format("Item с id=%d не найден", 2)));
        assertThrows(EntityNotFoundException.class, () -> itemService.get(2L, 2L));
    }

    @Test
    void delete_thenExpectedEqualsWithCreatedItemVariable() {
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(createdItem));
        assertEquals(itemService.delete(1L), createdItem);
    }

    @Test
    void searchByNameOrDescription_whenExpectedEqualsWithListOfCreatedItem() {
        Mockito
                .when(itemRepository.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndAvailable("name", "name", true))
                .thenReturn(List.of(createdItem));
        assertEquals(itemService.searchByNameOrDescription("name"), List.of(createdItem));
    }

    @Test
    void getOwnerItems_whenExpectedEqualsWithListOfCreatedItem() {
        Mockito
                .when(itemRepository.findOwnerItems(1L, PageRequest.of(0,1)))
                .thenReturn(List.of(createdItem));
        assertEquals(itemService.getOwnerItems(1L, PageRequest.of(0, 1)), List.of(createdItem));
    }

    @Test
    void createComment_thenExpectedEqualsWithComment() {
        User booker = new User(2L, "name", "mail", new ArrayList<>(), new ArrayList<>(), null);
        Booking booking = new Booking(1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), Status.WAITING, booker, createdItem);
        Comment comment = new Comment(1L, booker, createdItem, LocalDateTime.now(), "comment");
        bookings.add(booking);
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(createdItem));
        Mockito.when(commentRepository.save(any())).thenReturn(comment);
        assertEquals(comment, itemService.createComment(comment, booker.getId(), createdItem.getId()));
    }

    @Test
    void createComment_thenThrowRequestValidationException() {
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(createdItem));
        assertThrows(RequestValidationException.class, () -> itemService.createComment(new Comment(), 1L, 1L));
    }

}