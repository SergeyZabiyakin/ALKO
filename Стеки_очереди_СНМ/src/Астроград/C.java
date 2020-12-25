package Астроград;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> a = new ArrayList<>();
        int n = in.nextInt();
        in.nextInt();
        a.add(in.nextInt());
        for (int i = 1; i < n; i++) {
            int j = in.nextInt();
            if (j == 1) {
                a.add(0, in.nextInt());
            }
            if (j == 2) {
                a.remove(a.size()-1);
            }
            if (j == 3) {
                a.remove(0);
            }
            if (j == 4) {
                int q = in.nextInt();
                System.out.println(a.size() - a.indexOf(q)-1);
            }
            if (j == 5) {
                System.out.println(a.get(a.size() - 1));
            }
        }
    }
}
