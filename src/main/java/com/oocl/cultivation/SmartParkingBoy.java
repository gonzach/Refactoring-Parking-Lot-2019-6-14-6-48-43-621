package com.oocl.cultivation;

public class SmartParkingBoy extends ParkingBoy{

    private static final String CONST_NOT_ENOUGH_POSITION = "Not enough position.";

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingTicket park(Car car){
        ParkingLot parkingLot = getParkingLotList().stream()
                .reduce(((parkingLot1, parkingLot2) -> getParkingLot(parkingLot1, parkingLot2)))
                .orElse(null);

        if (parkingLot == null) {
            setLastErrorMessage(CONST_NOT_ENOUGH_POSITION);
            return null;
        }
        return parkingLot.addCar(car);
    }

    private ParkingLot getParkingLot(ParkingLot parkingLot1, ParkingLot parkingLot2) {
        return parkingLot1.countCars() <= parkingLot2.countCars() ? parkingLot1 : parkingLot2;
    }
}
