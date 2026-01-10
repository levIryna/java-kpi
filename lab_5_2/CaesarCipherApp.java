import java.io.*;

public class CaesarCipherApp {
    public static void main(String[] args) {
        // Текст для перевірки
        String originalText = "This is text for testing Caesar algorythm";
        char key = (char) 5;
        String filename = "encrypted.txt";

        // а) Шифрування
        try (CaesarWriter cw = new CaesarWriter(new FileWriter(filename), key)) {
            cw.write(originalText);
            System.out.println("Текст зашифровано у файл: " + filename);
        } catch (IOException e) {
            System.err.println("Помилка запису: " + e.getMessage());
        }

        // b) Дешифрування
        System.out.print("Дешифрований текст: ");
        try (CaesarReader cr = new CaesarReader(new FileReader(filename), key)) {
            int data;
            while ((data = cr.read()) != -1) {
                System.out.print((char) data);
            }
            System.out.println(); 
        } catch (IOException e) {
            System.err.println("Помилка читання: " + e.getMessage());
        }
    }
}

// Клас для шифрування
class CaesarWriter extends FilterWriter {
    private final char key;

    public CaesarWriter(Writer out, char key) {
        super(out);
        this.key = key;
    }

    // Шифруємо кожен символ перед записом
    @Override
    public void write(int c) throws IOException {
        out.write(c + key);
    }

    // Додатковий метод для запису цілих рядків
    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(str.charAt(off + i));
        }
    }
}

// Клас для дешифрування
class CaesarReader extends FilterReader {
    private final char key;

    public CaesarReader(Reader in, char key) {
        super(in);
        this.key = key;
    }

    // Дешифруємо символ під час читання
    @Override
    public int read() throws IOException {
        int c = in.read();
        if (c == -1) return -1;
        return c - key;
    }
}