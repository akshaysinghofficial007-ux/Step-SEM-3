import java.util.Arrays;

// Problem 2: Merging Two Token Queues
public class Problem2 {
    // Two-pointer merge.
    // Time: O(m + n)   Space: O(m + n) for the output (O(1) extra beyond it)
    // Join + sort would cost O((m + n) log(m + n)) because it ignores the existing order.
    static int[] mergeTokens(int[] a, int[] b) {
        int[] out = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) out[k++] = a[i++];
            else out[k++] = b[j++];
        }
        while (i < a.length) out[k++] = a[i++];
        while (j < b.length) out[k++] = b[j++];
        return out;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeTokens(new int[]{3, 8, 15, 20}, new int[]{5, 8, 12})));
        System.out.println(Arrays.toString(mergeTokens(new int[]{}, new int[]{4, 9})));
    }
}
