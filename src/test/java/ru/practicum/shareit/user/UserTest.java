package ru.practicum.shareit.user;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.user.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void shouldCorrectWorkToString() {
        User user = new User(null, null);
        System.out.println(user);
        assertEquals("User(id=null, name=null, email=null)", user.toString());
    }

}
