// Problem 4: Hot Weather Alert Windows
public class Problem4 {
    // Sliding window.
    // Time: O(n)   Space: O(1)
    // Recomputing every block's sum from scratch would be O(n * k).
    static int countAlerts(int[] readings, int k, int threshold) {
        long target = (long) k * threshold;          // avoids decimal division
        long sum = 0;
        int count = 0;
        for (int i = 0; i < readings.length; i++) {
            sum += readings[i];                      // new reading enters
            if (i >= k) sum -= readings[i - k];      // old reading leaves
            if (i >= k - 1 && sum >= target) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        System.out.println(countAlerts(readings, 3, 4));   // 3
    }
}
