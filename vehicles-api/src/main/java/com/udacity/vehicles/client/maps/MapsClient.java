package com.udacity.vehicles.client.maps;

import com.udacity.vehicles.domain.Location;

import java.util.Objects;

import com.udacity.vehicles.domain.car.Car;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Implements a class to interface with the Maps Client for location data.
 */
@Component
public class MapsClient {

    private static final Logger log = LoggerFactory.getLogger(MapsClient.class);

    private final WebClient client;
    private final ModelMapper mapper;

    public MapsClient(@Qualifier("maps") WebClient maps, ModelMapper mapper) {
        this.client = maps;
        this.mapper = mapper;
    }

    /**
     * Gets an address from the Maps client, given latitude and longitude.
     *
     * @param car An object containing "vehicleId" and "lat" and "lon" of location
     * @return An updated location including street, city, state and zip,
     * or an exception message noting the Maps service is down
     */
    public Location getAddress(Car car) {
        try {
            Address address = client
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/maps/")
                            .queryParam("vehicleId", car.getId())
                            .build()
                    )
                    .retrieve().bodyToMono(Address.class).block();

            mapper.map(Objects.requireNonNull(address), car.getLocation());

            return car.getLocation();
        } catch (Exception e) {
            log.warn("Map service is down");
            return car.getLocation();
        }
    }

    public Location saveAddress(Car car) {
        try {
            Address address = client
                    .post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/maps")
                            .queryParam("vehicleId", car.getId())
                            .queryParam("lat", car.getLocation().getLat())
                            .queryParam("lon", car.getLocation().getLon())
                            .build()
                    )
                    .retrieve().bodyToMono(Address.class).block();
            mapper.map(Objects.requireNonNull(address), car.getLocation());
            return car.getLocation();
        } catch (Exception e) {
            log.warn("Map service is down");
            return car.getLocation();
        }
    }
}
