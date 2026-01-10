import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagFrequencyCounter {
    public static void main(String[] args) {
        String urlString = "https://www.google.com"; 
        
        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();

            // Регулярний вираз для пошуку назв тегів
            // Шукає текст відразу після '<' (наприклад, <div -> div)
            Pattern pattern = Pattern.compile("<\\s*([a-zA-Z0-9]+)");
            Matcher matcher = pattern.matcher(content.toString());

            Map<String, Integer> tagMap = new HashMap<>();
            while (matcher.find()) {
                String tagName = matcher.group(1).toLowerCase();
                tagMap.put(tagName, tagMap.getOrDefault(tagName, 0) + 1);
            }

            if (tagMap.isEmpty()) {
                System.out.println("Тегів не знайдено або доступ заблоковано.");
                return;
            }

            // а) В лексикографічному порядку
            System.out.println("--- Теги в лексикографічному порядку ---");
            new TreeMap<>(tagMap).forEach((tag, count) -> System.out.println(tag + ": " + count));

            // b) В порядку зростання частоти
            System.out.println("\n--- Теги за частотою (зростання) ---");
            tagMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }
}