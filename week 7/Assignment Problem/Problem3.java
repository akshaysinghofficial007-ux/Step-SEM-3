// Problem 3: Backyard Toolshed Routine
abstract class GardenTool {
    public GardenTool() { }
    public abstract String use();
}

class CuttingTool extends GardenTool {
    public CuttingTool() { super(); }

    // GardenTool.use() is abstract, so super.use() cannot be called here
    // (compile error). This is the first concrete message in the hierarchy.
    @Override public String use() { return "Using the tool in the garden, blade sharpened first"; }
}

class Pruner extends CuttingTool {
    public Pruner() { super(); }

    @Override public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}

public class Problem3 {
    public static void main(String[] args) {
        CuttingTool c = new CuttingTool();
        Pruner p = new Pruner();
        System.out.println(c.use());
        System.out.println(p.use());
    }
}
