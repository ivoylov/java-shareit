package ru.practicum.shareit.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shareit_user", schema = "public")
public class User {

    public static final int MAX_NAME_LENGTH = 20;
    public static final int MIN_NAME_LENGTH = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(name = "user_email", nullable = false)
    private String email;
    @JsonIgnore
    @OneToMany(targetEntity = Item.class, mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Item> items;
    @JsonIgnore
    @OneToMany(targetEntity = Booking.class, mappedBy = "booker", fetch = FetchType.LAZY)
    private List<Booking> bookings;

    public void updateUser(User updatedUser) {
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isBlank()) {
            this.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getName() != null && !updatedUser.getName().isBlank()) {
            this.setName(updatedUser.getName());
        }
    }

}
