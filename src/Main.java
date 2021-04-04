public class Main {

    public static void main(String[] args) {
	new Main().run();
    }

    private void run() {

        int minFloors = 5;
        int maxFloors = 20;
        int maxFloor = Util.randomBetweenTwo(minFloors, maxFloors);

        int elevCapacity = 5;
        Building building = new Building.Builder()
                .maxFloor(maxFloor)
                .elevatorCapacity(elevCapacity)
                .build();

        int minPassengers = 0;
        int maxPassengers = 10;
        building.addPassengersInsideBuilding(minPassengers, maxPassengers);

        building.getElevator().elevatorStart();
    }

}
