import java.util.*;

// Question 5: The Campus Notice Broadcaster
public class Q5_NoticeBroadcaster {

    // ---- Channel abstraction: each channel delivers in its own way ----
    interface NotificationChannel {
        void send(Student student, String message);
    }
    static class EmailChannel implements NotificationChannel {
        public void send(Student s, String msg) { System.out.println("[Email \u2192 " + s.getName() + "] " + msg + "."); }
    }
    static class SmsChannel implements NotificationChannel {
        public void send(Student s, String msg) { System.out.println("[SMS \u2192 " + s.getName() + "] " + msg + "."); }
    }
    static class AppChannel implements NotificationChannel {
        public void send(Student s, String msg) { System.out.println("[App \u2192 " + s.getName() + "] " + msg + "."); }
    }
    // WhatsAppChannel = one more implementation; NoticeBoard does not change.

    static class Student {
        private final String name;
        private final String department;
        private final List<NotificationChannel> channels = new ArrayList<>();

        Student(String name, String department, NotificationChannel... preferred) {
            this.name = name;
            this.department = department;
            channels.addAll(Arrays.asList(preferred));
        }
        String getName() { return name; }
        String getDepartment() { return department; }
        List<NotificationChannel> getChannels() { return channels; }
    }

    // ---- Notice validates itself ----
    static class Notice {
        private final String title;
        private final Set<String> departments;

        Notice(String title, String... departments) {
            if (title == null || title.trim().isEmpty())
                throw new IllegalArgumentException("A title is required.");
            if (departments == null || departments.length == 0)
                throw new IllegalArgumentException("At least one target department is required.");
            this.title = title;
            this.departments = new LinkedHashSet<>(Arrays.asList(departments));
        }
        String getTitle() { return title; }
        Set<String> getDepartments() { return departments; }
    }

    // ---- NoticeBoard depends only on the NotificationChannel abstraction ----
    static class NoticeBoard {
        private final List<Student> students = new ArrayList<>();

        void register(Student s) { students.add(s); }

        void post(Notice notice) {
            System.out.println("Notice '" + notice.getTitle() + "' posted to " + String.join(", ", notice.getDepartments()) + ".");
            for (Student s : students) {
                if (!notice.getDepartments().contains(s.getDepartment())) continue;
                for (NotificationChannel ch : s.getChannels()) {
                    ch.send(s, notice.getTitle());
                }
            }
        }
    }

    public static void main(String[] args) {
        NotificationChannel email = new EmailChannel(), sms = new SmsChannel(), app = new AppChannel();
        NoticeBoard board = new NoticeBoard();
        board.register(new Student("Asha", "CSE", email, app));
        board.register(new Student("Ravi", "ECE", sms));

        board.post(new Notice("Lab Closed Tomorrow", "CSE"));
        board.post(new Notice("Fee Deadline Extended", "CSE", "ECE"));

        try {
            board.post(new Notice("Sports Day"));            // no target department
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot post notice: " + e.getMessage());
        }
    }
}
