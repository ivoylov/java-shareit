package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.exception.ItemNotAvailableException;
import ru.practicum.shareit.item.ItemService;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService implements CrudOperations<Booking> {

    private final BookingRepository bookingRepository;
    private final ItemService itemService;

    @Override
    public Booking create(Booking booking) {
        log.info("{}; create; {}", this.getClass(), booking);
        if (!itemService.get(booking.getItem().getId()).getAvailable()) {
            throw new ItemNotAvailableException(booking.getItem());
        }
        booking.setStatus(Status.WAITING);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        log.info("{}; update; {}", this.getClass(), booking);
        if (!bookingRepository.existsById(booking.getId())) {
            throw new EntityNotFoundException(booking);
        };
        bookingRepository.update(booking.getStatus(), booking.getId());
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

}
