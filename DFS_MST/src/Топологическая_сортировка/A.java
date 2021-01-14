package Топологическая_сортировка;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class A {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = scanner.nextInt() + 1;
        int count = 0;
        int m = scanner.nextInt();
        int[] in = new int[n]; // кол-во входящих вершин
        LinkedList<Integer>[] graf = new LinkedList[n];

        for (int i = 1; i < n; i++) {
            graf[i] = new LinkedList();
        }

        for (int i = 0; i < m; i++) {
            int begin = scanner.nextInt();
            int end = scanner.nextInt();
                graf[begin].add(end);
                in[end]++;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < n; i++) {
            if (in[i] == 0) {
                stack.add(i);
            }
        }
        while (!stack.isEmpty()) {
            int node = stack.pop();
            count++;
            sb.append(node).append(' ');
            for (int i : graf[node]) {
                in[i]--;
                if (in[i] == 0) stack.add(i);
            }
        }
        if (count == n - 1) {
            System.out.println(sb.toString());
        } else {
            System.out.println("-1 ");
        }
    }
}