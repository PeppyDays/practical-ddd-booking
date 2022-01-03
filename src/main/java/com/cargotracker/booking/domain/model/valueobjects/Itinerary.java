package com.cargotracker.booking.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {

    @Embedded
    public static final Itinerary EMPTY = new Itinerary();

    @ElementCollection
    @CollectionTable(
            name = "leg",
            joinColumns = @JoinColumn(name = "cargo_id"),
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<Leg> legs = Collections.emptyList();
}
