// Problem 5: Skyline Delivery Fleet
abstract class Drone {
    protected final String id;
    public Drone(String id) {
        if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("id must not be blank");
        this.id = id;
    }
    public abstract String fly();
}

interface Trackable {
    String getLocation();
}

class DeliveryDrone extends Drone implements Trackable {
    public DeliveryDrone(String id) { super(id); }
    @Override public String fly() { return id + " flying a delivery route"; }
    @Override public String getLocation() { return id + " at Sector 4"; }
}

class ScoutDrone extends Drone {            // sibling of DeliveryDrone, NOT Trackable
    public ScoutDrone(String id) { super(id); }
    @Override public String fly() { return id + " scouting the area"; }
}

class GroundRobot implements Trackable {    // Trackable only, unrelated to Drone
    private final String id;
    public GroundRobot(String id) {
        if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("id must not be blank");
        this.id = id;
    }
    @Override public String getLocation() { return id + " at Sector 4"; }
}

public class Problem5 {
    static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable t = (Trackable) o;     // safe cast after instanceof
            return t.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        System.out.println(getLocationIfTrackable(new DeliveryDrone("DR-1")));
        System.out.println(getLocationIfTrackable(new ScoutDrone("SC-1")));
        System.out.println(getLocationIfTrackable(new GroundRobot("GR-1")));
    }
}
