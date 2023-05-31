package com.udacity.pricing.repository;

import com.udacity.pricing.domain.price.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "vehicle-price", path = "prices")
public interface PriceRepository extends CrudRepository<Price, Long> {
    @RestResource(rel = "find-by-vehicle-id", path = "find-by-vehicle-id")
    Optional<Price> findByVehicleId(@Param("vehicleId") Long vehicleId);
}
