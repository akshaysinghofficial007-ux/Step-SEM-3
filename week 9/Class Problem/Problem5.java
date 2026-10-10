// Problem 5: Maximize Area Between Two Boundaries
public class Problem5 {
    // Brute force: every pair of walls.   Time: O(n^2)   Space: O(1)
    static int maxAreaBruteForce(int[] h) {
        int best = 0;
        for (int i = 0; i < h.length; i++)
            for (int j = i + 1; j < h.length; j++)
                best = Math.max(best, Math.min(h[i], h[j]) * (j - i));
        return best;
    }

    // Two pointers from both ends.        Time: O(n)     Space: O(1)
    // Moving the taller wall inward can never help (width shrinks, height is capped by the shorter
    // wall), so we always move the shorter one in hope of finding a taller wall.
    static int maxContainerArea(int[] h) {
        int left = 0, right = h.length - 1, best = 0;
        while (left < right) {
            best = Math.max(best, Math.min(h[left], h[right]) * (right - left));
            if (h[left] < h[right]) left++;
            else right--;
        }
        return best;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxContainerArea(heights));       // 49
        System.out.println(maxAreaBruteForce(heights));      // 49
    }
}
