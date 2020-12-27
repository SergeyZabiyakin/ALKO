package Великая_Китайская_Стена;

import java.util.*;

import static java.lang.Math.max;

public class J {
    static long[] sets;
    static long[][] min;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int size = Integer.highestOneBit(n);
        if (size < n) size *= 2;
        min = new long[2 * size - 1][2];
        sets = new long[2 * size - 1];
        for (int i = 0; i < size; i++) {
            set(i, i + 1, 0, 0, 0, size);
        }
        sb = new StringBuilder();
        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String str = in.next();
            if (str.charAt(0) == 'a') {
                long[] out = mini(in.nextInt() - 1, in.nextInt(), 0, 0, size);
                sb.append(out[0]).append(" ").append(out[1]);
                sb.append('\n');
            } else {
                set(in.nextInt() - 1, in.nextInt(), in.nextLong(), 0, 0, size);
            }
        }
        System.out.println(sb.toString());
    }


    public static void set(int l, int r, long v, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return;
        }
        if (rx - lx == 1) {
            sets[x] = max(v, sets[x]);
            min[x][0] = max(v, min[x][0]);
            min[x][1] = lx + 1;
            return;
        }
        if (lx >= l && rx <= r) {
            sets[x] = max(v, sets[x]);
            min[x][0] = max(v, min[x][0]);
            min[x][1] = min[2 * x + 1][0] <= min[2 * x + 2][0] ? min[2 * x + 1][1] : min[2 * x + 2][1];
            return;
        }
        sets[x] = 0;
        int m = (rx + lx) / 2;
        set(l, r, v, 2 * x + 1, lx, m);
        set(l, r, v, 2 * x + 2, m, rx);
        if (min[2 * x + 1][0] <= min[2 * x + 2][0]) {
            min[x][0] = min[2 * x + 1][0];
            min[x][1] = min[2 * x + 1][1];
        } else {
            min[x][0] = min[2 * x + 2][0];
            min[x][1] = min[2 * x + 2][1];
        }
    }

    public static long[] mini(int l, int r, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return new long[]{Long.MAX_VALUE, Long.MAX_VALUE};
        }
        if (lx >= l && rx <= r) {
            return new long[]{
                    min[x][0], min[x][1]
            };
        }
        int m = (rx + lx) / 2;
        long[] out1 = mini(l, r, 2 * x + 1, lx, m);
        long[] out2 = mini(l, r, 2 * x + 2, m, rx);
        return out1[0] <= out2[0] ? out1 : out2;
    }

    public static void propagate(int x, int lx, int rx) {
        if (rx - lx == 1 || sets[x] == 0) return;
        min[2 * x + 1][0] = max(sets[x], min[2 * x + 1][0]);
        if (2 * (2 * x + 1) + 2 < min.length) {
            min[2 * x + 1][1] = min[2 * (2 * x + 1) + 1][0] <= min[2 * (2 * x + 1) + 2][0] ? min[2 * (2 * x + 1) + 1][1] : min[2 * (2 * x + 1) + 2][1];
        } else {
            min[2 * x + 1][1] = lx + 1;
        }
        sets[2 * x + 1] = max(sets[x], sets[2 * x + 1]);

        min[2 * x + 2][0] = max(sets[x], min[2 * x + 2][0]);
        if (2 * (2 * x + 2) + 2 < min.length) {
            min[2 * x + 2][1] = min[2 * (2 * x + 2) + 1][0] <= min[2 * (2 * x + 2) + 2][0] ? min[2 * (2 * x + 2) + 1][1] : min[2 * (2 * x + 2) + 2][1];
        } else {
            min[2 * x + 2][1] = (rx + lx) / 2 + 1;
        }
        sets[2 * x + 2] = max(sets[x], sets[2 * x + 2]);
        sets[x] = 0;
    }
}
