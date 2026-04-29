import java.util.Arrays;

public class LongestCommonSubsequence {
  
    public static int lcs(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println("LCS Length: " + dp[m][n]);
        System.out.print("LCS: ");
        printLCS(dp, X, Y, m, n);
        System.out.println();

        printDPTable(dp, X, Y);
        return dp[m][n];
    }
  
    public static void printLCS(int[][] dp, String X, String Y, int i, int j) {
        if (i == 0 || j == 0) return;

        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            printLCS(dp, X, Y, i - 1, j - 1);
            System.out.print(X.charAt(i - 1));
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
            printLCS(dp, X, Y, i - 1, j);
        } else {
            printLCS(dp, X, Y, i, j - 1);
        }
    }
    public static int lcsOptimized(String X, String Y) {
        int m = X.length(), n = Y.length();
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n];
    }

    public static void printDPTable(int[][] dp, String X, String Y) {
        int m = X.length(), n = Y.length();
        System.out.println("\nDP Table:");
        System.out.print(" ");
        for (int j = 0; j <= n; j++) {
            if (j == 0) System.out.print(" ");
            else System.out.print(" " + Y.charAt(j - 1));
        }
        System.out.println();

        for (int i = 0; i <= m; i++) {
            if (i == 0) System.out.print(" ");
            else System.out.print(X.charAt(i - 1));
            for (int j = 0; j <= n; j++) {
                System.out.print(" " + dp[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String X = "AGGTAB";
        String Y = "GXTXAYB";

        System.out.println("=== Longest Common Subsequence ===");
        System.out.println("X: " + X);
        System.out.println("Y: " + Y);
        System.out.println("----------------------------------");

        lcs(X, Y);

        System.out.println("\nSpace optimized length: " + lcsOptimized(X, Y));
    }
}
