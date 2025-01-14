package com.example.parking_system.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ParkingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private ParkingSlot parkingSlot;

    private LocalDateTime parkingTime;
    private LocalDateTime unParkingTime;

    // Getters and setters
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Car getCar(){
        return car;
    }
    public void setCar(Car car){
        this.car = car;
    }
    public ParkingSlot getParkingSlot(){
        return parkingSlot;
    }
    public void setParkingSlot(ParkingSlot parkingSlot){
        this.parkingSlot = parkingSlot;
    }
    public LocalDateTime getParkingTime(){
        return parkingTime;
    }
    public void setParkingTime(LocalDateTime parkingTime){
        this.parkingTime = parkingTime;
    }
    public LocalDateTime getUnParkingTime(){
        return unParkingTime;
    }
    public void setUnParkingTime(LocalDateTime unParkingTime){
        this.unParkingTime = unParkingTime;
    }
}

