package ru.practicum.shareit.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE items " +
            "SET item_name = :name, item_description = :description, item_available = :available " +
            "WHERE item_id = :itemId",
            nativeQuery = true)
    void update(String name, String description, Boolean available, Long itemId);

    List<Item> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndAvailable(String name, String description, Boolean state);

    @Query(value = "SELECT * FROM items WHERE owner_id = :ownerId", nativeQuery = true)
    List<Item> findOwnerItems(Long ownerId);

}
