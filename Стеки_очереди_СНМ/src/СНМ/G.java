package СНМ;

import java.util.Scanner;

public class G {
    static int[][] a;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb=new StringBuilder();
        int n = in.nextInt();
        a = new int[n + 1][4];
        for (int i = 1; i <= n; i++) {
            a[i][0] = i;
            a[i][1] = i;
            a[i][2] = i;
            a[i][3] = 1;
        }
        while (in.hasNext()) {
            if (in.next().charAt(0) == 'g') {
                int x = in.nextInt();
                while (a[x][0] != x) x=a[x][0];
                sb.append(a[x][1] + " " + a[x][2] + " " + a[x][3]+"\n");
            } else {
                int x = in.nextInt();
                int y = in.nextInt();
                while (a[x][0] != x) x=a[x][0];
                while (a[y][0] != y) y=a[y][0];
                if (x != y) {
                    if (a[x][3] < a[y][3]) {
                        int k = x;
                        x = y;
                        y = k;
                    }
                    if (a[x][1] > a[y][1]) a[x][1] = a[y][1];
                    if (a[x][2] < a[y][2]) a[x][2] = a[y][2];
                    a[y][0] = x;
                    a[x][3] += a[y][3];
                }
            }
        }
        System.out.println(sb);
    }
}
