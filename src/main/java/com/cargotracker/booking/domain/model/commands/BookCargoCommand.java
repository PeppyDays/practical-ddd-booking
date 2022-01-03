package com.cargotracker.booking.domain.model.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookCargoCommand {

    private String bookingId;
    private Integer bookingAmount;
    private String originLocation;
    private String destLocation;
    private LocalDateTime destArrivalDeadline;

    public BookCargoCommand(Integer bookingAmount, String originLocation, String destLocation, LocalDateTime destArrivalDeadline) {
        this.bookingAmount = bookingAmount;
        this.originLocation = originLocation;
        this.destLocation = destLocation;
        this.destArrivalDeadline = destArrivalDeadline;
    }
}
