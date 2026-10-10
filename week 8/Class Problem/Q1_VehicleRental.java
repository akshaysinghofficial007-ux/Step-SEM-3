import java.util.Locale;

// Question 1: Vehicle Rental System
public class Q1_VehicleRental {

    static class Customer {
        private final String name;
        Customer(String name) { this.name = name; }
        String getName() { return name; }
    }

    // ---- Each vehicle category defines its own charge rule ----
    static abstract class Vehicle {
        private final String name;
        private boolean available = true;

        Vehicle(String name) { this.name = name; }
        String getName() { return name; }
        boolean isAvailable() { return available; }
        void markRented() { available = false; }
        void markReturned() { available = true; }

        abstract double calculateCharge(int days);
    }
    static class Sedan extends Vehicle {
        Sedan(String n) { super(n); }
        double calculateCharge(int days) { return 40.0 * days; }
    }
    static class SUV extends Vehicle {
        SUV(String n) { super(n); }
        double calculateCharge(int days) {
            double c = 60.0 * days;
            return days >= 5 ? c * 0.90 : c;                // 10% off for 5+ days
        }
    }
    static class Truck extends Vehicle {
        Truck(String n) { super(n); }
        double calculateCharge(int days) { return 100.0 * days + 50.0; }   // flat loading fee
    }
    // A new category = a new Vehicle subclass; Rental logic is unchanged.

    static class Rental {
        private final Vehicle vehicle;
        private final Customer customer;
        private final int days;
        private boolean active = true;

        Rental(Vehicle vehicle, Customer customer, int days) {
            this.vehicle = vehicle; this.customer = customer; this.days = days;
        }
        Customer getCustomer() { return customer; }
        double getCharge() { return vehicle.calculateCharge(days); }

        void end() {
            if (!active) throw new IllegalStateException("Rental already ended.");
            active = false;
            vehicle.markReturned();
        }
    }

    // ---- Common rental processing ----
    static Rental rent(Customer c, Vehicle v, int days) {
        if (days <= 0) throw new IllegalArgumentException("days must be positive");
        if (!v.isAvailable()) {
            System.out.println(v.getName() + " is currently unavailable.");
            return null;
        }
        v.markRented();
        Rental r = new Rental(v, c, days);
        System.out.println(String.format(Locale.US, "%s rented successfully by %s. Rental charge: $%.2f.",
            v.getName(), c.getName(), r.getCharge()));
        return r;
    }

    static void giveBack(Rental r, Vehicle v) {
        r.end();
        System.out.println(v.getName() + " returned by " + r.getCustomer().getName() + ".");
    }

    public static void main(String[] args) {
        Customer c1 = new Customer("Customer 1"), c2 = new Customer("Customer 2"), c3 = new Customer("Customer 3");
        Vehicle sedanA = new Sedan("Sedan A"), suvB = new SUV("SUV B");

        Rental r1 = rent(c1, sedanA, 3);
        rent(c2, sedanA, 2);              // unavailable
        giveBack(r1, sedanA);
        rent(c3, suvB, 5);
    }
}
