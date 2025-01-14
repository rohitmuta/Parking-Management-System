# Parking-Management-System
This is a simple Parking Management System built with Java and Spring Boot. It allows you to manage parking slots and cars, with functionalities for parking cars, viewing parked cars, and checking available parking slots.

Parking Management System
This is a simple Parking Management System built with Java and Spring Boot. It allows you to manage parking slots and cars, with functionalities for parking cars, viewing parked cars, and checking available parking slots.

Features:
Park Car: Park a car by specifying its license plate and the parking slot.
Unpark Car: Unpark a car by providing its license plate.
History: Get history of a parked car by providing its id.
Get Parked Cars: Retrieve a list of all parked cars.
Get Available Slots: Retrieve a list of all available parking slots.
Automatic Car Addition: If a car is not already in the system, it is automatically added when parking.

Technologies Used:
Java
Spring Boot
Spring Data JPA (for database interaction)
Spring Security
MySQL (for data storage)

Endpoints:
POST /api/parking/park: Park a car by providing its license plate and slot number.
POST /api/parking/unpark: Unpark a car by providing its license plate.
GET /api/parking/history: Get history of a parked car by providing its id.
GET /api/parking/parked-cars: Get all parked cars.
GET /api/parking/available-slots: Get all available parking slots.

Database:
The system uses MySQL to store the data of cars and parking slots.
