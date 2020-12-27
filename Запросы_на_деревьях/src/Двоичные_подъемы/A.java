package Двоичные_подъемы;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = in.nextInt();
        int N = n;
        int logn = -1;
        while (N != 0) {
            logn++;
            N /= 2;
        }
        if ((1 << logn) < n) logn++;
        if (logn == 0) {
            System.out.println("1: ");
        }
        int[][] Q = new int[n][logn];
        for (int i = 0; i < n; i++) {
            int j = in.nextInt();
            Q[i][0] = j;
        }
        for (int i = 1; i < logn; i++) {
            for (int j = 0; j < n; j++) {
                if (Q[j][i - 1] != 0) Q[j][i] = Q[Q[j][i - 1] - 1][i - 1];
            }
        }
        for (int i = 0; i < n; i++) {
            int j = 0;
            sb.append(i + 1).append(':').append(' ');
            while (j < logn && Q[i][j] != 0) {
                sb.append(Q[i][j]).append(' ');
                j++;
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}