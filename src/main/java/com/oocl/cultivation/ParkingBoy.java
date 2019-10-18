package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private static final String CONST_NOT_ENOUGH_POSITION = "Not enough position.";
    private static final String CONST_PROVIDE_TICKET = "Please provide your parking ticket.";
    private static final String CONST_UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";

    private List<ParkingLot> parkingLotList = new ArrayList<>();
    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        addParkingLot(parkingLot);
    }

    public ParkingTicket park(Car car) {
        if (parkingLot.getAvailableParkingPosition() != 0)
            return parkingLot.addCar(car);
        else {
            ParkingLot availableParkingLot = parkingLotList.stream()
                    .filter(parkingLot -> parkingLot.countCars() != parkingLot.getCapacity())
                    .findFirst().orElse(null);
            if (availableParkingLot == null) {
                setLastErrorMessage(getConstNotEnoughPosition());
                return null;
            } else {
                return availableParkingLot.addCar(car);
            }
        }
    }

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }

    public Car fetch(ParkingTicket ticket) {
        Car fetchedTicketCar = parkingLot.getCar(ticket);
        if (ticket == null) {
            setLastErrorMessage(getConstProvideTicket());
        } else if (fetchedTicketCar == null) {
            setLastErrorMessage(getConstUnrecognizedParkingTicket());
        }
        return fetchedTicketCar;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLotList.add(parkingLot);
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public static String getConstProvideTicket() {
        return CONST_PROVIDE_TICKET;
    }

    public static String getConstUnrecognizedParkingTicket() {
        return CONST_UNRECOGNIZED_PARKING_TICKET;
    }

    public static String getConstNotEnoughPosition() {
        return CONST_NOT_ENOUGH_POSITION;
    }

}
