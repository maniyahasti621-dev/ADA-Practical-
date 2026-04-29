import java.util.Arrays;

public class Knapsack01DP {

    public static int knapsack(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]],
                                        dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        printSelectedItems(dp, wt, val, n, W);
        return dp[n][W];
    }

    public static void printSelectedItems(int[][] dp, int[] wt, int[] val, int n, int W) {
        System.out.println("Selected items:");
        int res = dp[n][W];
        int w = W;

        for (int i = n; i > 0 && res > 0; i--) {
            if (res!= dp[i - 1][w]) {
                System.out.println("Item " + i + ": weight=" + wt[i - 1] + ", value=" + val[i - 1]);
                res -= val[i - 1];
                w -= wt[i - 1];
            }
        }
    }

    public static int knapsackOptimized(int W, int[] wt, int[] val, int n) {
        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            for (int w = W; w >= wt[i]; w--) {
                dp[w] = Math.max(dp[w], val[i] + dp[w - wt[i]]);
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
        int[] val = {60, 100, 120}; 
        int[] wt = {10, 20, 30}; 
        int W = 50; 
        int n = val.length;

        System.out.println("=== 0/1 Knapsack Problem ===");
        System.out.println("Weights: " + Arrays.toString(wt));
        System.out.println("Values: " + Arrays.toString(val));
        System.out.println("Capacity: " + W);
        System.out.println("-----------------------------");

        int maxValue = knapsack(W, wt, val, n);
        System.out.println("Maximum value: " + maxValue);

        System.out.println("\nSpace optimized result: " + knapsackOptimized(W, wt, val, n));
    }
}
