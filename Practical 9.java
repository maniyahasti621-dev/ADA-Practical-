import java.util.Arrays;

public class CoinChangeDP {

    public static int minCoins(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max); 
        dp[0] = 0; 

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount? -1 : dp[amount];
    }

    public static void printMinCoins(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        int[] parent = new int[amount + 1]; 
        Arrays.fill(dp, max);
        Arrays.fill(parent, -1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    parent[i] = coin;
                }
            }
        }

        if (dp[amount] > amount) {
            System.out.println("Cannot make amount " + amount);
            return;
        }

        System.out.println("Min coins for " + amount + ": " + dp[amount]);
        System.out.print("Coins used: ");
        int curr = amount;
        while (curr > 0) {
            System.out.print(parent[curr] + " ");
            curr -= parent[curr];
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] coins = {1, 3, 4};
        int amount = 6;

        System.out.println("=== Minimum Coins to Make Change ===");
        printMinCoins(coins, amount);

        int[] coins2 = {2, 5, 10, 1};
        int amount2 = 27;
        printMinCoins(coins2, amount2);
    }
}
