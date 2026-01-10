import java.util.HashMap;
import java.util.Scanner;

class Translator {
    private HashMap<String, String> dictionary;

    public Translator() {
        this.dictionary = new HashMap<>();
    }

    // Метод для додавання пари слів
    public void addWord(String english, String ukrainian) {
        dictionary.put(english.toLowerCase().trim(), ukrainian.trim());
    }

    // Метод для перекладу фрази
    public String translate(String phrase) {
        if (phrase == null || phrase.isEmpty()) {
            return "";
        }

        // Розбиваємо фразу на окремі слова
        String[] words = phrase.split("\\s+");
        StringBuilder translatedPhrase = new StringBuilder();

        for (String word : words) {
            // Очищуємо слово від знаків пунктуації для пошуку в словнику
            String cleanWord = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
            
            // Шукаємо переклад, якщо не знайшли — залишаємо оригінал у дужках
            String translation = dictionary.getOrDefault(cleanWord, "[" + word + "]");
            translatedPhrase.append(translation).append(" ");
        }

        return translatedPhrase.toString().trim();
    }
}

public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator();
        Scanner scanner = new Scanner(System.in);

        // Початкове наповнення словника
        translator.addWord("best", "найкращий");
        translator.addWord("university", "університет");
        translator.addWord("world", "світ");
        translator.addWord("is", "це");
        translator.addWord("in", "в");

        System.out.println("--- Англо-український перекладач ---");
        
        // Введення нових слів користувачем
        System.out.print("Бажаєте додати нове слово в словник? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("Введіть англійське слово: ");
            String eng = scanner.nextLine();
            System.out.print("Введіть український переклад: ");
            String ukr = scanner.nextLine();
            translator.addWord(eng, ukr);
            System.out.println("Слово додано!");
        }

        // Переклад фрази
        System.out.println("\nВведіть фразу для перекладу англійською мовою:");
        String inputPhrase = scanner.nextLine();
        
        String result = translator.translate(inputPhrase);
        System.out.println("Переклад: " + result);
    }
}