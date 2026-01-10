import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        try {
            System.out.println("--- Етап 1: Ініціалізація транспорту та людей ---");
            // Створюємо транспорт 
            Bus bus = new Bus(20);
            FireTruck fireTruck = new FireTruck(4);
            System.out.println("Створено автобус (20 місць) та пожежну машину (4 місця) ");
            
            // Створюємо людей 
            Human person = new Human("Іван");
            Fireman fireman = new Fireman("Степан");
            System.out.println("Створено пасажирів: " + person.getName() + " та " + fireman.getName());

            System.out.println("\n--- Етап 2: Тестування посадки пасажирів ---");
            // Тестуємо посадку 
            bus.addPassenger(person);
            System.out.println("Іван сів у автобус. Зайнято місць: " + bus.getOccupiedSeats() + " ");
            
            fireTruck.addPassenger(fireman);
            System.out.println("Степан (пожежник) сів у пожежну машину. Зайнято місць: " + fireTruck.getOccupiedSeats() + " ");
            
            System.out.println("\n--- Етап 3: Робота на дорозі (Wildcards) ---");
            // Демонстрація роботи Road 
            Road road = new Road();
            road.addCarToRoad(bus);
            road.addCarToRoad(fireTruck);
            System.out.println("Машини виїхали на дорогу");
            System.out.println("Загальна кількість людей на дорозі: " + road.getCountOfHumans() + );

            System.out.println("\n--- Етап 4: Серіалізація даних у файл ---");
            // Збереження списку пасажирів у файл 
            List<Human> passengers = new ArrayList<>();
            passengers.add(person);
            passengers.add(fireman);
            
            System.out.println("Підготовка до збереження списку з " + passengers.size() + " людей у файл...");
            saveToFile(passengers, "data.ser");
            System.out.println("Дані успішно збережено у файл 'data.ser' ");
            
        } catch (Exception e) {
            // Обробка виключних ситуацій (переповнення місць тощо) 
            System.err.println("\n[ПОМИЛКА]: " + e.getMessage());
        }
    }

    // Серіалізація об'єктів у файл 
    public static void saveToFile(List<Human> data, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        }
    }
}