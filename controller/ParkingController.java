package com.example.parking_system.controller;

import com.example.parking_system.dto.ParkingRequest;
import com.example.parking_system.entities.Car;
import com.example.parking_system.entities.ParkingHistory;
import com.example.parking_system.entities.ParkingSlot;
import com.example.parking_system.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/park")
    public ResponseEntity<String> parkCar(@RequestBody ParkingRequest parkingRequest) {
        try {
            String licensePlate = parkingRequest.getLicensePlate();
            String slotNumber = parkingRequest.getSlotNumber();

            ParkingHistory history = parkingService.parkCar(licensePlate, slotNumber);
            return ResponseEntity.ok("Car parked successfully at slot " + slotNumber);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/unpark")
    public ResponseEntity<String> unParkCar(@RequestParam String licensePlate) {
        try {
            ParkingHistory history = parkingService.unParkCar(licensePlate);
            return ResponseEntity.ok("Car unparked successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/history/{carId}")
    public ResponseEntity<List<ParkingHistory>> getParkingHistory(@RequestParam Long carId) {
        List<ParkingHistory> history = parkingService.getParkingHistory(carId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/parked-cars")
    public ResponseEntity<List<Car>> getAllParkedCars() {
        List<Car> parkedCars = parkingService.getAllParkedCars();
        return ResponseEntity.ok(parkedCars);
    }

    @GetMapping("/available-slots")
    public ResponseEntity<List<ParkingSlot>> getAllAvailableSlots() {
        List<ParkingSlot> availableSlots = parkingService.getAllAvailableSlots();
        return ResponseEntity.ok(availableSlots);
    }
}

