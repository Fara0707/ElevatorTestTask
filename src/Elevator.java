import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Elevator {

    private int elevCapacity;
    //по условию всегда с первого
    private int currFloor = 1;
    private int direction = 1;
    private Building building;
    private List<Passenger> passengerInElevator = new ArrayList<>();

    public Elevator(int elevCapacity, Building building) {
        this.elevCapacity = elevCapacity;
        this.building = building;
    }

    public void elevatorStart(){
        int step = 0;

        Scanner scanner = new Scanner(System.in);
        while (true){
            step++;
            System.out.println("Шаг " + step);
            printPassengersInElevator();
            building.printPassengers();

            move();

            //чтобы удобнее было разглядывать кадры
            scanner.nextLine();
        }
    }

    private void move() {
        //если уперлись вниз или вверх, то меняем направление
        turnIfEnds();

        //запомнить выходящих людей с лифта на нужный этаж
        List<Passenger> outPassengers = outPassengers();

        passengerInElevator.removeIf(passenger -> passenger.getFloor() == currFloor);

        List<Passenger> passengers = building.getPassengers().get(currFloor-1);

        directionIfElevatorIsEmpty(passengers);

        addPassengerInElevator(passengers);

        //тем кто вышли ставим новый этаж, уже после того как взяли новых,
        //т.к дела нужно же какие-то сделать, не сразу же обратно в очередь
        outPassengers.forEach(passenger -> passenger.setFloor(Passenger.generateFloor(currFloor-1, building.getMaxFloor())));
        building.getPassengers().get(currFloor-1).addAll(outPassengers);

        //перемещаем лифт
        currFloor += direction;
    }


    private List<Passenger> outPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        for (Passenger value : passengerInElevator) {
            if (value.getFloor() == currFloor) passengers.add(value);
        }
        return passengers;
    }

    private void turnIfEnds() {
        if(currFloor == building.getMaxFloor() || currFloor <= 1){
            direction*=-1;
        }
    }

    public void addPassengerInElevator(List<Passenger> passengers) {
        Iterator<Passenger> iterator = passengers.iterator();
        while (iterator.hasNext()){
            if (elevCapacity > passengerInElevator.size()) {
                Passenger pass = iterator.next();
                boolean isUP = direction == 1 && pass.getFloor() > currFloor;
                boolean isDown = direction == -1 && pass.getFloor() < currFloor;
                if (isUP || isDown) {
                    passengerInElevator.add(pass);
                    iterator.remove();
                }
            }
            else return;
        }
    }

    public void directionIfElevatorIsEmpty(List<Passenger> passengers) {
        // куда двигаться дальше
        if(passengerInElevator.isEmpty()) {
            if (!passengers.isEmpty()) {
                int up = 0;
                int down = 0;
                for (Passenger passenger : passengers) {
                    if (passenger.getFloor() > currFloor) up++;
                    if (passenger.getFloor() < currFloor) down++;
                }
                direction = up > down ? 1 : -1;
            }
        }
    }

    private void printPassengersInElevator() {
        System.out.print("Сейчас лифт на " + currFloor + "\nЕдем " + (direction == 1 ? " ^ "  : " V "));
        System.out.print("Внутри | ");
        for (Passenger passenger : passengerInElevator) {
            System.out.print(passenger.getFloor() + " ");
        }
        System.out.println(" |");
    }

    //для тестов
    public void setCurrFloor(int currFloor) {
        this.currFloor = currFloor;
    }

    public int getDirection() {
        return direction;
    }

    public List<Passenger> getPassengerInElevator() {
        return passengerInElevator;
    }
}
