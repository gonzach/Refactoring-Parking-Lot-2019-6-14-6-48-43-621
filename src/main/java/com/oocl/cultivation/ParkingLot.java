package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }


    public ParkingTicket addCar(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        if ( getAvailableParkingPosition()  == 0 ) {
            return null;
        }
        cars.put(parkingTicket, car);
        return parkingTicket;
    }


    public Car getCar(ParkingTicket ticket) {
        Car car = cars.get(ticket);
        cars.remove(ticket);
        return car;
    }

    public Map<ParkingTicket, Car> getCars() {
        return cars;
    }

    public int countCars() {
        return cars.size();
    }

    public int getCapacity() {
        return this.capacity;
    }

}
