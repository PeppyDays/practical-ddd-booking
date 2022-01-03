package com.cargotracker.booking.infrastructure.repositories;

import com.cargotracker.booking.domain.model.aggregates.BookingId;
import com.cargotracker.booking.domain.model.aggregates.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Cargo findByBookingId(BookingId bookingId);

    @Query("select c.bookingId from Cargo c")
    List<BookingId> findAllBookingIds();
}
