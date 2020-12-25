package Поляна_дров;

import java.util.Scanner;

public class J {
    static long vp, vf;
    static double a;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        vf = in.nextLong();
        vp = in.nextLong();
        a = in.nextDouble();
        bin(0, 1);
    }

    static void bin(double l, double r) {
        double k = (l + r) / 2;
        while (f(k)!=0) {
            if (f(k) < 0) l = k;
            else r = k;
            k = (l + r) / 2;
            if(l==r||k==l||k==r) break;
        }
        System.out.println(k);
    }

    static double f(double x) {
        return (vp*x)/(Math.sqrt(1-2*a+a*a+x*x))+(vf*(x-1))/(Math.sqrt(1-2*x+x*x+a*a));
    }
}
