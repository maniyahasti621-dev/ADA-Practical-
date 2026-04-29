public class ShortestCommonSupersequence {
    public static int scs(String X, String Y) {
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

        int lcsLen = dp[m][n];
        int scsLen = m + n - lcsLen;

        System.out.println("X: " + X + ", length = " + m);
        System.out.println("Y: " + Y + ", length = " + n);
        System.out.println("LCS length: " + lcsLen);
        System.out.println("SCS length: " + scsLen);
        System.out.print("SCS: ");
        printSCS(dp, X, Y, m, n);
        System.out.println();

        return scsLen;
    }
    public static void printSCS(int[][] dp, String X, String Y, int i, int j) {
        if (i == 0) {
            System.out.print(Y.substring(0, j));
            return;
        }
        if (j == 0) {
            System.out.print(X.substring(0, i));
            return;
        }

        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            printSCS(dp, X, Y, i - 1, j - 1);
            System.out.print(X.charAt(i - 1));
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
            printSCS(dp, X, Y, i - 1, j);
            System.out.print(X.charAt(i - 1));
        } else {
            printSCS(dp, X, Y, i, j - 1);
            System.out.print(Y.charAt(j - 1));
        }
    }

    public static int scsDirect(String X, String Y) {
        int m = X.length(), n = Y.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String X = "AGGTAB";
        String Y = "GXTXAYB";

        System.out.println("=== Shortest Common Supersequence ===");
        scs(X, Y);

        System.out.println("\n--- Another example ---");
        String X2 = "HELLO";
        String Y2 = "GEEKS";
        scs(X2, Y2);

        System.out.println("\nDirect DP SCS length for AGGTAB, GXTXAYB: " + scsDirect(X, Y));
    }
}
