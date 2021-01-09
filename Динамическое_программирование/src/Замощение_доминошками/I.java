package Замощение_доминошками;
import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] matrix = new char[m + 1][n];

        for (int i = 0; i < n; i++) {
            char[] chars = in.next().toCharArray();
            for (int j = 0; j < m; j++) {
                matrix[j][i] = chars[j];
            }
        }

        long[][][] dp = new long[m + 1][n + 1][1 << n];
        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int p = 0; p < 1 << n; p++) {
                    if (((p & (1 << j)) != 0) && (matrix[i][j] == '.')) {
                        int pnew = p - (1 << j);
                        dp[i][j + 1][pnew] += dp[i][j][p];
                    } else {
                        if (matrix[i][j] == '.') {
                            int pnew = p + (1 << j);
                            if (matrix[i + 1][j] == '.') {
                                dp[i][j + 1][pnew] += dp[i][j][p];
                            }
                            if ((j < n - 1) && ((p & (1 << (j + 1))) == 0) && (matrix[i][j + 1] == '.')) {
                                pnew = p + (1 << (j + 1));
                                dp[i][j + 1][pnew] += dp[i][j][p];
                            }
                        } else {
                            dp[i][j + 1][p] += dp[i][j][p];
                        }
                    }
                }
            }
            for (int p = 0; p < 1 << n; p++) {
                dp[i + 1][0][p] += dp[i][n][p];
            }
        }

        System.out.println(dp[m - 1][n][0]);
    }
}
