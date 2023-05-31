package com.udacity.boogle.maps.repository;

import com.udacity.boogle.maps.entity.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MapsRepository extends CrudRepository<Address, Long> {
    Optional<Address> findByVehicleId(Long vehicleId);
}
