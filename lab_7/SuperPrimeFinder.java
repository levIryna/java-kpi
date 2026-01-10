import java.util.Scanner;

public class SuperPrimeFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть натуральне число n (до 1000): ");
        
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (n > 0 && n <= 1000) {
                int count = countSuperPrimes(n);
                System.out.println("Кількість надпростих чисел у ряді від 1 до " + n + ": " + count);
            } else {
                System.out.println("Будь ласка, введіть число в межах від 1 до 1000.");
            }
        } else {
            System.out.println("Помилка: введено не ціле число.");
        }
    }

    // Головна функція підрахунку надпростих чисел
    public static int countSuperPrimes(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isSuperPrime(i)) {
                System.out.print(i + " "); // Виводимо знайдене число для наочності
                count++;
            }
        }
        System.out.println(); // Новий рядок після списку чисел
        return count;
    }

    // Перевірка, чи є число надпростим
    public static boolean isSuperPrime(int number) {
        if (!isPrime(number)) {
            return false;
        }
        int reversed = reverseNumber(number);
        return isPrime(reversed);
    }

    // Метод для перевірки, чи є число простим
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Метод для запису цифр числа у зворотному порядку
    public static int reverseNumber(int n) {
        int reversed = 0;
        while (n != 0) {
            int digit = n % 10;
            reversed = reversed * 10 + digit;
            n /= 10;
        }
        return reversed;
    }
}