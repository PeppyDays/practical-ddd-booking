package com.cargotracker.booking.interfaces.rest;

import com.cargotracker.booking.application.internal.commandservices.CargoBookingCommandService;
import com.cargotracker.booking.application.internal.queryservices.CargoBookingQueryService;
import com.cargotracker.booking.domain.model.aggregates.BookingId;
import com.cargotracker.booking.domain.model.aggregates.Cargo;
import com.cargotracker.booking.interfaces.rest.dto.BookCargoDTO;
import com.cargotracker.booking.interfaces.rest.transform.BookCargoCommandDTOAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CargoBookingController {

    private final CargoBookingCommandService cargoBookingCommandService;
    private final CargoBookingQueryService cargoBookingQueryService;

    public CargoBookingController(CargoBookingCommandService cargoBookingCommandService, CargoBookingQueryService cargoBookingQueryService) {
        this.cargoBookingCommandService = cargoBookingCommandService;
        this.cargoBookingQueryService = cargoBookingQueryService;
    }

    @PostMapping("/bookcargo")
    @ResponseBody
    public BookingId bookCargo(@RequestBody BookCargoDTO bookCargoDTO) {
        return cargoBookingCommandService.bookCargo(
                BookCargoCommandDTOAssembler.toCommandFromDTO(bookCargoDTO)
        );
    }

    @GetMapping("/findcargo")
    @ResponseBody
    public Cargo findByBookingId(@RequestParam("bookingId") String bookingId) {
        return cargoBookingQueryService.find(bookingId);
    }
}
