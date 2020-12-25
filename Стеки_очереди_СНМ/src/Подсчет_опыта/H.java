package Подсчет_опыта;

import java.util.Scanner;

public class H {
    static int[][] a;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        a = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            a[i][0] = i;
            a[i][1] = 1;
            a[i][2] = 0;
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            String str = in.next();
            if (str.charAt(0) == 'g') {
                int x = in.nextInt();
                int out=Sum(x);
                System.out.println(out);
            } else if (str.charAt(0) == 'a') {
                int x = in.nextInt();
                x = Find(x);
                a[x][2]+=in.nextInt();
            } else {
                int x = in.nextInt();
                int y = in.nextInt();
                x = Find(x);
                y = Find(y);
                if (x != y) {
                    if (a[x][1] < a[y][1]) {
                        int k = x;
                        x = y;
                        y = k;
                    }
                    a[y][0] = x;
                    a[y][2] -= a[x][2];
                    a[x][1] += a[y][1];
                }
            }
        }
    }

    static int Find(int x) {
        while (a[x][0] != x) {
            x=a[x][0];
        }
        return a[x][0];
    }
    static int Sum(int x) {
        int out=a[x][2];
        while (a[x][0] != x) {
            x=a[x][0];
            out+=a[x][2];
        }
        return out;
    }
}
