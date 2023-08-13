package ru.practicum.shareit.item.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="items", schema = "public")
public class Item implements Comparable<Item> {
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
    private List<Booking> bookings;
    @OneToMany(targetEntity = Comment.class, mappedBy = "item", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public void update(Item updatedItem) {
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

    public boolean isAvailableOnRequestDate(LocalDateTime start, LocalDateTime end) {
        if (bookings == null) return true;
        for (Booking booking : bookings) {
            if ((start.isAfter(booking.getStart()) && start.isBefore(booking.getEnd())) ||
                    (end.isAfter(booking.getStart()) && end.isBefore(booking.getEnd()))) {
                return false;
            }
        }
        return true;
    }

    public Booking getLastBooking() {
        if (bookings == null) return null;
        return bookings.stream()
                .filter(b -> b.getStart().isBefore(LocalDateTime.now()))
                .filter(b -> b.getStatus() == Status.APPROVED || b.getStatus() == Status.WAITING)
                .max(Comparator.comparing(Booking::getEnd)).orElse(null);
    }

    public Booking getNextBooking() {
        if (bookings == null) return null;
        return bookings.stream()
                .filter(b -> b.getStart().isAfter(LocalDateTime.now()))
                .filter(b -> b.getStatus() == Status.APPROVED || b.getStatus() == Status.WAITING)
                .min(Comparator.comparing(Booking::getStart)).orElse(null);
    }

    @Override
    public int compareTo(Item item) {
        return this.id.compareTo(item.getId());
    }

    public String toString() {
        return String.format("id=%d, name=%s, description=%s, available=%s, owner=%s",
                id, name, description, available, owner);
    }

}
