package Симпатичные_узоры;

import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        //swap
        if (n > m) {
            n ^= m;
            m ^= n;
            n ^= m;
        }

        long[][][] dp = new long[m + 1][n + 1][1 << (n + 1)];
        //база
        dp[0][0][0] = 1;
        int newProf;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int prof = 0; prof < 1 << (n + 1); prof++) {
                    if (j == 0) {
                        dp[i][j + 1][prof] += dp[i][j][prof & ((1 << (n + 1)) - 2)];
                    } else {
                        int cell = (prof & (1 << (j - 1))) == 0 ? 0 : 1;
                        int cell1 = (prof & (1 << (j))) == 0 ? 0 : 1;
                        int cell2 = (prof & (1 << (j + 1))) == 0 ? 0 : 1;
                        if ((cell != cell1) || (cell != cell2) || (i == 0)) {
                            newProf = prof;
                            dp[i][j + 1][newProf] += dp[i][j][prof];
                        }
                        newProf = prof ^ (1 << j);
                        dp[i][j + 1][newProf] += dp[i][j][prof];
                    }
                }
            }
            for (int p = 0; p < 1 << (n + 1); p++) {
                dp[i + 1][0][(p & ((1 << n) - 1)) << 1] += dp[i][n][p];
            }
        }

        long out = 0;
        for (int prof = 0; prof < 1 << (n + 1); prof++) {
            out += dp[m - 1][n][prof];
        }
        System.out.println(out);
    }
}

