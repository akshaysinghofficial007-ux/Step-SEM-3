import java.util.HashMap;
import java.util.Map;

// Problem 3: Most Popular Canteen Order
public class Problem3 {
    // Hash map: Time O(n), Space O(k) where k = number of distinct items.
    // Re-scanning the list for every item would be O(n * k) <= O(n^2) time with O(1) space.
    static Object[] mostPopular(String[] orders) {
        Map<String, Integer> count = new HashMap<>();
        for (String o : orders) count.merge(o, 1, Integer::sum);

        String best = null;
        int bestCount = 0;
        for (String o : orders) {                    // original order => first item wins ties
            if (count.get(o) > bestCount) {
                bestCount = count.get(o);
                best = o;
            }
        }
        return new Object[]{ best, bestCount };
    }

    static void show(String[] orders) {
        Object[] r = mostPopular(orders);
        System.out.println("(\"" + r[0] + "\", " + r[1] + ")");
    }

    public static void main(String[] args) {
        show(new String[]{"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"});   // ("dosa", 3)
        show(new String[]{"tea", "coffee", "coffee", "tea"});                        // ("tea", 2)
    }
}
