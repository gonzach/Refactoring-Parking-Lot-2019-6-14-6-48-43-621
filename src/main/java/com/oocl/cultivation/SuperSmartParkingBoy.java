package com.oocl.cultivation;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = getParkingLotList().stream()
                .reduce(((parkingLot1, parkingLot2) -> getParkingLot(parkingLot1, parkingLot2)))
                .orElse(null);

        if (parkingLot == null) {
            setLastErrorMessage(super.getConstNotEnoughPosition());
            return null;
        }
        return parkingLot.addCar(car);
    }

    private ParkingLot getParkingLot(ParkingLot parkingLot1, ParkingLot parkingLot2) {
        return parkingLot1.getAvailableParkingPosition() / parkingLot1.getCapacity() <=
                parkingLot2.getAvailableParkingPosition() / parkingLot2.getCapacity() ? parkingLot1 : parkingLot2;
    }
}
