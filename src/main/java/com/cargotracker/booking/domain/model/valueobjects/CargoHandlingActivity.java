package com.cargotracker.booking.domain.model.valueobjects;

import com.cargotracker.booking.domain.model.entities.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CargoHandlingActivity {

    @Column(name = "next_expected_handling_event_type")
    private String type;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "next_expected_location_id"))
    private Location location;

    @Column(name = "next_expected_voyage_id")
    private Voyage voyage;

    public CargoHandlingActivity(String type, Location location) {
        this.type = type;
        this.location = location;
    }
}
