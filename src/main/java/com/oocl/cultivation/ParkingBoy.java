package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLotList = new ArrayList<>();
    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    private static final String CONST_NOT_ENOUGH_POSITION = "Not enough position.";
    private static final String CONST_PROVIDE_TICKET = "Please provide your parking ticket.";
    private static final String CONST_UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";


    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        parkingLotList.add(parkingLot);
    }

    public ParkingTicket park(Car car) {
        if (parkingLot.getAvailableParkingPosition() != 0)
            return parkingLot.addCar(car);
        else {
            ParkingLot availableParkingLot = parkingLotList.stream()
                    .filter(parkingLot -> parkingLot.countCars() != parkingLot.getCapacity())
                    .findFirst().orElse(null);
            if (availableParkingLot == null){
                setLastErrorMessage(CONST_NOT_ENOUGH_POSITION);
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
        if ( ticket == null ) {
            setLastErrorMessage(CONST_PROVIDE_TICKET);
        } else if ( fetchedTicketCar == null ) {
            setLastErrorMessage(CONST_UNRECOGNIZED_PARKING_TICKET);
        }
        return fetchedTicketCar;
    }

    public String getLastErrorMessage() {
       return lastErrorMessage;
    }

    public void addParkingLot(ParkingLot parkingLot){
        parkingLotList.add(parkingLot);
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }
}
