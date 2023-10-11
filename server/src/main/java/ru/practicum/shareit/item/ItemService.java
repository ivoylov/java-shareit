package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.State;
import ru.practicum.shareit.exception.RequestValidationException;
import ru.practicum.shareit.exception.entity.EntityNotFoundException;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.RequestService;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final RequestService requestService;

    @Transactional
    public Item create(Item item, Long ownerId, Long requestId) {
        log.info(this.getClass() + "; запрос на создание {}", item);
        item.setOwner(userService.get(ownerId));
        item.setRequest(requestId == null ? null : requestService.get(requestId, ownerId));
        return itemRepository.save(item);
    }

    @Transactional
    public Item update(Item updatedItem, Long itemId, Long ownerId) {
        log.info(this.getClass() + " запрос на обновление {}", updatedItem);
        User user = new User();
        user.setId(ownerId);
        updatedItem.setOwner(user);
        updatedItem.setId(itemId);
        checkOwner(updatedItem);
        Item itemToUpdate = itemRepository.findById(updatedItem.getId()).orElseThrow(() -> new EntityNotFoundException(updatedItem));
        itemToUpdate.update(updatedItem);
        itemRepository.update(itemToUpdate.getName(), itemToUpdate.getDescription(), itemToUpdate.getAvailable(), itemToUpdate.getId());
        return itemRepository.findById(itemToUpdate.getId()).orElse(null);
    }

    @Transactional
    public Item get(Long id, Long userId) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(new Formatter().format("Item с id=%d не найден", id)));
        if (!item.getOwner().getId().equals(userId)) {
            item.setBookings(null);
        }
        return item;
    }

    @Transactional
    public Item delete(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(new Formatter().format("Item с id=%d не найден", id)));
        itemRepository.deleteById(id);
        item.setBookings(Collections.emptyList());
        return item;
    }

    @Transactional
    public List<Item> searchByNameOrDescription(String text) {
        String formText = text.toLowerCase();
        return itemRepository.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndAvailable(formText, formText, true);
    }

    @Transactional
    public List<Item> getOwnerItems(Long ownerId, PageRequest pageRequest) {
        List<Item> items = itemRepository.findOwnerItems(ownerId, pageRequest);
        while (items.size() == 0 && pageRequest.getPageNumber() > -1) {
            pageRequest = pageRequest.previous();
            items.addAll(itemRepository.findOwnerItems(ownerId, pageRequest));
        }
        return items;
    }

    @Transactional
    private void checkOwner(Item item) {
        Item checkedItem = itemRepository.findById(item.getId()).orElseThrow(() -> new EntityNotFoundException(item));
        Long itemOwnerId = item.getOwner().getId();
        Long checkedOwnerId = checkedItem.getOwner().getId();
        if (!itemOwnerId.equals(checkedOwnerId)) {
            throw new EntityNotFoundException(item);
        }
    }

    @Transactional
    public Comment createComment(Comment comment, Long bookerId, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(itemId));
        List<Booking> bookings = item.getBookings().stream()
                .filter(booking -> booking.getBooker().getId().equals(bookerId))
                .filter(booking -> booking.getState() != State.FUTURE)
                .collect(Collectors.toList());
        if (bookings.size() == 0) {
            throw new RequestValidationException("Факт бронирования не подтверждён");
        }
        comment.setAuthor(bookings.get(0).getBooker());
        comment.setItem(item);
        comment.setCreatedDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

}
