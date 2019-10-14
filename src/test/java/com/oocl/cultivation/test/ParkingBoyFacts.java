package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_park_car_to_parking_lot_by_parking_boy_and_return_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingboy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        ParkingTicket car1_parkingTicket = parkingboy.park(car1);

        assertNotNull(car1_parkingTicket);
    }

    @Test
    void should_customer_give_the_parking_ticket_back_to_parking_boy_to_fetch_the_car() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingboy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        ParkingTicket car1_parkingTicket = parkingboy.park(car1);

        Car fetchedCar = parkingboy.fetch(car1_parkingTicket);

        assertNotNull(fetchedCar);
    }

    @Test
    void should_parking_boy_can_park_multiple_cars_into_the_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car car1 = new Car();
        ParkingTicket car1_parkingTicket = parkingBoy.park(car1);
        Car fetchedCar1 = parkingBoy.fetch(car1_parkingTicket);

        Car car2 = new Car();
        ParkingTicket car2_parkingTicket = parkingBoy.park(car2);
        Car fetchedCar2 = parkingBoy.fetch(car2_parkingTicket);

        assertEquals( fetchedCar1, car1);
        assertEquals( fetchedCar2, car2);
    }

    @Test
    void should_check_if_customer_gives_wrong_ticket_then_no_car_should_be_fetch() {
        ParkingLot parkinglot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);

        Car car1 = new Car();
        parkingBoy.park(car1);
        Car fetchedCar = parkingBoy.fetch(new ParkingTicket());

        assertNull(fetchedCar);
    }

    @Test
    void should_check_if_customer_gives_used_ticket_then_no_car_should_be_fetch() {
        ParkingLot parkinglot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
        Car car1 = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car1);

        Car fetchCar = parkingBoy.fetch(parkingTicket);
        Car fetchCarAgain = parkingBoy.fetch(parkingTicket);

        assertNotNull(fetchCar);
        assertNull(fetchCarAgain);
    }

    @Test
    void should_parking_boy_should_not_park_car_if_exceed_capacity() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        for(int i = 0; i< 10; i++){
            parkingBoy.park(new Car());
        }

        Car car1 = new Car();
        ParkingTicket car1_parkingTicket = parkingBoy.park(car1);

        assertNull(car1_parkingTicket);
    }

    @Test
    void should_get_response_message_if_customer_gives_wrong_ticket() {
        ParkingLot parkinglot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);

        Car fetchCar = parkingBoy.fetch(new ParkingTicket());

        assertNull(fetchCar);
        assertEquals(parkingBoy.getLastErrorMessage(), "Unrecognized parking ticket.");
    }

    @Test
    void should_get_response_message_when_customer_does_not_provide_ticket_when_fetching_car() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car fetchCar = parkingBoy.fetch(null);

        assertNull(fetchCar);
        assertEquals(parkingBoy.getLastErrorMessage(), "Please provide your parking ticket.");
    }

    @Test
    void should_parking_boy_will_park_cars_to_the_second_parking_lot_when_the_first_parking_lot_is_full() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1);
        for(int i = 0; i< 10; i++){
            parkingBoy.park(new Car());
        }

        Car car = new Car();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingBoy = new ParkingBoy(parkingLot2);
        parkingBoy.park(car);

        assertEquals(parkingLot2.getCars().containsValue(car), Boolean.TRUE);
    }

    @Test
    void should_smart_parking_boys_will_always_park_cars_to_the_parking_lot_which_contains_more_empty_positions() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLot1);
        ParkingBoy parkingBoy2 = new ParkingBoy(parkingLot2);

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();

        parkingBoy1.park(car1);
        parkingBoy1.park(car2);
        parkingBoy1.park(car3);
        parkingBoy1.park(car4);

        parkingBoy2.park(car1);
        parkingBoy2.park(car2);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);

        smartParkingBoy.park(new Car());

        assertEquals(4, parkingLot1.countCars());
        assertEquals(3, parkingLot2.countCars());
    }

    @Test
    void should_super_smart_parking_boys_will_always_park_cars_to_the_parking_lot_which_has_a_larger_available_position_rate() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLot1);
        ParkingBoy parkingBoy2 = new ParkingBoy(parkingLot2);

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();

        parkingBoy1.park(car1);
        parkingBoy1.park(car2);
        parkingBoy1.park(car3);
        parkingBoy1.park(car4);

        parkingBoy2.park(car1);
        parkingBoy2.park(car2);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot1);
        superSmartParkingBoy.addParkingLot(parkingLot2);

        superSmartParkingBoy.park(new Car());

        assertEquals(5, parkingLot1.countCars());
        assertEquals(2, parkingLot2.countCars());
    }



}
