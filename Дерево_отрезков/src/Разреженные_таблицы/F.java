package Разреженные_таблицы;

import java.util.Scanner;

import static java.lang.Math.min;

public class F {
    static int[][] m;
    static int N, M;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        int st = fl(N) + 1;
        m = new int[N][st];
        m[0][0] = in.nextInt();
        for (int i = 1; i < N; i++) {
            m[i][0] = (23 * m[i - 1][0] + 21563) % 16714589;
        }
        int z = 1;
        for (int k = 1; k <= st; k++) {
            z *= 2;
            int z2 = z / 2;
            for (int i = 0; i <= N - z; i++) {
                m[i][k] = min(m[i][k - 1], m[i + z2][k - 1]);
            }
        }
        int ui = in.nextInt(), vi = in.nextInt();
        int out;
        int k;
        if (ui == vi) {
            out = m[ui - 1][0];
        } else if (ui < vi) {
            k = (fl(vi - ui + 1));
            out = min(m[ui - 1][k], m[vi - (int) pow(k)][k]);
        } else {
            k = (fl(ui - vi + 1));
            out = min(m[vi - 1][k], m[ui - (int) pow(k)][k]);
        }
        for (int i = 1; i < M; i++) {
            ui = ((17 * ui + 751 + out + 2 * i) % N) + 1;
            vi = ((13 * vi + 593 + out + 5 * i) % N) + 1;
            if (ui == vi) {
                out = m[ui - 1][0];
            } else if (ui < vi) {
                k = (fl(vi - ui + 1));
                out = min(m[ui - 1][k], m[vi -  pow(k)][k]);
            } else {
                k = (fl(ui - vi + 1));
                out = min(m[vi - 1][k], m[ui -  pow(k)][k]);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ui).append(' ').append(vi).append(' ').append(out);
        System.out.println(sb.toString());
    }

    public static int fl(int len) {
        if (len == 1)
            return 0;
        else
            return fl(len / 2) + 1;
    }

    public static int pow(int len) {
        return 1 << len;
    }
}