package com.example.springprofile.vehicle;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
class VehicleService {

    private final VehicleRepository repository;


    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return repository.save(vehicle);
    }


    public List<Vehicle> getAllVehicles() {
        return repository.findAll();
    }


    public Vehicle findVehicleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle with id " + id + " doesn't exist."));
    }


    @Transactional
    public void deleteVehicleById(Long id) {
        if (id == 0) {
            throw new RuntimeException("You need to provide ID of student to be deleted. ID can not be 0.");
        }
        Optional<Vehicle> checkIfStudentWithIdExist = repository.findById(id);
        if (checkIfStudentWithIdExist.isEmpty()) {
            throw new RuntimeException(
                    "Student can not be deleted because student with id: " + id + " doesn't exist."
            );
        }
        repository.deleteVehicleById(id);
    }

}
