package com.cargotracker.booking.domain.model.aggregates;

import com.cargotracker.booking.domain.model.commands.BookCargoCommand;
import com.cargotracker.booking.domain.model.commands.RouteCargoCommand;
import com.cargotracker.booking.domain.model.entities.Location;
import com.cargotracker.booking.domain.model.valueobjects.*;
import com.cargotracker.shareddomain.events.CargoBookedEvent;
import com.cargotracker.shareddomain.events.CargoBookedEventData;
import com.cargotracker.shareddomain.events.CargoRoutedEvent;
import com.cargotracker.shareddomain.events.CargoRoutedEventData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
@Getter
@NoArgsConstructor
public class Cargo extends AbstractAggregateRoot<Cargo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cargoId;

    @Embedded
    private BookingId bookingId;

    @Embedded
    private BookingAmount bookingAmount;

    @Embedded
    private Location origin;

    @Embedded
    private RouteSpecification routeSpecification;

    @Embedded
    private Itinerary itinerary;

    @Embedded
    private Delivery delivery;

    public Cargo(BookCargoCommand bookCargoCommand) {
        this.bookingId = new BookingId(bookCargoCommand.getBookingId());
        this.routeSpecification = new RouteSpecification(
                new Location(bookCargoCommand.getOriginLocation()),
                new Location(bookCargoCommand.getDestLocation()),
                bookCargoCommand.getDestArrivalDeadline()
        );
        this.origin = this.routeSpecification.getOrigin();
        this.itinerary = Itinerary.EMPTY;
        this.bookingAmount = new BookingAmount(bookCargoCommand.getBookingAmount());
        this.delivery = new Delivery(this.itinerary, LastCargoHandledEvent.EMPTY);

        this.addDomainEvent(new CargoBookedEvent(new CargoBookedEventData(bookingId.getBookingId())));
    }

    public void assignToRoute(RouteCargoCommand routeCargoCommand) {
        this.itinerary = routeCargoCommand.getItinerary();
        this.delivery = new Delivery(this.itinerary, this.delivery.getLastEvent());

        this.addDomainEvent(new CargoRoutedEvent(new CargoRoutedEventData(bookingId.getBookingId())));
    }

    public void addDomainEvent(Object event) {
        registerEvent(event);
    }
}
