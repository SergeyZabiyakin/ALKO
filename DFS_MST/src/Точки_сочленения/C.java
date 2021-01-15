package Точки_сочленения;


import java.util.*;

public class C {
    private static boolean[] mark;
    private static int[] in;
    private static int[] up;
    private static int time = 0;
    private static int countChild = 0;
    private static List<Edge>[] graph;
    private static final StringBuilder sb = new StringBuilder();
    private static final Set<Integer> set = new TreeSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        graph = new LinkedList[n];
        mark = new boolean[n];
        in = new int[n];
        up = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList();
        }
        for (int i = 0; i < m; i++) {
            int begin = scanner.nextInt() - 1;
            int end = scanner.nextInt() - 1;
            graph[begin].add(new Edge(end, i + 1));
            graph[end].add(new Edge(begin, i + 1));
        }
        for (int i = 0; i < n; i++) {
            if (!mark[i]) {
                countChild = 0;
                time = 0;
                dfs(i, null);
                if (countChild > 1) {
                    set.add(i + 1);
                }
            }
        }
        System.out.println(set.size());
        set.forEach(it -> sb.append(it).append(' '));
        System.out.println(sb.toString());
    }

    static class Edge {
        private final int v;
        private final int num;

        public Edge(int x, int y) {
            this.v = x;
            this.num = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return num == edge.num;
        }
    }

    private static void dfs(int v, Edge e) {
        mark[v] = true;
        up[v] = time++;
        in[v] = up[v];
        for (Edge edge : graph[v]) {
            if (!edge.equals(e)) {
                if (!mark[edge.v]) {
                    if (in[v] == 0) countChild++;
                    dfs(edge.v, edge);
                    up[v] = Math.min(up[v], up[edge.v]);
                    if (up[edge.v] >= in[v] && in[v] != 0) set.add(v + 1);
                } else {
                    up[v] = Math.min(up[v], in[edge.v]);
                }
            }
        }
    }
}