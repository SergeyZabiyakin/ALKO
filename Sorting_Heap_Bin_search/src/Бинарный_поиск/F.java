package Бинарный_поиск;

import java.util.Scanner;

public class F {
    static long[] array;
    static int N, K;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();
        array = new long[N];
        for (int i = 0; i < N; i++) array[i] = in.nextLong();
        for (int i = 0; i < K; i++) {
            System.out.println(bin(0,N-1,in.nextLong()));

        }
    }
    static Long bin(int l, int r, long A) {
        int k = (l + r) / 2;
        while (k != l && k != r) {
            if (A > array[k]) l = k;
            else r = k;
            k = (l + r) / 2;
        }
        if(Math.abs(array[l]-A)<=Math.abs(array[r]-A))return array[l];
        return array[r];
    }
}
