package ru.practicum.shareit.item.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE items " +
            "SET name = :itemName, description = :itemDescription, available = :itemAvailable " +
            "WHERE id = :itemId",
            nativeQuery = true)
    void update(String itemName, String itemDescription, Boolean itemAvailable, Long itemId);
    List<Item> findItemsByIdAndOwnerId(Long itemId, Long ownerId);
    List<Item> findItemsByOwnerIdOrderById(Long ownerId);
}
