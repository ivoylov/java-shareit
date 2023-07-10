package ru.practicum.shareit.item.model;

import booking.model.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id", nullable = false)
    private User owner;
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<Booking> bookings;

}
