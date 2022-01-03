package com.cargotracker.shareddomain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CargoBookedEventData {

    private String bookingId;
}
