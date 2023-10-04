package ru.practicum.shareit.exception.item;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;

import static org.junit.jupiter.api.Assertions.*;

class UnsupportedItemStatusExceptionTest {

    @Test
    void getEntity() {
        Item item = new Item();
        UnsupportedItemStatusException exception = new UnsupportedItemStatusException(item);
        assertEquals(item, exception.getEntity());
    }

}