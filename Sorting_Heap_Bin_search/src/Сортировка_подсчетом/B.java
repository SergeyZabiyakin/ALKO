package Сортировка_подсчетом;

import java.util.ArrayList;
import java.util.Scanner;

public class B {
    public static ArrayList<Integer> a = new ArrayList<>();
    public static int[] b = new int[101];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) a.add(in.nextInt());
        for (int i : a) b[i]++;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < b[i]; j++)
                System.out.print(i + " ");
        }
    }
}
