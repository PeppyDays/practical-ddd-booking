package com.cargotracker.booking.domain.model.valueobjects;

import com.cargotracker.booking.domain.model.entities.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Getter
@NoArgsConstructor
public class Delivery {

    @Enumerated(EnumType.STRING)
    @Column(name = "routing_status")
    private RoutingStatus routingStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "transport_status")
    private TransportStatus transportStatus;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "last_known_location_id"))
    private Location lastKnownLocation;

    @Embedded
    @AttributeOverride(name = "voyageNumber", column = @Column(name = "current_voyage_id"))
    private Voyage currentVoyage;

    @Embedded
    private LastCargoHandledEvent lastEvent;

    @Embedded
    private CargoHandlingActivity nextExpectedActivity;

    public Delivery(Itinerary itinerary, LastCargoHandledEvent lastCargoHandledEvent) {
        this.lastEvent = lastCargoHandledEvent;
        this.routingStatus = this.calculateRoutingStatus(itinerary);
        this.transportStatus = this.calculateTransportStatus();
        this.lastKnownLocation = this.calculateLastKnownLocation();
        this.currentVoyage = this.calculateCurrentVoyage();
    }

    private RoutingStatus calculateRoutingStatus(Itinerary itinerary) {
        if (itinerary == Itinerary.EMPTY || itinerary == null)
            return RoutingStatus.NOT_ROUTED;
        else
            return RoutingStatus.ROUTED;
    }

    private TransportStatus calculateTransportStatus() {
        if (this.lastEvent.getHandlingEventType() == null)
            return TransportStatus.NOT_RECEIVED;

        return switch (this.lastEvent.getHandlingEventType()) {
            case "LOAD" -> TransportStatus.ONBOARD_CARRIER;
            case "UNLOAD", "RECEIVE", "CUSTOMS" -> TransportStatus.IN_PORT;
            case "CLAIM" -> TransportStatus.CLAIMED;
            default -> TransportStatus.UNKNOWN;
        };
    }

    private Location calculateLastKnownLocation() {
        if (this.lastEvent != null)
            return new Location(lastEvent.getHandlingEventLocation());
        else
            return null;
    }

    private Voyage calculateCurrentVoyage() {
        if (getTransportStatus().equals(TransportStatus.ONBOARD_CARRIER) && lastEvent != null)
            return new Voyage(lastEvent.getHandlingEventVoyage());
        else
            return null;
    }
}
