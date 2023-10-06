package ru.practicum.shareit.exception.entity;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityNotFoundExceptionTest {

    @Test
    void getEntity() {
        Item item = new Item();
        EntityNotFoundException ex = new EntityNotFoundException(item);
        assertEquals(item, ex.getEntity());
    }

}