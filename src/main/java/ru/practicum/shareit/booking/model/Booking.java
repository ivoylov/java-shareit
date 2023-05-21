package ru.practicum.shareit.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.model.Item;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date", nullable = false)
    private LocalDateTime start;
    @Column(name = "end_date", nullable = false)
    private LocalDateTime end;
    @Column(name = "item_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "booker_id", nullable = false)
    private Long bookerId;
    private Status status;

    private boolean isEndBeforeStart() {
        return end.isBefore(start);
    }

    private boolean isStartEqualEnd() {
        return start.equals(end);
    }

    public boolean isBookingTimeValid() {
        if (isEndBeforeStart()) return false;
        if (isStartEqualEnd()) return false;
        return true;
    }

}
