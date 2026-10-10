// Problem 4: Smart Kitchen Assistant
abstract class KitchenTool {
    private int speedLevel = 1;   // private; valid range 1-5

    public KitchenTool() { }

    public abstract String prepare();

    int getSpeedLevel() { return speedLevel; }

    void setSpeedLevel(int speedLevel) {
        if (speedLevel < 1 || speedLevel > 5)          // reject first...
            throw new IllegalArgumentException("speedLevel must be between 1 and 5");
        this.speedLevel = speedLevel;                  // ...assign second
    }
}

interface Washable {
    String clean();
}

class Blender extends KitchenTool implements Washable {
    public Blender() { super(); }
    @Override public String prepare() { return "Blending at speed " + getSpeedLevel(); }
    @Override public String clean() { return "Blender rinsed and dried"; }
}

public class Problem4 {
    public static void main(String[] args) {
        Blender b = new Blender();
        b.setSpeedLevel(3);
        System.out.println(b.getSpeedLevel());

        try {
            b.setSpeedLevel(9);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("Speed level stays " + b.getSpeedLevel());

        System.out.println(b.prepare());
        System.out.println(b.clean());
    }
}
