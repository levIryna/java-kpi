import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<?>> carsInRoad = new ArrayList<>();

    // Підрахунок всіх людей на дорозі 
    public int getCountOfHumans() {
        int count = 0;
        for (Vehicle<?> car : carsInRoad) {
            count += car.getOccupiedSeats();
        }
        return count;
    }

    // Додавання машини на дорогу 
    public void addCarToRoad(Vehicle<?> car) {
        carsInRoad.add(car);
    }
}