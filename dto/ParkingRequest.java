package com.example.parking_system.dto;

public class ParkingRequest {
    private String licensePlate;
    private String slotNumber;

    // Getters and Setters
    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public String getSlotNumber() {
        return slotNumber;
    }
    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }
}

