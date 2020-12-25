package Количество_инверсий;

import java.math.BigInteger;
import java.util.Scanner;

public class C {
    static int[] array;
    static int N;
    static BigInteger out = BigInteger.valueOf(0);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        array = new int[N];
        for (int i = 0; i < N; i++) array[i] = in.nextInt();
        M(0, N - 1);
        System.out.println(out);
    }

    static void M(int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            M(l, mid);
            M(mid + 1, r);
            int[] array2 = new int[r - l + 1];
            int X = 0, L = l, Mid = mid + 1;
            while (X < r - l + 1) {
                if (Mid == r + 1 || (L < mid + 1 && array[L] <= array[Mid])) {
                    array2[X++] = array[L++];
                } else {
                    array2[X++] = array[Mid++];
                    out=out.add(BigInteger.valueOf(mid + 1 - L));
                }
            }
            for (int i = 0; i < X; i++) {
                array[l++] = array2[i];
            }
        }
    }
}
