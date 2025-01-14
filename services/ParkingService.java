package com.example.parking_system.services;

import com.example.parking_system.entities.Car;
import com.example.parking_system.entities.ParkingHistory;
import com.example.parking_system.entities.ParkingSlot;
import com.example.parking_system.repositories.CarRepository;
import com.example.parking_system.repositories.ParkingHistoryRepository;
import com.example.parking_system.repositories.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private ParkingHistoryRepository parkingHistoryRepository;

    public List<Car> getAllParkedCars() {
        return carRepository.findByisParked(true);
    }

    public List<ParkingSlot> getAllAvailableSlots() {
        return parkingSlotRepository.findByIsOccupied(false);
    }

    public ParkingHistory parkCar(String licensePlate, String slotNumber) {
        // Check if the car exists; create it if not
        Car car = carRepository.findByLicensePlate(licensePlate)
                .orElseGet(() -> {
                    Car newCar = new Car();
                    newCar.setLicensePlate(licensePlate);
                    newCar.setisParked(false);
                    return carRepository.save(newCar);
                });

        // Check if the parking slot exists
        ParkingSlot parkingSlot = parkingSlotRepository.findBySlotNumber(slotNumber)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        // Check if the slot is already occupied
        if (parkingSlot.isOccupied()) {
            throw new RuntimeException("Parking Slot already occupied");
        }

        // Mark the slot as occupied
        parkingSlot.setOccupied(true);
        parkingSlotRepository.save(parkingSlot);

        // Mark the car as parked and save it
        car.setisParked(true);
        carRepository.save(car);

        // Create a parking history entry
        ParkingHistory parkingHistory = new ParkingHistory();
        parkingHistory.setCar(car);
        parkingHistory.setParkingSlot(parkingSlot);
        parkingHistory.setParkingTime(LocalDateTime.now());

        return parkingHistoryRepository.save(parkingHistory);
    }


    public ParkingHistory unParkCar(String licensePlate) {
        Car car = carRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        ParkingHistory history = parkingHistoryRepository.findByCarId(car.getId())
                .stream()
                .filter(h -> h.getUnParkingTime() == null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Car is not parked"));

        ParkingSlot parkingSlot = history.getParkingSlot();
        parkingSlot.setOccupied(false);
        parkingSlotRepository.save(parkingSlot);

        history.setUnParkingTime(LocalDateTime.now());
        return parkingHistoryRepository.save(history);
    }
    public List<ParkingHistory> getParkingHistory(Long carId) {
        return parkingHistoryRepository.findByCarId(carId);
    }

}

