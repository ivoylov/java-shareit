package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.exception.BookingAvailableException;
import ru.practicum.shareit.exception.BookingTimeException;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.ItemAvailableException;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.user.UserService;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService implements CrudOperations<Booking> {

    private final BookingRepository bookingRepository;
    private final ItemService itemService;
    private final UserService userService;

    @Override
    public Booking create(Booking booking) {
        log.info("{}; create; {}", this.getClass(), booking);
        booking.setItem(itemService.get(booking.getItem().getId()));
        booking.setBooker(userService.get(booking.getBooker().getId()));
        check(booking);
        booking.setStatus(Status.WAITING);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        log.info("{}; update; {}", this.getClass(), booking);
        if (!bookingRepository.existsById(booking.getId())) {
            throw new EntityNotFoundException(booking);
        };
        bookingRepository.update(booking.getStatus().getId(), booking.getId());
        return bookingRepository.findById(booking.getId()).orElse(null);
    }

    @Override
    public Boolean isExist(Long id) {
        return null;
    }

    @Override
    public Booking get(Long id) {
        return null;
    }

    @Override
    public List<Booking> getAll() {
        return null;
    }

    @Override
    public Booking delete(Long id) {
        return null;
    }

    private void check(Booking booking) {
        log.info("{}; check; {}", this.getClass(), booking);
        if (!booking.getItem().getAvailable()) {
            throw new ItemAvailableException(booking.getItem());
        }
        if (!booking.isBookingTimeValid()) {
            throw new BookingTimeException(booking);
        }
        if (!booking.getItem().isAvailableOnRequestDate(booking.getStart(), booking.getEnd())) {
            throw new BookingAvailableException(booking);
        }
        if (booking.getBooker().getId().equals(booking.getItem().getOwner().getId())) {
            throw new EntityNotFoundException(booking);
        }
    }

}
