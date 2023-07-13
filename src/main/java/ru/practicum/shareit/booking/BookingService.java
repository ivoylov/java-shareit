package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.CrudOperations;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.exception.EntityNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService implements CrudOperations<Booking> {

    private final BookingRepository bookingRepository;

    @Override
    public Booking create(Booking booking) {
        booking.setStatus(Status.WAITING);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
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
