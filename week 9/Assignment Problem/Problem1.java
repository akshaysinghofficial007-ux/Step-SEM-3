// Problem 1: Class Topper Finder
public class Problem1 {
    // Returns {rowIndex, total} of the student with the highest total (smallest index on ties).
    // Time: O(m * n)   Extra space: O(1)
    static int[] findTopper(int[][] marks) {
        int bestRow = 0, bestTotal = -1;
        for (int i = 0; i < marks.length; i++) {
            int total = 0;
            for (int j = 0; j < marks[i].length; j++) total += marks[i][j];
            if (total > bestTotal) {          // strictly greater keeps the smallest index on ties
                bestTotal = total;
                bestRow = i;
            }
        }
        return new int[]{ bestRow, bestTotal };
    }

    public static void main(String[] args) {
        int[][] marks = { {78, 85, 90}, {88, 92, 79}, {65, 70, 95} };
        int[] r = findTopper(marks);
        System.out.println("(" + r[0] + ", " + r[1] + ")");   // (1, 259)
    }
}
