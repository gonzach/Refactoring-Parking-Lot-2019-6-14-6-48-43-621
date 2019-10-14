package com.oocl.cultivation;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy{



    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingTicket park(Car car){
        ParkingLot parkingLot = getParkingLotList().stream()
                .reduce(((parkingLot1, parkingLot2) -> parkingLot1.countCars() <= parkingLot2.countCars() ? parkingLot1 : parkingLot2))
                .orElse(null);

        if (parkingLot == null) {
            setLastErrorMessage("Not enough position.");
            return null;
        }
        return parkingLot.addCar(car);
    }
}
