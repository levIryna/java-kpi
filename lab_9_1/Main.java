import java.util.*;
import java.util.concurrent.*;

class Account {
    private final int id;
    private int balance;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    void withdraw(int amount) {
        balance -= amount;
    }

    void deposit(int amount) {
        balance += amount;
    }
}

class Bank {

    public void transfer(Account from, Account to, int amount) {
        if (from == to) return;

        // Фіксований порядок блокування — запобігання deadlock
        Account first = from.getId() < to.getId() ? from : to;
        Account second = from.getId() < to.getId() ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int numAccounts = 100;
        int initialBalance = 1000;
        int numTransfers = 10_000;

        Bank bank = new Bank();
        List<Account> accounts = new ArrayList<>();

        // Створення рахунків
        for (int i = 0; i < numAccounts; i++) {
            accounts.add(new Account(i, initialBalance));
        }

        // Підрахунок суми до переказів
        long totalBefore = accounts.stream()
                .mapToLong(Account::getBalance)
                .sum();

        System.out.println("Total before: " + totalBefore);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Запуск переказів
        for (int i = 0; i < numTransfers; i++) {
            executor.execute(() -> {
                ThreadLocalRandom rand = ThreadLocalRandom.current();

                Account from = accounts.get(rand.nextInt(numAccounts));
                Account to = accounts.get(rand.nextInt(numAccounts));
                int amount = rand.nextInt(50);

                bank.transfer(from, to, amount);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Підрахунок суми після переказів
        long totalAfter = accounts.stream()
                .mapToLong(Account::getBalance)
                .sum();

        System.out.println("Total after: " + totalAfter);
        System.out.println("Success: " + (totalBefore == totalAfter));
    }
}
