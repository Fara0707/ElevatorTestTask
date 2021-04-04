import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElevatorTest {
    private final int maxFloor = 10;
    private final int minPassengers = 2, maxPassengers = 5;
    private final int elevCapacity = 5;
    Building building;
    Elevator elevator;

    @BeforeEach
    void setUp() {

       building = new Building.Builder()
                .maxFloor(maxFloor)
                .elevatorCapacity(elevCapacity)
                .build();

        elevator = new Elevator(elevCapacity, building);

    }


    @Test
    void dirIfLiftIsEmpty1() {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(4));
        passengers.add(new Passenger(5));
        passengers.add(new Passenger(7));
        passengers.add(new Passenger(1));
        passengers.add(new Passenger(2));
        passengers.add(new Passenger(6));

        elevator.setCurrFloor(2);
        elevator.directionIfElevatorIsEmpty(passengers);
        assertEquals(1, elevator.getDirection());
    }

    @Test
    void dirIfLiftIsEmpty2() {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(1));
        passengers.add(new Passenger(1));
        passengers.add(new Passenger(1));
        passengers.add(new Passenger(2));
        passengers.add(new Passenger(3));
        passengers.add(new Passenger(7));

        elevator.setCurrFloor(7);
        elevator.directionIfElevatorIsEmpty(passengers);
        assertEquals( -1, elevator.getDirection());
    }

    @Test
    void addPassInLift1() {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(4));
        passengers.add(new Passenger(5));
        passengers.add(new Passenger(7));
        passengers.add(new Passenger(1));
        passengers.add(new Passenger(2));
        passengers.add(new Passenger(6));
        passengers.add(new Passenger(6));

        elevator.setCurrFloor(8);
        elevator.directionIfElevatorIsEmpty(passengers);
        elevator.addPassengerInElevator(passengers);

        assertEquals(5, elevator.getPassengerInElevator().size());
    }

    @Test
    void addPassInLift2() {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(7));
        passengers.add(new Passenger(1));
        passengers.add(new Passenger(2));
        passengers.add(new Passenger(6));
        passengers.add(new Passenger(6));

        elevator.setCurrFloor(3);
        elevator.directionIfElevatorIsEmpty(passengers);
        elevator.addPassengerInElevator(passengers);

        assertEquals(3, elevator.getPassengerInElevator().size());
    }
}