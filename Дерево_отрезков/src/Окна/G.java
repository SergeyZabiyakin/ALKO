package Окна;

import java.util.*;


public class G {
    static long[] maxi, index, sets;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 400000;
        int size = Integer.highestOneBit(n);
        if (size < n) size *= 2;
        maxi = new long[2 * size - 1];
        index = new long[2 * size - 1];
        sets = new long[2 * size - 1];

        Arrays.fill(index, Long.MAX_VALUE);
        n = in.nextInt();
        int[] point = new int[4];
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            point[0] = in.nextInt();
            point[1] = in.nextInt();
            point[2] = in.nextInt();
            point[3] = in.nextInt();
            list.add(new int[]{point[0], point[1], point[3], 1});
            list.add(new int[]{point[2], point[1], point[3], -1});
        }
        list.sort(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return Integer.compare(b[3], a[3]);
                } else {
                    return Integer.compare(a[0], b[0]);
                }
            }
        });
        long m = 0;
        long x = 0;
        long y = 0;
        for (int[] a : list) {
            add(a[1] + 200000, a[2] + 200001, a[3], 0, 0, size);
            if (maxi[0] > m) {
                m = maxi[0];
                x = a[0];
                y = index[0] - 200000;
            }
        }
        System.out.println(m);
        System.out.println(x + " " + y);
    }


    public static void add(int l, int r, long v, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return;
        }
        if (rx - lx == 1) {
            maxi[x] += v;
            index[x] = lx;
            return;
        }
        if (lx >= l && rx <= r) {
            maxi[x] += v;
            sets[x] += v;
            if (index[x] == Long.MAX_VALUE) {
                index[x] = lx;
            }
            return;
        }
        int m = (rx + lx) / 2;
        sets[x] = 0;
        add(l, r, v, 2 * x + 1, lx, m);
        add(l, r, v, 2 * x + 2, m, rx);
        if (maxi[2 * x + 1] >= maxi[2 * x + 2]) {
            maxi[x] = maxi[2 * x + 1];
            index[x] = index[2 * x + 1];
        } else {
            maxi[x] = maxi[2 * x + 2];
            index[x] = index[2 * x + 2];
        }
    }


    public static void propagate(int x, int lx, int rx) {
        if (rx - lx == 1 || sets[x] == 0) return;
        maxi[2 * x + 1] += sets[x];
        maxi[2 * x + 2] += sets[x];
        sets[2 * x + 1] += sets[x];
        sets[2 * x + 2] += sets[x];
        sets[x] = 0;
        if (index[2 * x + 1] == Long.MAX_VALUE) {
            index[2 * x + 1] = lx;
        }
        if (index[2 * x + 2] == Long.MAX_VALUE) {
            index[2 * x + 2] = (lx + rx) / 2;
        }
    }
}
