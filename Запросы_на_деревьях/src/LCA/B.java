package LCA;

import java.util.Arrays;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int N = n;
        int logn = 1;
        while (N != 0) {
            logn++;
            N /= 2;
        }

        int[][] Q = new int[n][logn];
        int[] Qh = new int[n];
        Q[0][0] = 1;
        for (int i = 1; i < n; i++) {
            int j = in.nextInt();
            Q[i][0] = j;
            Qh[i] = Qh[j - 1] + 1;
        }
        for (int i = 1; i < logn; i++) {
            for (int j = 0; j < n; j++) {
                if (Q[j][i - 1] > 1) Q[j][i] = Q[Q[j][i - 1] - 1][i - 1];
                else Q[j][i] = 1;
            }
        }

        /*for (int i = 0; i < n; i++) {
            int j = 0;
            sb.append(i + 1).append(':').append(' ');
            while (j < logn && Q[i][j] != 0) {
                sb.append(Q[i][j]).append(' ');
                j++;
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
        sb.delete(0, sb.length());
        System.out.println(Arrays.toString(Qh));*/

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int q = in.nextInt() - 1;
            int q1 = in.nextInt() - 1;
            if (Qh[q] < Qh[q1]) {
                int q2 = q1;
                q1 = q;
                q = q2;
                /*q ^= q1;
                q1 ^= q;
                q ^= q1;*/
            }
            int j = 0;
            while (Qh[q] != Qh[q1]) {
                while (Qh[Q[q][j] - 1] > Qh[q1]) {
                    j++;
                }
                j--;
                if (j == -1) {
                    q = Q[q][0] - 1;
                    break;
                }
                q = Q[q][j] - 1;
                while (j != -1 && Qh[Q[q][j] - 1] < Qh[q1]) {
                    j--;
                }
                if (j == -1) {
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
                    q = Q[q][0] - 1;
                    break;
                }
                q = Q[q][j] - 1;
                q1 = Q[q1][j] - 1;
                while (j != -1 && Q[q][j] == Q[q1][j]) {
                    j--;
                }
                if (j == -1) {
                    q = Q[q][0] - 1;
                    break;
                }
            }
            q++;
            sb.append(q).append('\n');
        }

        System.out.println(sb.toString());
    }

}