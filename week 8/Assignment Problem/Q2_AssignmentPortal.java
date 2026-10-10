import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

// Question 2: The Assignment Submission Portal
public class Q2_AssignmentPortal {

    static class Student {
        private final String name;
        Student(String name) { this.name = name; }
        String getName() { return name; }
    }

    // ---- Each assignment type defines its own late-penalty rule ----
    static abstract class Assignment {
        private final String title;
        private final double maxMarks;
        private final LocalDate dueDate;

        Assignment(String title, double maxMarks, LocalDate dueDate) {
            this.title = title; this.maxMarks = maxMarks; this.dueDate = dueDate;
        }
        String getTitle() { return title; }
        double getMaxMarks() { return maxMarks; }
        LocalDate getDueDate() { return dueDate; }

        // Number of days past the due date (0 if on time). Owned by Assignment: it knows its due date.
        long daysLate(LocalDate submittedOn) {
            return Math.max(0, ChronoUnit.DAYS.between(dueDate, submittedOn));
        }

        // Penalty rule specific to the assignment type, as a percentage (0-100).
        abstract double penaltyPercent(long daysLate);

        double applyLatePenalty(double marks, long daysLate) {
            double pct = Math.min(100, penaltyPercent(daysLate));
            return marks * (1 - pct / 100.0);
        }
    }

    static class CodingAssignment extends Assignment {
        CodingAssignment(String t, double max, LocalDate due) { super(t, max, due); }
        @Override double penaltyPercent(long daysLate) { return 10 * daysLate; }
    }

    static class WrittenAssignment extends Assignment {
        WrittenAssignment(String t, double max, LocalDate due) { super(t, max, due); }
        @Override double penaltyPercent(long daysLate) { return 20 * daysLate; }
    }

    // ---- Submission guards its own status ----
    enum Status { SUBMITTED, GRADED }

    static class Submission {
        private final Student student;
        private final Assignment assignment;
        private LocalDate submittedOn;
        private Status status;
        private double finalMarks;

        Submission(Student student, Assignment assignment, LocalDate submittedOn) {
            this.student = student; this.assignment = assignment; this.submittedOn = submittedOn;
            this.status = Status.SUBMITTED;
        }

        Status getStatus() { return status; }
        long daysLate() { return assignment.daysLate(submittedOn); }

        void resubmit(LocalDate newDate) {
            if (status == Status.GRADED)
                throw new IllegalStateException("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            this.submittedOn = newDate;
        }

        // Final marks are calculated at grading time.
        double grade(double awarded) {
            if (status != Status.SUBMITTED)
                throw new IllegalStateException("Only a submitted work can be graded.");
            if (awarded < 0 || awarded > assignment.getMaxMarks())
                throw new IllegalArgumentException("Marks must be between 0 and " + assignment.getMaxMarks());
            finalMarks = assignment.applyLatePenalty(awarded, daysLate());
            status = Status.GRADED;
            return finalMarks;
        }
    }

    // ---- Demo helpers ----
    static Submission submit(Student s, Assignment a, LocalDate date) {
        Submission sub = new Submission(s, a, date);
        long late = sub.daysLate();
        String when = late == 0 ? "on time" : late + " day" + (late > 1 ? "s" : "") + " late";
        System.out.println(s.getName() + "'s submission for '" + a.getTitle() + "' received (" + when + "). Status: Submitted.");
        return sub;
    }

    static void grade(Student s, Assignment a, Submission sub, double awarded) {
        long late = sub.daysLate();
        double result = sub.grade(awarded);
        String text = String.format(Locale.US, "%s graded: %.0f/%.0f", s.getName(), result, a.getMaxMarks());
        if (late > 0) text += String.format(Locale.US, " after %.0f%% late penalty", a.penaltyPercent(late));
        System.out.println(text + ". Status: Graded.");
    }

    public static void main(String[] args) {
        Assignment lab = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        Assignment essay = new WrittenAssignment("Design Essay", 50, LocalDate.of(2026, 3, 12));
        Student asha = new Student("Asha"), ravi = new Student("Ravi");

        Submission ashaSub = submit(asha, lab, LocalDate.of(2026, 3, 10));
        Submission raviSub = submit(ravi, essay, LocalDate.of(2026, 3, 14));

        grade(asha, lab, ashaSub, 45);
        grade(ravi, essay, raviSub, 40);

        try {
            ashaSub.resubmit(LocalDate.of(2026, 3, 15));
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
