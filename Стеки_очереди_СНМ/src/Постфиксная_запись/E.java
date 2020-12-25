package Постфиксная_запись;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> a = new ArrayList<>();
        while (in.hasNext()) {
            String str = in.next();
            char c = str.charAt(0);
            if (Character.isDigit(c))
                a.add(Character.digit(c, 10));
            if ('+' == c) {
                a.add(a.remove(a.size() - 2) + a.remove(a.size() - 1));
            }
            if ('*' == c) {
                a.add(a.remove(a.size() - 2) * a.remove(a.size() - 1));
            }
            if ('-' == c) {
                a.add(a.remove(a.size() - 2) - a.remove(a.size() - 1));
            }

        }
        System.out.println(a.get(0));
    }
}