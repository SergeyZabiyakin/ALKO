package RSQ;

import java.util.*;


public class B {
    static long[] array;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = Integer.highestOneBit(n);
        if (m < n) m *= 2;
        array = new long[2 * m - 1];
        for (int i = 0; i < n; i++) {
            set(i, in.nextInt(), 0, 0, m);
        }
        sb = new StringBuilder();
        while (in.hasNext()) {
            //for (int i = 0; i < 11; i++) {
            String str = in.next();
            if (str.charAt(1) == 'u') {
                sb.append(sum(in.nextInt() - 1, in.nextInt() , 0, 0, m));
                sb.append('\n');
            } else {
                set(in.nextInt() - 1, in.nextInt(), 0, 0, m);
            }
        }
        System.out.println(sb.toString());
    }

    public static void set(int i, int v, int x, int lx, int rx) {
        if (rx - lx == 1) {
            array[x] = v;
            return;
        }
        int m = (rx + lx) / 2;
        if (i < m) {
            set(i, v, 2 * x + 1, lx, m);
        } else {
            set(i, v, 2 * x + 2, m, rx);
        }
        array[x] = array[2 * x + 1] + array[2 * x + 2];
    }

    public static long sum(int l, int r, int x, int lx, int rx) {
        if (l >= rx || lx >= r) {
            return 0;
        }
        if (lx >= l && rx <= r) {
            return array[x];
        }
        int m = (rx + lx) / 2;
        return sum(l, r, 2 * x + 1, lx, m) + sum(l, r, 2 * x + 2, m, rx);
    }
}
