package com.cargotracker.booking.interfaces.rest.transform;

import com.cargotracker.booking.domain.model.commands.BookCargoCommand;
import com.cargotracker.booking.interfaces.rest.dto.BookCargoDTO;

public class BookCargoCommandDTOAssembler {

    public static BookCargoCommand toCommandFromDTO(BookCargoDTO bookCargoDTO) {
        return new BookCargoCommand(
                bookCargoDTO.getBookingAmount(),
                bookCargoDTO.getOriginLocation(),
                bookCargoDTO.getDestLocation(),
                bookCargoDTO.getDestArrivalDeadline()
        );
    }
}
