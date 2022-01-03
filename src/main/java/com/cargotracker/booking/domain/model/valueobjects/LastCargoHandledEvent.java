package com.cargotracker.booking.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LastCargoHandledEvent {

    @Embedded
    public static final LastCargoHandledEvent EMPTY = new LastCargoHandledEvent();

    @Column(name = "last_handled_event_id")
    private Integer handlingEventId;

    @Transient
    private String handlingEventType;

    @Transient
    private String handlingEventVoyage;

    @Transient
    private String handlingEventLocation;
}
