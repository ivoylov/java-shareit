package ru.practicum.shareit.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bookings", schema = "public")
public class Booking  implements Comparable<Booking> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id")
    private Long id;
    @Column(name = "start_date")
    private LocalDateTime start;
    @Column(name = "end_date")
    private LocalDateTime end;
    @Enumerated
    @Column(name = "status")
    private Status status;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name="booker_id", nullable = false)
    private User booker;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Item.class)
    @JoinColumn(name="item_id", nullable = false)
    private Item item;
    public boolean isBookingTimeValid() {
        if (end.isBefore(start)) return false;
        if (start.equals(end)) return false;
        return true;
    }
    @Override
    public int compareTo(Booking booking) {
        return booking.getId().compareTo(this.id);
    }
    public String toString() {
        return String.format("id=%d, start=%s, end=%s, status=%s, booker=%s, item=%s",
                id, start, end, status, booker, item);
    }
}
