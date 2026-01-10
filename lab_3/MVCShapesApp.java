import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

// 1. ІНТЕРФЕЙС ТА МОДЕЛЬ
interface Drawable {
    void draw();
}

abstract class Shape implements Drawable {
    protected String shapeColor;
    public Shape(String shapeColor) { this.shapeColor = shapeColor; }
    public abstract double calcArea();
    @Override
    public String toString() {
        return String.format("Колір: %-8s | Площа: %-8.2f", shapeColor, calcArea());
    }
}

class Rectangle extends Shape {
    private double w, h;
    public Rectangle(String c, double w, double h) { super(c); this.w = w; this.h = h; }
    @Override public double calcArea() { return w * h; }
    @Override public void draw() { System.out.println("Draw Rectangle"); }
    @Override public String toString() { return "Rectangle | " + super.toString(); }
}

class Triangle extends Shape {
    private double b, h;
    public Triangle(String c, double b, double h) { super(c); this.b = b; this.h = h; }
    @Override public double calcArea() { return 0.5 * b * h; }
    @Override public void draw() { System.out.println("Draw Triangle"); }
    @Override public String toString() { return "Triangle  | " + super.toString(); }
}

class Circle extends Shape {
    private double r;
    public Circle(String c, double r) { super(c); this.r = r; }
    @Override public double calcArea() { return Math.PI * r * r; }
    @Override public void draw() { System.out.println("Draw Circle"); }
    @Override public String toString() { return "Circle    | " + super.toString(); }
}

// 2. VIEW
class ShapeView {
    public void printShapes(Shape[] shapes) {
        for (Shape s : shapes) System.out.println(s);
    }
    public void printMessage(String m) { System.out.println("\n--- " + m + " ---"); }
    public void printValue(String l, double v) { System.out.printf("%s: %.2f\n", l, v); }
}

// 3. CONTROLLER
class ShapeController {
    private Shape[] shapes;
    private ShapeView view;

    public ShapeController(ShapeView view) {
        this.view = view;
        this.shapes = generateData();
    }

    private Shape[] generateData() {
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        Random r = new Random();
        Shape[] data = new Shape[10];
        for (int i = 0; i < 10; i++) {
            int type = r.nextInt(3);
            String col = colors[r.nextInt(colors.length)];
            if (type == 0) data[i] = new Rectangle(col, r.nextDouble()*10, r.nextDouble()*10);
            else if (type == 1) data[i] = new Triangle(col, r.nextDouble()*10, r.nextDouble()*10);
            else data[i] = new Circle(col, r.nextDouble()*10);
        }
        return data;
    }

    public void run() {
        view.printMessage("Дані");
        view.printShapes(shapes);
        
        double total = 0;
        for(Shape s : shapes) total += s.calcArea();
        view.printValue("Загальна площа", total);

        view.printMessage("Сортування за площею");
        Arrays.sort(shapes, Comparator.comparingDouble(Shape::calcArea));
        view.printShapes(shapes);
    }
}

// 4. ГОЛОВНИЙ КЛАС (Назва файлу повинна бути MVCShapesApp.java)
public class MVCShapesApp {
    public static void main(String[] args) {
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);
        controller.run();
    }
}