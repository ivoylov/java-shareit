package ru.practicum.shareit.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleTest {

    @Test
    void getId_whenExpectedEqualsBooker0Owner1() {
        Role owner = Role.OWNER;
        Role booker = Role.BOOKER;
        assertEquals(0, booker.getId());
        assertEquals(1, owner.getId());
    }

}