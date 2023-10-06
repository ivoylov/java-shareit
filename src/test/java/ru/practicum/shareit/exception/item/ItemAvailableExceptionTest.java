package ru.practicum.shareit.exception.item;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemAvailableExceptionTest {

    @Test
    void getEntity() {
        Item item = new Item();
        ItemAvailableException exception = new ItemAvailableException(item);
        assertEquals(item, exception.getEntity());
    }
}