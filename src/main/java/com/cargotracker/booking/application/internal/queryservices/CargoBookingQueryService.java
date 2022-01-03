package com.cargotracker.booking.application.internal.queryservices;

import com.cargotracker.booking.domain.model.aggregates.BookingId;
import com.cargotracker.booking.domain.model.aggregates.Cargo;
import com.cargotracker.booking.infrastructure.repositories.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoBookingQueryService {

    private final CargoRepository cargoRepository;

    public CargoBookingQueryService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    public List<BookingId> findAllBookingIds() {
        return cargoRepository.findAllBookingIds();
    }

    public Cargo find(String bookingId) {
        return cargoRepository.findByBookingId(new BookingId(bookingId));
    }
}
