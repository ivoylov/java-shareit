package ru.practicum.shareit.exception.item;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnsupportedItemStatusExceptionTest {

    @Test
    void getEntity_whenExpectedNewItem() {
        Item item = new Item();
        UnsupportedItemStatusException exception = new UnsupportedItemStatusException(item);
        assertEquals(item, exception.getEntity());
    }

}