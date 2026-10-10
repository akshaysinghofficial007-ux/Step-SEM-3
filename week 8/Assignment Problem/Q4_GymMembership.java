import java.util.Locale;

// Question 4: The FitZone Membership Desk
public class Q4_GymMembership {

    static final double BASE_RATE = 1000;

    // ---- Each plan has its own fee rule ----
    interface MembershipPlan {
        String name();
        int months();
        double calculateFee();
    }
    static class MonthlyPlan implements MembershipPlan {
        public String name() { return "Monthly"; }
        public int months() { return 1; }
        public double calculateFee() { return BASE_RATE * months(); }
    }
    static class QuarterlyPlan implements MembershipPlan {
        public String name() { return "Quarterly"; }
        public int months() { return 3; }
        public double calculateFee() { return BASE_RATE * months() * 0.90; }   // 10% off
    }
    static class AnnualPlan implements MembershipPlan {
        public String name() { return "Annual"; }
        public int months() { return 12; }
        public double calculateFee() { return BASE_RATE * months() * 0.75; }   // 25% off
    }
    // A Half-Yearly plan = one more class; Membership is untouched.

    static class Member {
        private final String name;
        Member(String name) { this.name = name; }
        String getName() { return name; }
    }

    enum Status {
        ACTIVE("Active"), FROZEN("Frozen"), EXPIRED("Expired");
        final String label;
        Status(String label) { this.label = label; }
    }

    // ---- Membership owns its status and rejects invalid changes ----
    static class Membership {
        private final Member member;
        private final MembershipPlan plan;
        private Status status = Status.ACTIVE;

        Membership(Member member, MembershipPlan plan) { this.member = member; this.plan = plan; }

        Status getStatus() { return status; }
        double getFee() { return plan.calculateFee(); }
        MembershipPlan getPlan() { return plan; }

        void checkIn() {
            if (status != Status.ACTIVE)
                throw new IllegalStateException("Check-in denied: " + member.getName() + "'s membership is " + status.label + ".");
        }
        void freeze() {
            if (status == Status.EXPIRED) throw new IllegalStateException("Cannot freeze an Expired membership.");
            if (status == Status.FROZEN)  throw new IllegalStateException("Membership is already Frozen.");
            status = Status.FROZEN;
        }
        void unfreeze() {
            if (status == Status.EXPIRED) throw new IllegalStateException("Cannot unfreeze an Expired membership.");
            if (status == Status.ACTIVE)  throw new IllegalStateException("Membership is not Frozen.");
            status = Status.ACTIVE;
        }
        void expire() {
            if (status == Status.EXPIRED) throw new IllegalStateException("Membership is already Expired.");
            status = Status.EXPIRED;
        }
    }

    static Membership buy(Member m, MembershipPlan plan) {
        Membership ms = new Membership(m, plan);
        System.out.println(String.format(Locale.US, "%s membership created for %s. Fee: \u20B9%,.2f. Status: %s.",
            plan.name(), m.getName(), ms.getFee(), ms.getStatus().label));
        return ms;
    }

    public static void main(String[] args) {
        Member asha = new Member("Asha"), ravi = new Member("Ravi");
        Membership ashaMs = buy(asha, new QuarterlyPlan());
        Membership raviMs = buy(ravi, new MonthlyPlan());

        try {
            ashaMs.checkIn();
            System.out.println("Asha checked in successfully.");
            ashaMs.freeze();
            System.out.println("Asha's membership frozen. Status: " + ashaMs.getStatus().label + ".");
            ashaMs.checkIn();                                   // denied
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        try {
            raviMs.expire();
            System.out.println("Ravi's membership expired. Status: " + raviMs.getStatus().label + ".");
            raviMs.freeze();                                    // not allowed
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
