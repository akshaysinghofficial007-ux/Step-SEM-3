// Problem 2: Warehouse Grid Summary
public class Problem2 {
    // Time: O(m * n)   Additional space: O(1)
    // Returns {total, maxRow, maxCol}; strict ">" keeps the first max in row-major order.
    static int[] warehouseSummary(int[][] grid) {
        int total = 0, max = -1, maxR = -1, maxC = -1;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                total += grid[r][c];
                if (grid[r][c] > max) {
                    max = grid[r][c];
                    maxR = r;
                    maxC = c;
                }
            }
        }
        return new int[]{ total, maxR, maxC };
    }

    public static void main(String[] args) {
        int[][] grid = { {4, 9, 2}, {7, 1, 6}, {3, 12, 5} };
        int[] r = warehouseSummary(grid);
        System.out.println("(" + r[0] + ", (" + r[1] + ", " + r[2] + "))");   // (49, (2, 1))
    }
}
