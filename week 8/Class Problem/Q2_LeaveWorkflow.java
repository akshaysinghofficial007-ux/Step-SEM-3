import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

// Question 2: Employee Leave Request Workflow
public class Q2_LeaveWorkflow {

    // ---- Each employee type carries its own leave policy ----
    static abstract class Employee {
        private final String name;
        Employee(String name) { this.name = name; }
        String getName() { return name; }
        abstract int maxLeaveDays();

        // Policy check; subclasses may override for special rules.
        void validateLeave(long days) {
            if (days > maxLeaveDays())
                throw new IllegalArgumentException(name + " cannot request more than " + maxLeaveDays() + " days.");
        }
    }
    static class FullTimeEmployee extends Employee {
        FullTimeEmployee(String n) { super(n); }
        int maxLeaveDays() { return 20; }
    }
    static class PartTimeEmployee extends Employee {
        PartTimeEmployee(String n) { super(n); }
        int maxLeaveDays() { return 10; }
    }
    static class Contractor extends Employee {
        Contractor(String n) { super(n); }
        int maxLeaveDays() { return 5; }
    }

    enum Status {
        PENDING("Pending"), APPROVED("Approved"), REJECTED("Rejected");
        final String label;
        Status(String label) { this.label = label; }
    }

    // ---- LeaveRequest owns and guards its state transitions ----
    static class LeaveRequest {
        private static final DateTimeFormatter MON_DAY = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);
        private final Employee employee;
        private final LocalDate start, end;
        private Status status = Status.PENDING;

        LeaveRequest(Employee employee, LocalDate start, LocalDate end) {
            if (end.isBefore(start)) throw new IllegalArgumentException("End date is before start date.");
            employee.validateLeave(ChronoUnit.DAYS.between(start, end) + 1);   // policy enforcement
            this.employee = employee; this.start = start; this.end = end;
        }

        Status getStatus() { return status; }
        String range() { return MON_DAY.format(start) + "-" + end.getDayOfMonth(); }
        String describe() { return employee.getName() + "'s leave request (" + range() + ")"; }

        void approve() { transitionTo(Status.APPROVED); }
        void reject()  { transitionTo(Status.REJECTED); }
        void changeStatus(Status target) { transitionTo(target); }

        // Only PENDING -> APPROVED/REJECTED is allowed.
        private void transitionTo(Status target) {
            if (status != Status.PENDING || target == Status.PENDING)
                throw new IllegalStateException("Cannot change leave request status from "
                    + status.label + " to " + target.label + ".");
            status = target;
        }
    }

    static class Reviewer {
        private final String name;
        Reviewer(String name) { this.name = name; }
        String getName() { return name; }
        void approve(LeaveRequest r) {
            r.approve();
            System.out.println(r.describe() + " approved. Status: " + r.getStatus().label + ".");
        }
        void reject(LeaveRequest r) {
            r.reject();
            System.out.println(r.describe() + " rejected. Status: " + r.getStatus().label + ".");
        }
    }

    static LeaveRequest submit(Employee e, LocalDate s, LocalDate en) {
        LeaveRequest r = new LeaveRequest(e, s, en);
        System.out.println("Leave request submitted for " + e.getName() + " (" + r.range() + "). Status: " + r.getStatus().label + ".");
        return r;
    }

    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John"), jane = new PartTimeEmployee("Jane");
        Reviewer alice = new Reviewer("Alice"), bob = new Reviewer("Bob");

        LeaveRequest johnReq = submit(john, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        alice.approve(johnReq);

        LeaveRequest janeReq = submit(jane, LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 11));
        bob.reject(janeReq);

        try {
            johnReq.changeStatus(Status.PENDING);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
