package com.cargotracker.booking.domain.model.commands;

import com.cargotracker.booking.domain.model.valueobjects.Itinerary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RouteCargoCommand {

    private String cargoBookingId;
    private String originLocation;
    private String destinationLocation;
    private LocalDateTime arrivalDeadline;
    private Itinerary itinerary;

    public RouteCargoCommand(String cargoBookingId, String originLocation, String destinationLocation, LocalDateTime arrivalDeadline) {
        this.cargoBookingId = cargoBookingId;
        this.originLocation = originLocation;
        this.destinationLocation = destinationLocation;
        this.arrivalDeadline = arrivalDeadline;
    }
}
