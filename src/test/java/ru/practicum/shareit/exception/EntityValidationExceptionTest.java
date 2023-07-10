package ru.practicum.shareit.exception;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;

import static org.junit.jupiter.api.Assertions.*;

class EntityValidationExceptionTest {

    @Test
    void getEntity() {
        Item item = new Item();
        EntityValidationException ex = new EntityValidationException(item);
        assertEquals(item, ex.getEntity());
    }

}