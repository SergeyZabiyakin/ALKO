package Cows_in_a_Skyscraper;

import java.io.*;
import java.util.*;

import static java.lang.Integer.min;

public class K {
    static int n;
    static int w;
    static int[] a;
    static int[][] b;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("skyscraper.in"));//skyscraper.in
            n = in.nextInt();
            w = in.nextInt();
            a = new int[n];
            b = new int[(1 << n)][2];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            in.close();
            b[0][0] = 0;
            b[0][1] = 0;
            for (int i = 1; i < (1 << n); i++) {
                b[i][0] = n;
                b[i][1] = w;
            }
            for (int i = 0; i < (1 << n); i++) {
                for (int j = 0; j < n; j++) {
                    if (((i & (1 << j)) == 0)) {
                        int f, s;
                        if (a[j] + b[i][1] <= w) {
                            f = b[i][0];
                            s = a[j] + b[i][1];
                        } else {
                            f = b[i][0] + 1;
                            s = a[j];
                        }
                        b[i + (1 << j)][0] = min(f, b[i + (1 << j)][0]);
                        b[i + (1 << j)][1] = min(s, b[i + (1 << j)][1]);
                    }
                }
            }
            int mech = b[(1 << n) - 1][1] == 0 ? b[(1 << n) - 1][0] : b[(1 << n) - 1][0] + 1;
            sb.append(mech);
            sb.append('\n');
            int x = (1 << n) - 1;
            for (int i = (1 << n) - 1; i > 0; i = (i - 1) & x) {
                int mech2 = b[i][1] == 0 ? b[i][0] : b[i][0] + 1;
                if (mech2 == mech - 1 && sw(x - i)) {
                    f(x - i);
                    x = i;
                    mech--;
                }
            }
            f(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter out = new FileWriter(new File("skyscraper.out"));
            out.write(sb.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean sw(int i) {
        int out = 0;
        int k = 0;
        for (int j = 1; j <= i; j *= 2) {
            if ((i & j) != 0) {
                out += a[k];
            }
            k++;
        }
        return out <= w;
    }

    static void f(int i) {
        StringBuilder sv = new StringBuilder();
        int k = 1;
        int c = 0;
        for (int j = 1; j <= i; j *= 2) {
            if ((i & j) != 0) {
                sv.append(k);
                sv.append(' ');
                c++;
            }
            k++;
        }
        sb.append(c);
        sb.append(' ');
        sb.append(sv);
        sb.append('\n');
    }
}
