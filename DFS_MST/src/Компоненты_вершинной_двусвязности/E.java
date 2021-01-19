package Компоненты_вершинной_двусвязности;

import java.util.*;

public class E {
    private static boolean[] mark;
    private static int[] in;
    private static int[] up;
    private static int time = 0;
    private static List<Edge>[] graph;
    private static final StringBuilder sb = new StringBuilder();
    private static final Stack<Integer> stack = new Stack<>();
    private static int[] out;
    private static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        graph = new LinkedList[n];
        mark = new boolean[n];
        in = new int[n];
        up = new int[n];
        out = new int[m];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList();
        }
        for (int i = 0; i < m; i++) {
            int begin = scanner.nextInt() - 1;
            int end = scanner.nextInt() - 1;
            graph[begin].add(new Edge(end, i));
            graph[end].add(new Edge(begin, i));
        }
        for (int i = 0; i < n; i++) {
            if (!mark[i]) {
                dfs(i, null);
            }
        }
        System.out.println(count);
        for (int i : out) {
            sb.append(i).append(' ');
        }
        System.out.println(sb);
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
                if (out[edge.num] == 0) stack.add(edge.num);
                if (!mark[edge.v]) {
                    dfs(edge.v, edge);
                    up[v] = Math.min(up[v], up[edge.v]);
                    if (up[edge.v] >= in[v]) {
                        count++;
                        while (stack.peek() != edge.num) {
                            out[stack.pop()] = count;
                        }
                        out[stack.pop()] = count;
                    }
                } else {
                    up[v] = Math.min(up[v], in[edge.v]);
                }
            }
        }
    }
}

