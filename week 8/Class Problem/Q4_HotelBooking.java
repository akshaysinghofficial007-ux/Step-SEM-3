import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

// Question 4: Hotel Booking System
public class Q4_HotelBooking {

    static final DateTimeFormatter MON_DAY = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);

    static class Customer {
        private final String name;
        Customer(String name) { this.name = name; }
        String getName() { return name; }
    }

    // ---- Each room category has its own pricing rule ----
    static abstract class Room {
        private final int number;
        private final List<Reservation> reservations = new ArrayList<>();

        Room(int number) { this.number = number; }
        int getNumber() { return number; }
        abstract String category();
        abstract double pricePerNight();
        double calculatePrice(long nights) { return pricePerNight() * nights; }
        String label() { return category() + " Room " + number; }

        // Overlap rule: [in, out) ranges clash if in < otherOut && otherIn < out
        boolean isAvailable(LocalDate in, LocalDate out) {
            for (Reservation r : reservations)
                if (r.isActive() && in.isBefore(r.getCheckOut()) && r.getCheckIn().isBefore(out)) return false;
            return true;
        }
        void addReservation(Reservation r) { reservations.add(r); }
    }
    static class StandardRoom extends Room {
        StandardRoom(int n) { super(n); }
        String category() { return "Standard"; }
        double pricePerNight() { return 100; }
    }
    static class DeluxeRoom extends Room {
        DeluxeRoom(int n) { super(n); }
        String category() { return "Deluxe"; }
        double pricePerNight() { return 180; }
    }
    static class Suite extends Room {
        Suite(int n) { super(n); }
        String category() { return "Suite"; }
        double pricePerNight() { return 300; }
        @Override double calculatePrice(long nights) { return super.calculatePrice(nights) + 50; }  // amenity fee
    }

    static class Reservation {
        private final Customer customer;
        private final Room room;
        private final LocalDate checkIn, checkOut, cancelDeadline;
        private boolean active = true;

        Reservation(Customer c, Room r, LocalDate in, LocalDate out) {
            this.customer = c; this.room = r; this.checkIn = in; this.checkOut = out;
            this.cancelDeadline = in.minusDays(2);       // cancellable until 2 days before check-in
        }
        boolean isActive() { return active; }
        LocalDate getCheckIn() { return checkIn; }
        LocalDate getCheckOut() { return checkOut; }
        double price() { return room.calculatePrice(ChronoUnit.DAYS.between(checkIn, checkOut)); }
        String describe() {
            return customer.getName() + ", " + room.label() + " (" + range(checkIn, checkOut, false) + ")";
        }
        void cancel(LocalDate today) {
            if (!active) throw new IllegalStateException("Reservation already cancelled.");
            if (today.isAfter(cancelDeadline))
                throw new IllegalStateException("Cancellation deadline has passed.");
            active = false;
        }
    }

    static String range(LocalDate a, LocalDate b, boolean words) {
        return words ? MON_DAY.format(a) + " to " + MON_DAY.format(b)
                     : MON_DAY.format(a) + "-" + b.getDayOfMonth();
    }

    // ---- Operations ----
    static void checkAvailability(Room room, LocalDate in, LocalDate out) {
        System.out.println(room.label() + (room.isAvailable(in, out) ? " is available from " : " is not available from ")
            + range(in, out, true) + ".");
    }

    static Reservation reserve(Customer c, Room room, LocalDate in, LocalDate out) {
        if (!out.isAfter(in)) throw new IllegalArgumentException("Check-out must be after check-in.");
        if (!room.isAvailable(in, out)) {
            System.out.println(room.label() + " is not available from " + range(in, out, true) + ".");
            return null;
        }
        Reservation r = new Reservation(c, room, in, out);
        room.addReservation(r);
        System.out.println(String.format(Locale.US, "Reservation confirmed for %s. Price: $%.2f.", r.describe(), r.price()));
        return r;
    }

    public static void main(String[] args) {
        Customer a = new Customer("Customer A"), b = new Customer("Customer B"), c = new Customer("Customer C");
        Room std101 = new StandardRoom(101), dlx201 = new DeluxeRoom(201);
        LocalDate today = LocalDate.of(2025, 12, 20);

        checkAvailability(std101, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        Reservation resA = reserve(a, std101, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        reserve(b, std101, LocalDate.of(2026, 1, 3), LocalDate.of(2026, 1, 7));   // overlaps

        resA.cancel(today);
        System.out.println("Reservation for " + resA.describe() + " cancelled successfully.");

        reserve(c, dlx201, LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 12));
    }
}
