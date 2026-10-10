import java.time.LocalDateTime;
import java.util.*;

// Question 3: The Campus Premiere Ticket Counter
public class Q3_TicketCounter {

    static class Customer {
        private final String name;
        Customer(String name) { this.name = name; }
        String getName() { return name; }
    }

    // ---- Seat categories define their own price ----
    static abstract class Seat {
        private final String id;
        Seat(String id) { this.id = id; }
        String getId() { return id; }
        abstract double getPrice();
    }
    static class RegularSeat extends Seat {
        RegularSeat(String id) { super(id); }
        double getPrice() { return 150; }
    }
    static class PremiumSeat extends Seat {
        PremiumSeat(String id) { super(id); }
        double getPrice() { return 250; }
    }
    static class ReclinerSeat extends Seat {
        ReclinerSeat(String id) { super(id); }
        double getPrice() { return 400; }
    }

    // ---- Show keeps track of which seats are already booked ----
    static class Show {
        private final String name;
        private final LocalDateTime startTime;
        private final Set<String> bookedSeatIds = new HashSet<>();

        Show(String name, LocalDateTime startTime) { this.name = name; this.startTime = startTime; }
        LocalDateTime getStartTime() { return startTime; }
        boolean isAvailable(Seat seat) { return !bookedSeatIds.contains(seat.getId()); }

        Booking book(Customer customer, List<Seat> seats) {
            if (seats.isEmpty() || seats.size() > 6)
                throw new IllegalArgumentException("A booking must have 1 to 6 seats.");
            Set<String> requested = new HashSet<>();
            for (Seat s : seats) {
                if (!requested.add(s.getId()))
                    throw new IllegalArgumentException("Seat " + s.getId() + " selected twice.");
                if (!isAvailable(s))                                   // check ALL seats first...
                    throw new IllegalStateException("Seat " + s.getId() + " is already booked for this show.");
            }
            for (Seat s : seats) bookedSeatIds.add(s.getId());         // ...then reserve
            return new Booking(customer, this, seats);
        }

        void release(List<Seat> seats) {
            for (Seat s : seats) bookedSeatIds.remove(s.getId());
        }
    }

    static class Booking {
        private final Customer customer;
        private final Show show;
        private final List<Seat> seats;
        private boolean cancelled = false;

        Booking(Customer c, Show show, List<Seat> seats) {
            this.customer = c; this.show = show; this.seats = new ArrayList<>(seats);
        }

        double getTotal() {
            double total = 0;
            for (Seat s : seats) total += s.getPrice();
            return total;
        }

        String seatList() {
            StringBuilder sb = new StringBuilder();
            for (Seat s : seats) { if (sb.length() > 0) sb.append(", "); sb.append(s.getId()); }
            return sb.toString();
        }

        void cancel(LocalDateTime now) {
            if (cancelled) throw new IllegalStateException("Booking already cancelled.");
            if (!now.isBefore(show.getStartTime()))
                throw new IllegalStateException("Cannot cancel: the show has already started.");
            show.release(seats);          // all seats become available again
            cancelled = true;
        }
    }

    static Booking book(Show show, Customer c, Seat... seats) {
        try {
            Booking b = show.book(c, Arrays.asList(seats));
            System.out.println(String.format(Locale.US, "Booking confirmed for %s: %s. Total: \u20B9%.2f.",
                c.getName(), b.seatList(), b.getTotal()));
            return b;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        LocalDateTime showTime = LocalDateTime.of(2026, 3, 20, 19, 0);
        LocalDateTime now = LocalDateTime.of(2026, 3, 20, 17, 0);
        Show show = new Show("Campus Premiere", showTime);
        Customer asha = new Customer("Asha"), ravi = new Customer("Ravi"), neha = new Customer("Neha");

        Booking ashaBooking = book(show, asha, new RegularSeat("A1"), new RegularSeat("A2"), new PremiumSeat("F5"));
        book(show, ravi, new RegularSeat("A2"));                 // already booked
        book(show, ravi, new ReclinerSeat("R1"));

        ashaBooking.cancel(now);
        System.out.println(asha.getName() + "'s booking cancelled. Seats " + ashaBooking.seatList() + " released.");

        book(show, neha, new RegularSeat("A2"));
    }
}
