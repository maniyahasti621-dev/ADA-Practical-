import java.util.Arrays;

public class MatrixChainMultiplication {

    public static void matrixChainOrder(int[] p) {
        int n = p.length - 1; 
        int[][] m = new int[n + 1][n + 1]; 
        int[][] s = new int[n + 1][n + 1]; 
        for (int L = 2; L <= n; L++) {
            for (int i = 1; i <= n - L + 1; i++) {
                int j = i + L - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                        s[i][j] = k; 
                    }
                }
            }
        }

        System.out.println("Minimum multiplications: " + m[1][n]);
        System.out.print("Optimal Parenthesization: ");
        printParenthesis(s, 1, n);
        System.out.println();

        printDPTable(m, n);
    }

    public static void printParenthesis(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printParenthesis(s, i, s[i][j]);
            printParenthesis(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void printDPTable(int[][] m, int n) {
        System.out.println("\nDP Table m[i][j]:");
        System.out.print(" ");
        for (int j = 1; j <= n; j++) System.out.printf("%6d", j);
        System.out.println();
        for (int i = 1; i <= n; i++) {
            System.out.printf("%2d: ", i);
            for (int j = 1; j <= n; j++) {
                if (j >= i) System.out.printf("%6d", m[i][j]);
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] p = {10, 30, 5, 60, 4};

        System.out.println("=== Matrix Chain Multiplication ===");
        System.out.println("Matrix dimensions:");
        for (int i = 1; i < p.length; i++) {
            System.out.println("A" + i + ": " + p[i - 1] + " x " + p[i]);
        }
        System.out.println("-------------------------------------");

        matrixChainOrder(p);
    }
}
