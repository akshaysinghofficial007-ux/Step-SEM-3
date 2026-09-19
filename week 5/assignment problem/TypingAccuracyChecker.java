public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int matches = 0;
        int totalLength = original.length();
        int firstMismatchPos = -1;

        for (int i = 0; i < totalLength; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matches++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1;
            }
        }

        double accuracy = ((double) matches / totalLength) * 100;

        if (firstMismatchPos == -1) {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | No Mismatches%n", 
                              matches, totalLength, accuracy);
        } else {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d ('%c' vs '%c')%n", 
                              matches, totalLength, accuracy, firstMismatchPos, 
                              original.charAt(firstMismatchPos - 1), typed.charAt(firstMismatchPos - 1));
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}