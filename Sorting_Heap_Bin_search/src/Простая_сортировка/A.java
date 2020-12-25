package Простая_сортировка;

import java.util.Random;
import java.util.Scanner;

public class A {
    public static long[] array;
    public static int N;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        array = new long[N];
        for (int i = 0; i < N; i++) array[i] = in.nextLong();
        QuickSort(0, N - 1);
        for (int i = 0; i < N; i++) System.out.print(array[i] + " ");
    }

    public static void QuickSort(int l, int r) {
        Random random = new Random();
        long x = array[random.nextInt(r - l + 1) + l];
        int i = l, j = r;
        while (i <= j) {
            while (array[i] < x) i++;
            while (array[j] > x) j--;
            if (i <= j) {
                long swap = array[i];
                array[i] = array[j];
                array[j] = swap;
                i++;
                j--;
            }
        }
        if (j > l) QuickSort(l, j);
        if (r > i) QuickSort(i, r);
    }
}
