// Problem 1: The Talking Toy Box
abstract class Toy {
    private static int counter = 1000;      // shared static counter
    private final String toyId;             // final, assigned once in constructor
    protected final String name;

    protected Toy(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("name must not be blank");
        this.name = name;
        this.toyId = "TOY-" + (++counter);  // advances once per Toy object
    }

    public abstract String makeSound();

    String getToyId() { return toyId; }
}

class ToyCar extends Toy {
    public ToyCar(String name) { super(name); }
    @Override public String makeSound() { return name + ": Vroom vroom!"; }
}

class ToyRobot extends Toy {
    public ToyRobot(String name) { super(name); }
    @Override public String makeSound() { return name + ": Beep boop!"; }
}

public class Problem1 {
    public static void main(String[] args) {
        // Toy t = new Toy("x");  // COMPILE ERROR: Toy is abstract; cannot be instantiated
        ToyCar c = new ToyCar("Speedster");
        ToyRobot r = new ToyRobot("Bolt");
        System.out.println(c.makeSound());
        System.out.println(r.makeSound());
        System.out.println(c.getToyId());
        System.out.println(r.getToyId());
    }
}
