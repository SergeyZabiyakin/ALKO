package Быстрый_поиск_в_массиве;

import java.util.Random;
import java.util.Scanner;

public class E {
    static long[] array;
    static int N;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        array = new long[N];
        for (int i = 0; i < N; i++) array[i] = in.nextLong();
        N = in.nextInt();
        quickSort(0, array.length - 1);
        for (int i = 0; i < N; i++) {
            System.out.print(-binA(0, array.length - 1, in.nextLong()) + binB(0, array.length - 1, in.nextLong()) + " ");
        }
    }

    static void quickSort(int l, int r) {
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
        if (j > l) quickSort(l, j);
        if (r > i) quickSort(i, r);
    }

    static Integer binA(int l, int r, long A) {
        int k = (l + r) / 2;
        while (k != l && k != r) {
            if (A > array[k]) l = k;
            else r = k;
            k = (l + r) / 2;
        }
        if (array[l] > A) return l;
        if (array[l] == A) return Math.max(l - 1, 0);
        if (array[r] >= A) return r;
        return r + 1;

    }

    static Integer binB(int l, int r, long B) {
        int k = (l + r) / 2;
        while (k != l && k != r) {
            if (B >= array[k]) l = k;
            else r = k;
            k = (l + r) / 2;
        }
        if (array[r] <= B) return r + 1;
        if (array[l] <= B) return l + 1;
        return l;
    }
}
