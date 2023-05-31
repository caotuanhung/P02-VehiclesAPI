package com.udacity.boogle.maps.service;

import com.udacity.boogle.maps.entity.Address;
import com.udacity.boogle.maps.exception.AddressNotFound;
import com.udacity.boogle.maps.exception.SavingAddressException;
import com.udacity.boogle.maps.repository.MapsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MapsService {
    private MapsRepository mapsRepository;
    public MapsService(MapsRepository mapsRepository) {
        this.mapsRepository = mapsRepository;
    }
    public Address saveAddress(Long vehicleId, Double lat, Double lon) {
        try {
            Address address = MockAddressRepository.getRandom();
            address.setVehicleId(vehicleId);
            return mapsRepository.save(address);
        } catch (Exception e) {
            throw new SavingAddressException("Unexpected exception when saving address for vehicle ".concat(vehicleId.toString()));
        }
    }

    public Address getAddress(Long vehicleId) {
        Optional<Address> optionalAddress = mapsRepository.findByVehicleId(vehicleId);
        if (optionalAddress.isEmpty()) {
            throw new AddressNotFound("Address not found for vehicle ".concat(vehicleId.toString()));
        }
        return optionalAddress.get();
    }
}
