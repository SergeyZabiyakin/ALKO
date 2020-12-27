package Продавец_аквариумов;

import java.util.*;

import static java.lang.Math.min;


public class H {
    static int n;
    static long[][] a;
    static long[][] b;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        a = new long[n][n];
        b = new long[1 << n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextLong();
            }
        }
        in.close();
        for (int i = 1; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = Integer.MAX_VALUE;
            }
        }
        int J = 0;
        for (int i = 1; i < (1 << n); i *= 2) {
            b[i][J++] = 0;
        }
        for (int i = 1; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == (1 << j)) {
                    for (int k = 0; k < n; k++) {
                        if (((i - (1 << j)) & (1 << k)) == (1 << k)) {
                            b[i][j] = min(b[i][j], b[i - (1 << j)][k] + a[k][j]);
                        }
                    }
                }
            }
        }
        int x = (1 << n) - 1;
        long out = Integer.MAX_VALUE;
        int gor = 0;
        for (int k = 0; k < n; k++) {
            if (out > b[x][k]) {
                out = b[x][k];
                gor = k;
            }
        }
        sb.append(out);
        sb.append('\n');
        x -= (1 << gor);
        while (x != 0) {
            for (int k = 0; k < n; k++) {
                if ((x & (1 << k)) == (1 << k) && out == b[x][k] + a[gor][k]) {
                    sb.append(gor+1);
                    sb.append(' ');
                    out = b[x][k];
                    gor = k;
                    x -= (1 << gor);
                }
            }
        }
        sb.append(gor+1);
        System.out.println(sb.toString());
    }
}