import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Building {

    private int maxFloor;
    private Elevator elevator;
    private List<List<Passenger>> passengers = new ArrayList<>();

    public static class Builder{
        private Building newBuilding;

        public Builder() {
            newBuilding = new Building();
        }

        public Builder maxFloor(int maxFloor){
            newBuilding.maxFloor = maxFloor;
            return this;
        }

        public Builder elevatorCapacity(int maxCapacity){
            newBuilding.elevator = new Elevator(maxCapacity, newBuilding);
            return this;
        }

        public Building build() {
            return newBuilding;
        }
    }

    public void addPassengersInsideBuilding(int minPassengers, int maxPassengers) {

        int[] numPassengersOnFloor = IntStream.range(0, maxFloor).map(i -> Util.randomBetweenTwo(minPassengers, maxPassengers)).toArray();

        for(int i = 0; i < maxFloor; i++){
            List <Passenger> passengersOnFloor = new ArrayList<>();
            for(int j = 0; j < numPassengersOnFloor[i]; j++){
                passengersOnFloor.add(new Passenger(Passenger.generateFloor(i, maxFloor)));
            }
            passengers.add(passengersOnFloor);
        }
    }

    public void printPassengers(){
        for (int i = 0; i < passengers.size(); i++) {
            List<Passenger> passenger = passengers.get(i);
            System.out.print((i+1) + "| ");
            for (Passenger value : passenger) {
                System.out.print(value.getFloor() + " ");
            }
            System.out.println();
        }
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public List<List<Passenger>> getPassengers() {
        return passengers;
    }
}