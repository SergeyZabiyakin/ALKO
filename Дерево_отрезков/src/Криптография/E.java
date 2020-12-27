package Криптография;

import java.io.File;
import java.io.FileWriter;
import java.util.*;


public class E {
    static int R;
    static int[][] array;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("crypto.in"));
        R = in.nextInt();
        int n = in.nextInt();
        int M = in.nextInt();
        int m = Integer.highestOneBit(n);
        if (m < n) m *= 2;
        array = new int[2 * m - 1][4];
        for (int i = 0; i < n; i++) {
            set(i, in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), 0, 0, m);
        }
        sb = new StringBuilder();
        int[] out;
        for (int i = 0; i < M; i++) {
            out = sum(in.nextInt() - 1, in.nextInt(), 0, 0, m);
            sb.append(out[0]);
            sb.append(' ');
            sb.append(out[1]);
            sb.append('\n');
            sb.append(out[2]);
            sb.append(' ');
            sb.append(out[3]);
            sb.append('\n');
            sb.append('\n');
        }
        in.close();
        FileWriter output = new FileWriter("crypto.out");
        output.write(sb.toString());
        output.close();
    }

    public static void set(int i, int v1, int v2, int v3, int v4, int x, int lx, int rx) {
        if (rx - lx == 1) {
            array[x][0] = v1 % R;
            array[x][1] = v2 % R;
            array[x][2] = v3 % R;
            array[x][3] = v4 % R;
            return;
        }
        int m = (rx + lx) / 2;
        if (i < m) {
            set(i, v1, v2, v3, v4, 2 * x + 1, lx, m);
        } else {
            set(i, v1, v2, v3, v4, 2 * x + 2, m, rx);
        }
        array[x][0] = (array[2 * x + 1][0] * array[2 * x + 2][0] + array[2 * x + 1][1] * array[2 * x + 2][2]) % R;
        array[x][1] = (array[2 * x + 1][0] * array[2 * x + 2][1] + array[2 * x + 1][1] * array[2 * x + 2][3]) % R;
        array[x][2] = (array[2 * x + 1][2] * array[2 * x + 2][0] + array[2 * x + 1][3] * array[2 * x + 2][2]) % R;
        array[x][3] = (array[2 * x + 1][2] * array[2 * x + 2][1] + array[2 * x + 1][3] * array[2 * x + 2][3]) % R;
    }

    public static int[] sum(int l, int r, int x, int lx, int rx) {
        if (l >= rx || lx >= r) {
            return new int[]{1 , 0, 0, 1 };
        }
        if (lx >= l && rx <= r) {
            return new int[]{array[x][0], array[x][1], array[x][2], array[x][3]};
        }
        int m = (rx + lx) / 2;
        int[] a = sum(l, r, 2 * x + 1, lx, m);
        int[] b = sum(l, r, 2 * x + 2, m, rx);
        return new int[]{
                (a[0] * b[0] + a[1] * b[2]) % R,
                (a[0] * b[1] + a[1] * b[3]) % R,
                (a[2] * b[0] + a[3] * b[2]) % R,
                (a[2] * b[1] + a[3] * b[3]) % R};
    }
}
