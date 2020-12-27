package Черепаха_и_монеты;

import java.io.*;
import java.util.*;

import static java.lang.Integer.max;

public class B {
    static int n;
    static int m;
    static int[][] array, array2;

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner in = new Scanner(new File("input.txt"));
            n = in.nextInt();
            m = in.nextInt();
            array = new int[n][m];
            array2 = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    array[i][j] = in.nextInt();
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i - 1 >= 0 && j - 1 >= 0) array2[i][j] = max(array2[i - 1][j], array2[i][j - 1]) + array[i][j];
                    else if (i - 1 >= 0 && j - 1 < 0) array2[i][j] = array2[i - 1][j] + array[i][j];
                    else if (i - 1 < 0 && j - 1 >= 0) array2[i][j] = array2[i][j - 1] + array[i][j];
                    else array2[i][j] = array[i][j];
                }
            }
            List<String> list = new LinkedList<>();
            sb.append(array2[n - 1][m - 1]);
            sb.append('\n');
            int i = n - 1;
            int j = m - 1;
            while (list.size() < n + m - 1) {
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (array2[i - 1][j] == array2[n - 1][m - 1] - array[i][j]) {
                        list.add("D");
                        array2[n - 1][m - 1] = array2[i - 1][j];
                        i--;
                    } else {
                        list.add("R");
                        array2[n - 1][m - 1] = array2[i][j - 1];
                        j--;
                    }
                }
                if (i == 0) {
                    list.add("R");
                    j--;
                }
                else if (j == 0) {
                    list.add("D");
                    i--;
                }
            }
            Collections.reverse(list);
            list.remove(0);
            for (int k = 0; k < list.size(); k++) {
                sb.append(list.get(k));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter out = new FileWriter(new File("output.txt"));
            out.write(sb.toString());
            out.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
