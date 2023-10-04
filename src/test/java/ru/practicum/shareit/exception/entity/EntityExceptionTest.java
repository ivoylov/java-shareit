package ru.practicum.shareit.exception.entity;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.exception.entity.EntityException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityExceptionTest {

    @Test
    void getEntity() {
        Item item = new Item();
        User user = new User();
        EntityException itemEntityException = new EntityException(item);
        EntityException userEntityException = new EntityException(user);
        assertEquals(item, itemEntityException.getEntity());
        assertEquals(user, userEntityException.getEntity());
    }

}
