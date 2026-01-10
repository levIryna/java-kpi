import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.Random;

public class ForkJoinSumApp {

    // Клас рекурсивної задачі
    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 20; 
        private int[] array;
        private int start;
        private int end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            // Якщо елементів менше порогу, рахуємо суму прямо тут
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                // Інакше — ділимо задачу навпіл
                int middle = (start + end) / 2;
                SumTask leftTask = new SumTask(array, start, middle);
                SumTask rightTask = new SumTask(array, middle, end);


                leftTask.fork();
                long rightResult = rightTask.compute();
                // Чекаємо завершення лівої частини та додаємо результати
                long leftResult = leftTask.join();

                return leftResult + rightResult;
            }
        }
    }

    public static void main(String[] args) {
        int size = 1_000_000;
        int[] array = new int[size];
        Random random = new Random();

        // Ініціалізація масиву випадковими числами [0, 100]
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(101);
        }

        // Створення пулу потоків
        ForkJoinPool pool = new ForkJoinPool();
        
        // Вимірювання часу та обчислення суми
        long startTime = System.currentTimeMillis();
        long totalSum = pool.invoke(new SumTask(array, 0, array.length));
        long endTime = System.currentTimeMillis();

        System.out.println("Сума всіх елементів масиву: " + totalSum);
        System.out.println("Час виконання (ForkJoin): " + (endTime - startTime) + " мс");
        
        // Перевірка (звичайний цикл для контролю)
        long checkSum = 0;
        for (int val : array) checkSum += val;
        System.out.println("Контрольна сума: " + checkSum);
    }
}