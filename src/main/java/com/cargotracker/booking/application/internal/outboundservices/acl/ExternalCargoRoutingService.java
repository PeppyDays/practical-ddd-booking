package com.cargotracker.booking.application.internal.outboundservices.acl;

import com.cargotracker.booking.domain.model.entities.Location;
import com.cargotracker.booking.domain.model.valueobjects.Itinerary;
import com.cargotracker.booking.domain.model.valueobjects.Leg;
import com.cargotracker.booking.domain.model.valueobjects.RouteSpecification;
import com.cargotracker.booking.domain.model.valueobjects.Voyage;
import com.cargotracker.shareddomain.TransitEdge;
import com.cargotracker.shareddomain.TransitPath;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExternalCargoRoutingService {

    public Itinerary fetchRouteForSpecification(RouteSpecification routeSpecification) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("origin", routeSpecification.getOrigin().getUnLocCode());
        params.put("destination", routeSpecification.getDestination().getUnLocCode());
        params.put("arrivalDeadline", routeSpecification.getArrivalDeadline().toString());

        TransitPath transitPath = restTemplate.getForObject(
                "<<ROUTING_SERVICE_URL>>/cargorouting/",
                TransitPath.class,
                params
        );
        List<Leg> legs;

        if (transitPath != null) {
            legs = new ArrayList<>(transitPath.getTransitEdges().size());

            for (TransitEdge edge : transitPath.getTransitEdges())
                legs.add(this.toLeg(edge));
        } else {
            legs = new ArrayList<>();
        }

        return new Itinerary(legs);
    }

    private Leg toLeg(TransitEdge edge) {
        return new Leg(
                new Voyage(edge.getVoyageNumber()),
                new Location(edge.getFromUnLoCode()),
                new Location(edge.getToUnLoCode()),
                edge.getFromDate(),
                edge.getToDate()
        );
    }
}
