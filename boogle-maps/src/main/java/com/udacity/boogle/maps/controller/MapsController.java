package com.udacity.boogle.maps.controller;

import com.udacity.boogle.maps.service.MapsService;
import com.udacity.boogle.maps.service.MockAddressRepository;

import com.udacity.boogle.maps.entity.Address;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maps")
public class MapsController {
    private final MapsService mapsService;

    public MapsController(MapsService mapsService) {
        this.mapsService = mapsService;
    }

    @GetMapping
    public Address get(@RequestParam Long vehicleId) {
        return mapsService.getAddress(vehicleId);
    }

    @PostMapping
    public Address save(@RequestParam Long vehicleId, @RequestParam Double lat, @RequestParam Double lon) {
        return mapsService.saveAddress(vehicleId, lat, lon);
    }

    @DeleteMapping
    public void delete(@RequestParam Long vehicleId) {
        mapsService.deleteAddress(vehicleId);
    }
}
