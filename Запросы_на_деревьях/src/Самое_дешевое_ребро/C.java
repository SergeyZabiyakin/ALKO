package Самое_дешевое_ребро;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class C {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("minonpath.in"), StandardCharsets.UTF_8); //minonpath.in
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int N = n;
        int logn = 1;
        while (N != 0) {
            logn++;
            N /= 2;
        }

        int[][] Q = new int[n][logn];
        int[][] Qmin = new int[n][logn];
        int[] Qh = new int[n];
        Q[0][0] = 1;
        Qmin[0][0] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int j = in.nextInt();
            int k = in.nextInt();
            Q[i][0] = j;
            Qmin[i][0] = k;
            Qh[i] = Qh[j - 1] + 1;
        }
        for (int i = 1; i < logn; i++) {
            for (int j = 0; j < n; j++) {
                if (Q[j][i - 1] > 1) {
                    Q[j][i] = Q[Q[j][i - 1] - 1][i - 1];
                    Qmin[j][i] = Math.min(Qmin[j][i - 1], Qmin[Q[j][i - 1] - 1][i - 1]);
                } else {
                    Q[j][i] = 1;
                    Qmin[j][i] = Qmin[j][i-1];
                }
            }
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int q = in.nextInt() - 1;
            int q1 = in.nextInt() - 1;
            if (Qh[q] < Qh[q1]) {
                q ^= q1;
                q1 ^= q;
                q ^= q1;
            }
            int j = 0;
            int qmin = Integer.MAX_VALUE;
            int q1min = Integer.MAX_VALUE;
            while (Qh[q] != Qh[q1]) {
                while (Qh[Q[q][j] - 1] > Qh[q1]) {
                    j++;
                }
                j--;
                if (j == -1) {
                    qmin = Math.min(qmin, Qmin[q][0]);
                    q = Q[q][0] - 1;
                    break;
                }
                qmin = Math.min(qmin, Qmin[q][j]);
                q = Q[q][j] - 1;
                while (j != -1 && Qh[Q[q][j] - 1] < Qh[q1]) {
                    j--;
                }
                if (j == -1) {
                    qmin = Math.min(qmin, Qmin[q][0]);
                    q = Q[q][0] - 1;
                    break;
                }
            }
            j = 0;
            while (q != q1) {
                while (Q[q][j] != Q[q1][j]) {
                    j++;
                }
                j--;
                if (j == -1) {
                    qmin = Math.min(qmin, Qmin[q][0]);
                    q1min = Math.min(q1min, Qmin[q1][0]);
                    break;
                }
                qmin = Math.min(qmin, Qmin[q][j]);
                q1min = Math.min(q1min, Qmin[q1][j]);
                q = Q[q][j] - 1;
                q1 = Q[q1][j] - 1;
                while (j != -1 && Q[q][j] == Q[q1][j]) {
                    j--;
                }
                if (j == -1) {
                    qmin = Math.min(qmin, Qmin[q][0]);
                    q1min = Math.min(q1min, Qmin[q1][0]);
                    break;
                }
            }
            sb.append(Math.min(qmin, q1min)).append('\n');
        }

        FileWriter out = new FileWriter(new File("minonpath.out"));
        out.write(sb.toString());
        out.close();
    }

}
