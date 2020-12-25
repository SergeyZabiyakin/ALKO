package I;

import java.util.Scanner;

public class I {
    static double C;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        C = in.nextDouble();
        double l = 0, r = C;
        double m = (l + r) / 2;
        for (int i = 0; i < 100; i++) {
            if (f(m) < C) {
                l=m;
            } else {
                r = m;
            }
            m = (l + r) / 2;
        }
        System.out.print(m);
    }

    static double f(double x) {
        return Math.sqrt(x) + x * x;
    }
}
