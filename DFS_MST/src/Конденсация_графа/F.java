package Конденсация_графа;

import java.util.*;

public class F {
    private static boolean[] mark;
    private static int[] in;
    private static int[] up;
    private static int time = 0;
    private static List<Integer>[] graph;
    private static final Stack<Integer> stack = new Stack<>();
    private static boolean[] inStack;
    private static int count = 0;
    private static int[] kond;
    private static final Set<Pair> set = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        graph = new LinkedList[n];
        mark = new boolean[n];
        in = new int[n];
        up = new int[n];
        inStack = new boolean[n];
        kond = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList();
        }
        for (int i = 0; i < m; i++) {
            int begin = scanner.nextInt() - 1;
            int end = scanner.nextInt() - 1;
            graph[begin].add(end);
        }
        for (int i = 0; i < n; i++) {
            if (!mark[i]) {
                dfs(i);
            }
        }
        for (int v = 0; v < n; v++) {
            for (int vu : graph[v]) {
                if (kond[v] != kond[vu]) {
                    set.add(new Pair(kond[v], kond[vu]));
                }
            }
        }
        System.out.println(set.size());
    }

    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static void dfs(int v) {
        stack.add(v);
        inStack[v] = true;
        mark[v] = true;
        up[v] = time++;
        in[v] = up[v];
        for (int vu : graph[v]) {
            if (!mark[vu]) {
                dfs(vu);
                up[v] = Math.min(up[v], up[vu]);
            } else if (inStack[vu]) {
                up[v] = Math.min(up[v], in[vu]);
            }
        }
        if (up[v] == in[v]) {
            count++;
            while (stack.peek() != v) {
                inStack[stack.peek()] = false;
                kond[stack.pop()] = count;
            }
            inStack[stack.peek()] = false;
            kond[stack.pop()] = count;
        }
    }
}
