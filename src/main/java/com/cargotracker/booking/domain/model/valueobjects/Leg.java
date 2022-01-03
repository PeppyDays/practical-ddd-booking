package com.cargotracker.booking.domain.model.valueobjects;

import com.cargotracker.booking.domain.model.entities.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Leg {

    @Embedded
    private Voyage voyage;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "load_location_id"))
    private Location loadLocation;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "unload_location_id"))
    private Location unloadLocation;

    @Column(name = "load_time")
    @NotNull
    private LocalDateTime loadTime;

    @Column(name = "unload_time")
    @NotNull
    private LocalDateTime unloadTime;
}
