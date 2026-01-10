import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CuratorJournal {
    private static ArrayList<JournalEntry> journal = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Додати запис");
            System.out.println("2. Показати всі записи");
            System.out.println("3. Вихід");
            System.out.print("Виберіть дію: ");
            
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                addNewEntry();
            } else if (choice.equals("2")) {
                showJournal();
            } else if (choice.equals("3")) {
                break;
            }
        }
    }

    private static void addNewEntry() {
        // Отримуємо дані з валідацією
        String lastName = inputWithValidation("Прізвище: ", "^[A-ZА-ЯІЇЄ][a-zа-яіїє']+$");
        String firstName = inputWithValidation("Ім'я: ", "^[A-ZА-ЯІЇЄ][a-zа-яіїє']+$");
        String birthDate = inputWithValidation("Дата народження (дд.мм.рррр): ", "^\\d{2}\\.\\d{2}\\.\\d{4}$");
        String phone = inputWithValidation("Телефон (+380...): ", "^\\+380\\d{9}$");
        
        System.out.println("Введіть адресу:");
        String street = inputWithValidation("  Вулиця: ", "^[A-ZА-ЯІЇЄa-zа-яіїє\\s0-9]+$");
        String house = inputWithValidation("  Будинок: ", "^\\d+[a-zA-Zа-яА-Я]?$");
        String apartment = inputWithValidation("  Квартира: ", "^\\d+$");

        String fullAddress = "вул. " + street + ", буд. " + house + ", кв. " + apartment;

        // Якщо всі дані пройшли валідацію, створюємо об'єкт
        journal.add(new JournalEntry(lastName, firstName, birthDate, phone, fullAddress));
        System.out.println("Запис успішно додано!");
    }

    private static String inputWithValidation(String message, String regex) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (Pattern.matches(regex, input)) {
                return input;
            } else {
                System.out.println("Помилка! Неправильний формат. Спробуйте ще раз.");
            }
        }
    }

    private static void showJournal() {
        if (journal.isEmpty()) {
            System.out.println("Журнал порожній.");
        } else {
            System.out.println("\n--- Список записів ---");
            for (JournalEntry entry : journal) {
                System.out.println(entry);
            }
        }
    }
}