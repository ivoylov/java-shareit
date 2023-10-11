package ru.practicum.shareit.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.exception.booking.BookingAvailableException;
import ru.practicum.shareit.exception.booking.BookingTimeException;
import ru.practicum.shareit.exception.item.ItemAvailableException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bookings", schema = "public")
public class Booking implements Comparable<Booking> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
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
    @JoinColumn(name = "booker_id", nullable = false)
    private User booker;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Item.class)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public boolean isBookingTimeValid() {
        if (end.isBefore(start)) return false;
        if (start.equals(end)) return false;
        return true;
    }

    public State getState() {
        Clock clock = Clock.systemDefaultZone();
        LocalDateTime now = LocalDateTime.now(clock);
        if (getEnd().isBefore(now)) return State.PAST;
        if (getStart().isAfter(now)) return State.FUTURE;
        return State.CURRENT;
    }

    public void check() {
        if (!item.getAvailable()) {
            throw new ItemAvailableException(getItem());
        }
        if (!isBookingTimeValid()) {
            throw new BookingTimeException(this);
        }
        if (!item.isAvailableOnRequestDate(getStart(), getEnd())) {
            throw new BookingAvailableException(this);
        }
        if (booker.getId().equals(item.getOwner().getId())) {
            throw new EntityNotFoundException(this);
        }
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
