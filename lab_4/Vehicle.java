import java.util.ArrayList;
import java.util.List;

// Транспортний засіб 
public abstract class Vehicle<T extends Human> {
    private final int maxSeats; 
    private final List<T> passengers = new ArrayList<>();

    public Vehicle(int maxSeats) { this.maxSeats = maxSeats; }

    public int getMaxSeats() { return maxSeats; } 
    public int getOccupiedSeats() { return passengers.size(); } 

    // Посадка пасажира 
    public void addPassenger(T passenger) throws Exception {
        if (passengers.size() >= maxSeats) {
            throw new Exception("Всі місця вже зайнято!");
        }
        passengers.add(passenger);
    }

    // Висадка пасажира 
    public void removePassenger(T passenger) throws Exception {
        if (!passengers.contains(passenger)) {
            throw new Exception("Вказаний пасажир не знаходиться у транспорті!");
        }
        passengers.remove(passenger);
    }
}

// Автобус: може перевозити будь-яких пасажирів 
class Bus extends Vehicle<Human> {
    public Bus(int seats) { super(seats); }
}

// Таксі: може перевозити будь-яких пасажирів 
class Taxi extends Vehicle<Human> {
    public Taxi(int seats) { super(seats); }
}

// Пожежна машина: тільки пожежників 
class FireTruck extends Vehicle<Fireman> {
    public FireTruck(int seats) { super(seats); }
}

// Поліцейська машина: тільки поліцейських 
class PoliceCar extends Vehicle<Policeman> {
    public PoliceCar(int seats) { super(seats); }
}