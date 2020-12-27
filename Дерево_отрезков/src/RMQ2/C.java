package RMQ2;

import java.util.*;

import static java.lang.Math.min;


public class C {
    static long[] min, sets, adds;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int size = Integer.highestOneBit(n);
        if (size < n) size *= 2;
        min = new long[2 * size - 1];
        sets = new long[2 * size - 1];
        adds = new long[2 * size - 1];
        Arrays.fill(sets, Long.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            set(i, i + 1, in.nextLong(), 0, 0, size);
        }
        sb = new StringBuilder();
        while (in.hasNext()) {
            //for (int i = 0; i < 10; i++) {
            String str = in.next();
            if (str.charAt(0) == 'm') {
                sb.append(mini(in.nextInt() - 1, in.nextInt(), 0, 0, size));
                sb.append('\n');
            } else if (str.charAt(0) == 's') {
                set(in.nextInt() - 1, in.nextInt(), in.nextLong(), 0, 0, size);
            } else {
                add(in.nextInt() - 1, in.nextInt(), in.nextLong(), 0, 0, size);
            }
        }
        System.out.println(sb.toString());
    }

    public static void add(int l, int r, long v, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return;
        }
        if (lx >= l && rx <= r) {
            min[x] += v;
            adds[x] += v;
            return;
        }
        int m = (rx + lx) / 2;
        add(l, r, v, 2 * x + 1, lx, m);
        add(l, r, v, 2 * x + 2, m, rx);
        min[x] = min(min[2 * x + 1], min[2 * x + 2]);
    }

    public static void set(int l, int r, long v, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return;
        }
        if (lx >= l && rx <= r) {
            sets[x] = v;
            adds[x] = 0;
            min[x] = v;
            return;
        }
        int m = (rx + lx) / 2;
        set(l, r, v, 2 * x + 1, lx, m);
        set(l, r, v, 2 * x + 2, m, rx);
        min[x] = min(min[2 * x + 1], min[2 * x + 2]);
    }

    public static long mini(int l, int r, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return Long.MAX_VALUE;
        }
        if (lx >= l && rx <= r) {
            return min[x];
        }
        int m = (rx + lx) / 2;
        return min(mini(l, r, 2 * x + 1, lx, m), mini(l, r, 2 * x + 2, m, rx));
    }

    public static void propagate(int x, int lx, int rx) {
        if (rx - lx == 1) return;
        if (sets[x] == Long.MIN_VALUE && adds[x] == 0) return;
        if (sets[x] != Long.MIN_VALUE) {
            sets[2 * x + 1] = sets[x];
            adds[2 * x + 1] = adds[x];
            min[2 * x + 1] = sets[x] + adds[x];

            sets[2 * x + 2] = sets[x];
            adds[2 * x + 2] = adds[x];
            min[2 * x + 2] = sets[x] + adds[x];
        } else {
            adds[2 * x + 1] += adds[x];
            min[2 * x + 1] += adds[x];

            adds[2 * x + 2] += adds[x];
            min[2 * x + 2] += adds[x];
        }
        sets[x] = Long.MIN_VALUE;
        adds[x] = 0;
    }
}
