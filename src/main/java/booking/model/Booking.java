package booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="booker_id", nullable = false)
    private User booker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id", nullable = false)
    private Item item;
    @Column(name="start_date")
    private LocalDateTime start;
    @Column(name="end_date")
    private LocalDateTime end;
    @Enumerated
    @Column(name="status")
    private Status status;
}
