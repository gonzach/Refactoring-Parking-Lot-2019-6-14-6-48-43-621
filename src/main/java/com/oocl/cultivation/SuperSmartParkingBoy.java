package com.oocl.cultivation;

import java.util.Comparator;
import java.util.function.Function;

public class SuperSmartParkingBoy extends ParkingBoy {

    private final ParkingLot parkingLot;
    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
        this.parkingLot = parkingLot;
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = getParkingLotList().stream()
                .reduce(((parkingLot1, parkingLot2) -> parkingLot1.getAvailableParkingPosition() / parkingLot1.getCapacity() <=
                        parkingLot2.getAvailableParkingPosition()/parkingLot2.getCapacity() ? parkingLot1 : parkingLot2))
                .orElse(null);

        if (parkingLot == null) {
            setLastErrorMessage("Not enough position.");
            return null;
        }
        return parkingLot.addCar(car);
    }
}
