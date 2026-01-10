import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MaxWordsFinder {
    public static void main(String[] args) {
        String filePath = "input.txt";

        try {
            List<String> allLines = Files.readAllLines(Paths.get(filePath));

            // Знаходимо максимальну кількість слів серед усіх рядків
            int maxWords = allLines.stream()
                    .mapToInt(line -> line.trim().isEmpty() ? 0 : line.trim().split("\\s+").length)
                    .max()
                    .orElse(0);

            // Якщо слів взагалі немає (maxWords == 0), просто виводимо повідомлення
            if (maxWords == 0) {
                System.out.println("Файл порожній або не містить слів.");
                return;
            }

            // Знаходимо всі рядки з такою кількістю слів
            List<String> resultLines = allLines.stream()
                    .filter(line -> !line.trim().isEmpty() && line.trim().split("\\s+").length == maxWords)
                    .collect(Collectors.toList());

            System.out.println("Максимальна кількість слів: " + maxWords);
            resultLines.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Помилка читання: " + e.getMessage());
        }
    }
}