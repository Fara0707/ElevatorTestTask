public class Passenger {

    private int floor;

    public Passenger(int floor) {
     this.floor = floor;
    }

    public static int generateFloor(int currFloor, int maxFloor){
        int floor;

        do {
            floor = 1 + (int) (Math.random() * maxFloor);
        } while (currFloor + 1 == floor);

        return floor;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
