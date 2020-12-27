package Сумма_простая;

import java.util.*;

public class A {
    static int n;
    static long[] a;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        a = new long[n];
        int l = in.nextInt();
        a[0] = l;
        int math = (int) (Math.pow(2, 16));
        for (int i = 1; i < n; i++) {
            l = (x * l + y) % math;
            a[i] = l + a[i - 1];
        }
        int m = in.nextInt();
        int z = in.nextInt();
        int t = in.nextInt();
        int b = in.nextInt();
        long out = 0;
        math = (int) (Math.pow(2, 30));
        for (int i = 0; i < m; i++) {
            int c1 = b % n;
            b = (z * b + t) % math;
            if (b < 0) b += math;
            int c2 = b % n;
            if (c2 < c1) {
                int c3 = c2;
                c2 = c1;
                c1 = c3;
            }
            if (c1 == 0) out += a[ c2];
            else out += a[ c2] - a[ c1 - 1];
            b = (z * b + t) % (math);
            if (b < 0) b += (math);
        }
        System.out.println(out);
    }
}
