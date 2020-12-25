package Шарики;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> a = new ArrayList<>();
        a.add(in.nextInt());
        int out = 0, j = 1;
        while (in.hasNext()) {
            a.add(in.nextInt());
            if (a.get(a.size() - 2).intValue() == a.get(a.size() - 1)) j++;
            else {
                if (j >= 3) {
                    out += j;
                    for (int k = 0; k < j; k++) {
                        a.remove(a.size() - 2);
                    }
                    j = 1;
                    while (a.size() - j > 0 && a.get(a.size() - j - 1).intValue() == a.get(a.size() - j)) j++;
                } else j = 1;
            }
        }
        if (j >= 3) {
            out += j;
        }
        System.out.println(out);
    }

}
