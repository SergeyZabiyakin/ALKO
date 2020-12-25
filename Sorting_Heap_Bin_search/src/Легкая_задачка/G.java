package Легкая_задачка;

import java.util.Scanner;

public class G {
    static int n, x, y;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        x = in.nextInt();
        y = in.nextInt();
        int l = 0, r = n - 1;
        int i = 0;
        int out = 2147483647;
        while (r > -1) {
            int G = Math.max(l * x, r * y);
            if (G < out) out = G;
            i++;
            r--;
            l++;
        }
        System.out.println(out + Math.min(x, y));
    }

}
