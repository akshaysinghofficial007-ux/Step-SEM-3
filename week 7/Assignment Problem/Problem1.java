// Problem 1: Morning Wake-Up Circuit
interface Ringable {
    String ring();
}

class AlarmClock implements Ringable {
    private final String time;
    public AlarmClock(String time) {
        if (time == null || time.trim().isEmpty()) throw new IllegalArgumentException("time must not be blank");
        this.time = time;
    }
    @Override public String ring() { return "Alarm ringing for " + time; }
}

class Doorbell implements Ringable {
    private final String location;
    public Doorbell(String location) {
        if (location == null || location.trim().isEmpty()) throw new IllegalArgumentException("location must not be blank");
        this.location = location;
    }
    @Override public String ring() { return "Doorbell ringing at " + location; }
}

public class Problem1 {
    static void ringAll(Ringable[] devices) {
        for (Ringable d : devices) System.out.println(d.ring());
    }

    public static void main(String[] args) {
        AlarmClock a = new AlarmClock("7:00 AM");
        Doorbell d = new Doorbell("Front Door");
        System.out.println(a.ring());
        System.out.println(d.ring());
        System.out.println("--- ringAll ---");
        ringAll(new Ringable[]{ a, d });
    }
}
