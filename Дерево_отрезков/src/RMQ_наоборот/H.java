package RMQ_наоборот;

import java.io.File;
import java.io.FileWriter;
import java.util.*;


public class H {
    static long[] mini, sets;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("rmq.in"));
        int n = in.nextInt();
        int size = Integer.highestOneBit(n);
        if (size < n) size *= 2;
        mini = new long[2 * size - 1];
        Arrays.fill(mini, Long.MAX_VALUE);
        sets = new long[2 * size - 1];
        Arrays.fill(sets, Long.MAX_VALUE);
        int m = in.nextInt();
        List<long[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(new long[]{in.nextLong(), in.nextLong(), in.nextLong()});
        }
        in.close();
        list.sort(new Comparator<long[]>() {
            public int compare(long[] a, long[] b) {
                return Long.compare(a[2], b[2]);
            }
        });
        for (long[] a : list) {
            set(a[0] - 1, a[1], a[2], 0, 0, size);
        }
        for (long[] a : list) {
            if (a[2] != minix(a[0] - 1, a[1], 0, 0, size)) {
                FileWriter output = new FileWriter(new File("rmq.out"));
                output.write("inconsistent");
                output.close();
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("consistent").append('\n');
        for (int i = 0; i < n; i++) {
            long out = minix(i, i + 1, 0, 0, size);
            if (out == Long.MAX_VALUE) {
                sb.append(Integer.MAX_VALUE).append(" ");
            } else {
                sb.append(out).append(" ");
            }
        }
        FileWriter output = new FileWriter(new File("rmq.out"));
        output.write(sb.toString());
        output.close();
    }


    public static void set(long l, long r, long v, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return;
        }
        if (lx >= l && rx <= r) {
            mini[x] = v;
            sets[x] = v;
            return;
        }
        int m = (rx + lx) / 2;
        set(l, r, v, 2 * x + 1, lx, m);
        set(l, r, v, 2 * x + 2, m, rx);
        mini[x] = Math.min(mini[2 * x + 1], mini[2 * x + 2]);
        sets[x] = Long.MAX_VALUE;
    }

    public static long minix(long l, long r, int x, int lx, int rx) {
        propagate(x, lx, rx);
        if (l >= rx || lx >= r) {
            return Long.MAX_VALUE;
        }
        if (lx >= l && rx <= r) {
            return mini[x];
        }
        int m = (rx + lx) / 2;
        return Math.min(minix(l, r, 2 * x + 1, lx, m), minix(l, r, 2 * x + 2, m, rx));
    }


    public static void propagate(int x, int lx, int rx) {
        if (rx - lx == 1 || sets[x] == Long.MAX_VALUE) return;
        mini[2 * x + 1] = sets[x];
        mini[2 * x + 2] = sets[x];

        sets[2 * x + 1] = sets[x];
        sets[2 * x + 2] = sets[x];

        sets[x] = Long.MAX_VALUE;
    }
}
