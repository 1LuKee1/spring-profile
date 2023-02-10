package com.example.springprofile.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        return new ResponseEntity<>(service.createVehicle(vehicle), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicleList = service.getAllVehicles();
        return new ResponseEntity<>(vehicleList, HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> findVehicleById(@PathVariable Long vehicleId) {
        Vehicle vehicle = service.findVehicleById(vehicleId);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @DeleteMapping("{vehicleId}")
    public ResponseEntity<?> deleteVehicleById(@PathVariable Long vehicleId) {
        Vehicle vehicle = service.findVehicleById(vehicleId);
        service.deleteVehicleById(vehicle.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
