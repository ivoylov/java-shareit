package ru.practicum.shareit.item.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="items", schema = "public")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    @Column(name = "item_name", nullable = false)
    private String name;
    @Column(name = "item_description")
    private String description;
    @Column(name = "item_available")
    private Boolean available;
    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id", nullable = false)
    private User owner;
    @JsonIgnore
    @OneToMany(targetEntity = Booking.class, mappedBy = "item", fetch = FetchType.LAZY)
    private List<Booking> bookings = new ArrayList<>();
    public void updateItem(Item updatedItem) {
        if (updatedItem.getName() != null && !updatedItem.getName().isBlank()) {
            this.setName(updatedItem.getName());
        }
        if (updatedItem.getDescription() != null && !updatedItem.getDescription().isBlank()) {
            this.setDescription(updatedItem.getDescription());
        }
        if (updatedItem.getAvailable() != null) {
            this.setAvailable(updatedItem.getAvailable());
        }
    }
    public String toString() {
        return String.format("name=%s, description=%s", name, description);
    }

}
