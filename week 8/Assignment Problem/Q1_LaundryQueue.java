import java.util.Locale;

// Question 1: The Hostel Laundry Queue
public class Q1_LaundryQueue {

    // ---- Wash types: each defines its own duration and charge ----
    interface WashType {
        String name();
        int durationMinutes();
        double charge();
    }

    static class QuickWash implements WashType {
        public String name() { return "Quick"; }
        public int durationMinutes() { return 30; }
        public double charge() { return 20; }
    }

    static class NormalWash implements WashType {
        public String name() { return "Normal"; }
        public int durationMinutes() { return 45; }
        public double charge() { return 30; }
    }

    static class HeavyWash implements WashType {
        public String name() { return "Heavy"; }
        public int durationMinutes() { return 60; }
        public double charge() { return 45; }
    }
    // A new type (e.g. Delicate) is just another WashType implementation; nothing else changes.

    static class Student {
        private final String name;
        Student(String name) { this.name = name; }
        String getName() { return name; }
    }

    // ---- One wash: tracks student, machine and wash type ----
    static class WashCycle {
        private final Student student;
        private final WashingMachine machine;
        private final WashType type;
        WashCycle(Student student, WashingMachine machine, WashType type) {
            this.student = student; this.machine = machine; this.type = type;
        }
        Student getStudent() { return student; }
        WashingMachine getMachine() { return machine; }
        WashType getType() { return type; }
        double calculateCharge() { return type.charge(); }
    }

    // ---- Machine: busy status is private and only changed through start/complete ----
    static class WashingMachine {
        private final String id;
        private boolean busy = false;
        private WashCycle current;

        WashingMachine(String id) { this.id = id; }
        String getId() { return id; }
        boolean isBusy() { return busy; }

        WashCycle startWash(Student student, WashType type) {
            if (busy) throw new IllegalStateException("Machine " + id + " is currently busy.");
            current = new WashCycle(student, this, type);
            busy = true;
            return current;
        }

        void completeCycle() {
            if (!busy) throw new IllegalStateException("Machine " + id + " is not running a wash.");
            current = null;
            busy = false;
        }
    }

    // ---- Common booking logic (works for any WashType) ----
    static void startWash(Student s, WashingMachine m, WashType t) {
        try {
            WashCycle c = m.startWash(s, t);
            System.out.println(String.format(Locale.US,
                "%s wash started on %s for %s (%d min). Charge: \u20B9%.2f.",
                t.name(), m.getId(), s.getName(), t.durationMinutes(), c.calculateCharge()));
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    static void complete(WashingMachine m) {
        m.completeCycle();
        System.out.println(m.getId() + " cycle completed. " + m.getId() + " is now free.");
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha"), ravi = new Student("Ravi"), neha = new Student("Neha");
        WashingMachine m1 = new WashingMachine("M1"), m2 = new WashingMachine("M2");

        startWash(asha, m1, new QuickWash());
        startWash(ravi, m1, new HeavyWash());     // M1 busy
        startWash(ravi, m2, new HeavyWash());
        complete(m1);
        startWash(neha, m1, new NormalWash());
    }
}
