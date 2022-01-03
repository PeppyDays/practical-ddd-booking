package com.cargotracker.shareddomain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransitEdge implements Serializable {

    private String voyageNumber;
    private String fromUnLoCode;
    private String toUnLoCode;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
