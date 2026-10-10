import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Problem 4: Pair With Target Sum (Unsorted Array)
public class Problem4 {
    // Approach 1 - Brute force over all pairs.      Time: O(n^2)       Space: O(1)
    static boolean bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] + nums[j] == target) return true;
        return false;
    }

    // Approach 2 - HashSet of complements.          Time: O(n)         Space: O(n)
    static boolean withHashSet(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int x : nums) {
            if (seen.contains(target - x)) return true;
            seen.add(x);
        }
        return false;
    }

    // Approach 3 - Sort a copy, then two pointers.  Time: O(n log n)   Space: O(n) for the copy
    static boolean withTwoPointers(int[] nums, int target) {
        int[] s = nums.clone();
        Arrays.sort(s);
        int lo = 0, hi = s.length - 1;
        while (lo < hi) {
            long sum = (long) s[lo] + s[hi];
            if (sum == target) return true;
            if (sum < target) lo++;
            else hi--;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15};
        int[] b = {3, 4, 6};
        System.out.println(withHashSet(a, 9) + " " + bruteForce(a, 9) + " " + withTwoPointers(a, 9));      // true true true
        System.out.println(withHashSet(b, 20) + " " + bruteForce(b, 20) + " " + withTwoPointers(b, 20));   // false false false
    }
}
