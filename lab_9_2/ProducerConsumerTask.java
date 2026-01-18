import java.util.concurrent.atomic.AtomicInteger;

class CircularBuffer {
    private final String[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;

    public CircularBuffer(int size) {
        this.buffer = new String[size];
    }

    public synchronized void put(String item) throws InterruptedException {
        while (count == buffer.length) {
            wait(); 
        }
        buffer[tail] = item;
        tail = (tail + 1) % buffer.length;
        count++;
        notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (count == 0) {
            wait(); // Буфер порожній
        }
        String item = buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        notifyAll();
        return item;
    }
}

public class ProducerConsumerTask {
    public static void main(String[] args) throws InterruptedException {
        CircularBuffer buffer1 = new CircularBuffer(10);
        CircularBuffer buffer2 = new CircularBuffer(10);

        for (int i = 1; i <= 5; i++) {
            int threadId = i;
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        buffer1.put("Потік № " + threadId + " згенерував повідомлення");
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
            t.setDaemon(true);
            t.start();
        }

        for (int i = 1; i <= 2; i++) {
            int threadId = i;
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        String msg = buffer1.take();
                        buffer2.put("Потік № " + threadId + " переклав повідомлення (" + msg + ")");
                    }
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
            t.setDaemon(true);
            t.start();
        }

        for (int i = 1; i <= 100; i++) {
            System.out.println(i + ": " + buffer2.take());
        }

        System.out.println("Програма завершена.");
    }
}