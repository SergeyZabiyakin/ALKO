package Дипломы;

import java.util.Scanner;

public class H {
    static long w, h, n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        w = in.nextLong();
        h = in.nextLong();
        n = in.nextLong();
        bin(0, n * Math.max(w,h));
    }

    static void bin(long l, long r) {
        long k = (l + r) / 2;
        while (l < r) {
            if (f(k) == false) l = k + 1;
            else r = k;
            k = (l + r) / 2;
        }
        if (f(l) == true) {
            k = r;
        }
        if (f(r) == true) {
            k = r;
        }
        System.out.println(k);
    }

    static Boolean f(long x) {
        if (((x / w) * (x / h)) >= n) {
            return true;
        }
        return false;
    }
}
