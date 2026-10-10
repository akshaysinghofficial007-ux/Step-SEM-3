import java.util.*;

// Question 3: Online Examination System
public class Q3_OnlineExam {

    static class Student {
        private final String name;
        Student(String name) { this.name = name; }
        String getName() { return name; }
    }

    // ---- Each question type evaluates itself ----
    static abstract class Question {
        private final int id;
        private final String text;
        private final int points;

        Question(int id, String text, int points) { this.id = id; this.text = text; this.points = points; }
        int getId() { return id; }
        int getPoints() { return points; }
        abstract boolean isCorrect(String answer);
        int evaluate(String answer) { return answer != null && isCorrect(answer) ? points : 0; }
    }

    static class MultipleChoiceQuestion extends Question {
        private final String correctOption;
        MultipleChoiceQuestion(int id, String text, int points, String correctOption) {
            super(id, text, points); this.correctOption = correctOption;
        }
        boolean isCorrect(String a) { return correctOption.equalsIgnoreCase(a.trim()); }
    }

    static class TrueFalseQuestion extends Question {
        private final boolean correct;
        TrueFalseQuestion(int id, String text, int points, boolean correct) {
            super(id, text, points); this.correct = correct;
        }
        boolean isCorrect(String a) { return Boolean.parseBoolean(a.trim()) == correct; }
    }

    static class ShortAnswerQuestion extends Question {
        private final String expected;
        ShortAnswerQuestion(int id, String text, int points, String expected) {
            super(id, text, points); this.expected = expected;
        }
        boolean isCorrect(String a) { return expected.equalsIgnoreCase(a.trim()); }
    }
    // New question type = new Question subclass; Attempt/Exam code stays the same.

    static class Examination {
        private final String name;
        private final List<Question> questions = new ArrayList<>();
        private final Set<Student> submitted = new HashSet<>();   // one submitted attempt per student

        Examination(String name) { this.name = name; }
        String getName() { return name; }
        void addQuestion(Question q) { questions.add(q); }
        List<Question> getQuestions() { return questions; }
        int maxScore() { int t = 0; for (Question q : questions) t += q.getPoints(); return t; }

        Attempt start(Student s) {
            if (submitted.contains(s))
                throw new IllegalStateException(s.getName() + " has already submitted " + name + ".");
            return new Attempt(s, this);
        }
        void markSubmitted(Student s) { submitted.add(s); }
    }

    enum State { IN_PROGRESS, SUBMITTED }

    static class Attempt {
        private final Student student;
        private final Examination exam;
        private final Map<Integer, String> answers = new LinkedHashMap<>();
        private State state = State.IN_PROGRESS;

        Attempt(Student s, Examination e) { this.student = s; this.exam = e; }

        void answer(int questionId, String value) {
            if (state == State.SUBMITTED)
                throw new IllegalStateException("Cannot change answers for a submitted examination.");
            answers.put(questionId, value);
        }

        String submit() {
            if (state == State.SUBMITTED) throw new IllegalStateException("Already submitted.");
            state = State.SUBMITTED;
            exam.markSubmitted(student);
            StringBuilder sb = new StringBuilder("Result: ");
            int total = 0;
            for (Question q : exam.getQuestions()) {
                int pts = q.evaluate(answers.get(q.getId()));
                total += pts;
                sb.append("Question ").append(q.getId()).append(": ")
                  .append(pts > 0 ? "Correct" : "Incorrect").append(" (").append(pts).append(" points), ");
            }
            sb.append("Total score: ").append(total).append("/").append(exam.maxScore()).append(".");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Examination examA = new Examination("Exam A");
        examA.addQuestion(new MultipleChoiceQuestion(1, "Which keyword makes a class abstract?", 5, "C"));
        examA.addQuestion(new TrueFalseQuestion(2, "An interface can have instance fields.", 5, false));

        Student s1 = new Student("Student 1");
        Attempt attempt = examA.start(s1);
        System.out.println("Exam A started by Student 1.");

        attempt.answer(1, "C");
        System.out.println("Answer recorded for Question 1.");
        attempt.answer(2, "True");
        System.out.println("Answer recorded for Question 2.");

        String result = attempt.submit();
        System.out.println("Exam A submitted by Student 1.");
        System.out.println(result);

        try {
            attempt.answer(1, "B");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
