package Наибольшая_возрастающая_подпоследовательность;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class C {
    static int[] a, b;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = in.nextInt();
        a = new int[n];
        b = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] < a[i] && b[j] > b[i]) {
                    b[i] = b[j];
                }
            }
            b[i]++;
            if (max < b[i]) max = b[i];
        }
        sb.append(max);
        sb.append('\n');
        List<Integer> list = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (b[i] == max) {
                list.add(0, a[i]);
                max--;
            }
        }
        for (int i : list) {
            sb.append(i);
            sb.append(' ');
        }
        System.out.println(sb);
    }
}