package Парковка;

import java.io.File;
import java.io.FileWriter;
import java.util.*;


public class K {
    static int n;
    static int[] array;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("parking.in"));
        n = in.nextInt();
        int M = in.nextInt();
        int m = Integer.highestOneBit(n);
        if (m < n) m *= 2;
        array = new int[2 * m - 1];
        Arrays.fill(array, 1);
        sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String str = in.next();
            if (str.charAt(1) == 'x') {
                exit(in.nextInt() - 1, 0, 0, m);
            } else {
                int out = enter(in.nextInt() - 1, 0, 0, m);
                if (out == -1) {
                    out = enter(0, 0, 0, m);
                }
                sb.append(out);
                sb.append('\n');
            }
        }
        in.close();
        FileWriter output = new FileWriter(new File("parking.out"));
        output.write(sb.toString());
        output.close();
    }

    public static void exit(int i, int x, int lx, int rx) {
        if (rx - lx == 1) {
            array[x] = 1;
            return;
        }
        int m = (rx + lx) / 2;
        if (i < m) {
            exit(i, 2 * x + 1, lx, m);
        } else {
            exit(i, 2 * x + 2, m, rx);
        }
        array[x] = 1;
    }

    public static int enter(int i, int x, int lx, int rx) {
        if (lx >= n) return -1;
        if (rx - lx == 1) {
            if (array[x] == 0) {
                return -1;
            }
            array[x] = 0;
            return rx;
        }
        int m = (rx + lx) / 2;
        int out;
        if (i < m && array[2 * x + 1] != 0) {
            out = enter(i, 2 * x + 1, lx, m);
            if (out == -1) out = enter(i, 2 * x + 2, m, rx);
        } else {
            out = enter(i, 2 * x + 2, m, rx);
        }
        array[x] = array[2 * x + 1] + array[2 * x + 2];
        return out;
    }
}
