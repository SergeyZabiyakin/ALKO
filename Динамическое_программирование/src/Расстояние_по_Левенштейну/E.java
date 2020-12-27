package Расстояние_по_Левенштейну;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.min;

public class E {
    static String str1, str2;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        str1 = in.nextLine();
        str2 = in.nextLine();
        char[] astr1 = str1.toCharArray();
        char[] astr2 = str2.toCharArray();
        int[][] out = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < str1.length() + 1; i++) {
            for (int j = 0; j < str2.length() + 1; j++) {
                if (i == 0) {
                    out[i][j] = j;
                } else if (j == 0) {
                    out[i][j] = i;
                } else if (astr1[i - 1] == astr2[j - 1]) {
                    out[i][j] = out[i - 1][j - 1];
                } else {
                    out[i][j] = min(out[i - 1][j], out[i][j - 1]);
                    out[i][j] = min(out[i][j], out[i - 1][j - 1]) + 1;
                }
                sb.append(out[i][j]);
                sb.append(' ');
            }
            sb.append('\n');
        }
        //System.out.println(sb);
        System.out.println(out[str1.length()][str2.length()]);
    }
}