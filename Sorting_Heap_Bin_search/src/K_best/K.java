package K_best;

import java.util.Random;
import java.util.Scanner;

public class K {
    static int[][] array1;
    static double[][] array2;
    static int n, k;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
        array1 = new int[n][2];
        array2 = new double[n][2];
        for (int i = 0; i < n; i++) {
            array1[i][0] = in.nextInt();
            array1[i][1] = in.nextInt();
        }
        bin(0, 100000.01);
    }

    static void bin(double l, double r) {
        double mid = (l + r) / 2;
        while (mid != l && mid != r) {
            if (f(mid) >= 0) l = mid;
            else r = mid;
            mid = (l + r) / 2;
        }
        f(mid);
        for (int i = 0; i < k; i++) {
            System.out.println((int) array2[n - 1 - i][1]);
        }
    }

    static double f(double x) {
        double out = 0;
        for (int i = 0; i < n; i++) {
            array2[i][0] = (array1[i][0] - array1[i][1] * x);
            array2[i][1] = i + 1;
        }
        QuickSort(0, n - 1);
        for (int i = 0; i < k; i++) {
            out += array2[n - 1 - i][0];
        }
        return out;
    }


    static void QuickSort(int l, int r) {
        Random random = new Random();
        double x = array2[random.nextInt(r - l + 1) + l][0];
        int i = l, j = r;
        while (i <= j) {
            while (array2[i][0] < x) i++;
            while (array2[j][0] > x) j--;
            if (i <= j) {
                double swap1 = array2[i][0];
                double swap2 = array2[i][1];
                array2[i][0] = array2[j][0];
                array2[i][1] = array2[j][1];
                array2[j][0] = swap1;
                array2[j][1] = swap2;
                i++;
                j--;
            }
        }
        if (j > l ) QuickSort(l, j);
        if (r > i ) QuickSort(i, r);
    }
}
