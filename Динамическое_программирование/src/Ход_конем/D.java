package Ход_конем;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class D {
    static long[][] a;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        a = new long[10][n + 1];
        for (int i = 0; i < 10; i++) {
            a[i][1] = 1;
        }
        a[0][1] = 0;
        a[8][1] = 0;
        for (int i = 2; i < n + 1; i++) {
            a[0][i] = (a[4][i - 1] + a[6][i - 1]) % 1000000000;
            a[1][i] = (a[8][i - 1] + a[6][i - 1]) % 1000000000;
            a[2][i] = (a[7][i - 1] + a[9][i - 1]) % 1000000000;
            a[3][i] = (a[4][i - 1] + a[8][i - 1]) % 1000000000;
            a[4][i] = (a[3][i - 1] + a[9][i - 1] + a[0][i - 1]) % 1000000000;
            a[6][i] = (a[1][i - 1] + a[7][i - 1] + a[0][i - 1]) % 1000000000;
            a[7][i] = (a[2][i - 1] + a[6][i - 1]) % 1000000000;
            a[8][i] = (a[1][i - 1] + a[3][i - 1]) % 1000000000;
            a[9][i] = (a[4][i - 1] + a[2][i - 1]) % 1000000000;

        }
        long out = 0;
        for (int i = 0; i < 10; i++) {
            out += a[i][n];
        }
        System.out.println(out % 1000000000);
    }
}
