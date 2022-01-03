package com.cargotracker.booking.application.internal.commandservices;

import com.cargotracker.booking.application.internal.outboundservices.acl.ExternalCargoRoutingService;
import com.cargotracker.booking.domain.model.aggregates.BookingId;
import com.cargotracker.booking.domain.model.aggregates.Cargo;
import com.cargotracker.booking.domain.model.commands.BookCargoCommand;
import com.cargotracker.booking.domain.model.commands.RouteCargoCommand;
import com.cargotracker.booking.domain.model.entities.Location;
import com.cargotracker.booking.domain.model.valueobjects.Itinerary;
import com.cargotracker.booking.domain.model.valueobjects.RouteSpecification;
import com.cargotracker.booking.infrastructure.repositories.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CargoBookingCommandService {

    private final CargoRepository cargoRepository;
    private final ExternalCargoRoutingService externalCargoRoutingService;

    public CargoBookingCommandService(CargoRepository cargoRepository, ExternalCargoRoutingService externalCargoRoutingService) {
        this.cargoRepository = cargoRepository;
        this.externalCargoRoutingService = externalCargoRoutingService;
    }

    public BookingId bookCargo(BookCargoCommand bookCargoCommand) {
        String randomBookingId = UUID.randomUUID().toString().toUpperCase();
        bookCargoCommand.setBookingId(randomBookingId.substring(0, randomBookingId.indexOf("-")));
        Cargo cargo = new Cargo(bookCargoCommand);
        cargoRepository.save(cargo);
        return new BookingId(randomBookingId);
    }

    public void assignRouteToCargo(RouteCargoCommand routeCargoCommand) {
        Cargo cargo = cargoRepository.findByBookingId(new BookingId(routeCargoCommand.getCargoBookingId()));
        Itinerary itinerary = externalCargoRoutingService.fetchRouteForSpecification(
                new RouteSpecification(
                        new Location(routeCargoCommand.getOriginLocation()),
                        new Location(routeCargoCommand.getDestinationLocation()),
                        routeCargoCommand.getArrivalDeadline()
                )
        );
        routeCargoCommand.setItinerary(itinerary);
        cargo.assignToRoute(routeCargoCommand);
        cargoRepository.save(cargo);
    }
}
