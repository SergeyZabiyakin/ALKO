package Художник;

import java.util.*;

import static java.lang.StrictMath.max;


public class D {
    static long[] arr, sum, left, mid, right, sets;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 1500000;
        int size = Integer.highestOneBit(n);
        if (size < n) size *= 2;
        arr = new long[2 * size - 1];
        sum = new long[2 * size - 1];
        left = new long[2 * size - 1];
        right = new long[2 * size - 1];
        mid = new long[2 * size - 1];
        sets = new long[2 * size - 1];
        Arrays.fill(sets, -1);
        sb = new StringBuilder();
        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String str = in.next();
            int l = in.nextInt()+500000;
            if (str.charAt(0) == 'B') {
                set(l, l + in.nextInt(), 1, 0, 0, size);
            } else {
                set(l, l + in.nextInt(), 0, 0, 0, size);
            }
            sb.append(mid[0]);
            sb.append(' ');
            sb.append(arr[0]);
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }


    public static void set(int l, int r, long v, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return;
        }
        if (lx >= l && rx <= r) {
            arr[x] = (rx - lx) * v;
            left[x] = v;
            right[x] = v;
            mid[x] = v;
            sets[x] = v;
            return;
        }
        int m = (rx + lx) / 2;
        set(l, r, v, 2 * x + 1, lx, m);
        set(l, r, v, 2 * x + 2, m, rx);
        arr[x] = arr[2 * x + 1] + arr[2 * x + 2];
        left[x] = left[2 * x + 1];
        right[x] = right[2 * x + 2];
        if (right[2 * x + 1] == left[2 * x + 2] && right[2 * x + 1] == 1) {
            mid[x] = mid[2 * x + 1] + mid[2 * x + 2] - 1;
        } else {
            mid[x] = mid[2 * x + 1] + mid[2 * x + 2];
        }
        sets[x] = -1;
    }


    public static void propagate(int x, int lx, int rx) {
        if (rx - lx == 1) return;
        if (sets[x] == -1) return;
        int m = (rx + lx) / 2;
        arr[2 * x + 1] = (m - lx) * sets[x];
        left[2 * x + 1] = sets[x];
        right[2 * x + 1] = sets[x];
        mid[2 * x + 1] = sets[x];
        sets[2 * x + 1] = sets[x];

        arr[2 * x + 2] = (rx - m) * sets[x];
        left[2 * x + 2] = sets[x];
        right[2 * x + 2] = sets[x];
        mid[2 * x + 2] = sets[x];
        sets[2 * x + 2] = sets[x];

        sets[x] = -1;
    }
}
