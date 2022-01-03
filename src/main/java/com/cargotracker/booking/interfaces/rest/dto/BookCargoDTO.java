package com.cargotracker.booking.interfaces.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCargoDTO {

    private int bookingAmount;
    private String originLocation;
    private String destLocation;
    private LocalDateTime destArrivalDeadline;
}
