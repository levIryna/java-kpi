import java.io.Serializable;

// Базовий клас Людина 
public class Human implements Serializable {
    private String name;
    public Human(String name) { this.name = name; }
    public String getName() { return name; }
}

// Пожежник 
class Fireman extends Human {
    public Fireman(String name) { super(name); }
}

// Поліцейський 
class Policeman extends Human {
    public Policeman(String name) { super(name); }
}