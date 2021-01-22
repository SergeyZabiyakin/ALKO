package Планирование_вечеринки;

import java.util.*;

public class G {
    private static int count = -1;
    private static int time = 0;
    private static int[] in;
    private static int[] up;
    private static int[] cond;
    private static boolean[] mark;
    private static boolean[] inStack;
    private static List<Integer>[] graph;
    private static final Stack<Integer> stackV = new Stack<>();
    private static final Map<String, Integer> mapName = new HashMap<>();
    private static final Map<Integer, String> name = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt() * 2;
        int m = scanner.nextInt();
        for (int i = 0; i < n; i += 2) {
            String str = scanner.next();
            mapName.put("+" + str, i);
            mapName.put("-" + str, i + 1);
            name.put(i, str);
        }
        graph = new LinkedList[n];
        mark = new boolean[n];
        in = new int[n];
        up = new int[n];
        inStack = new boolean[n];
        cond = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList();
        }

        for (int i = 0; i < m; i++) {
            String name = scanner.next();
            scanner.next();
            String name1 = scanner.next();
            int begin = mapName.get(name);
            int end = mapName.get(name1);
            graph[begin].add(end);
            //инвертируем : x => y <=> !y => !x
            begin ^= 1;
            end ^= 1;
            graph[end].add(begin);
        }
        for (int i = 0; i < n; i++) {
            if (!mark[i]) {
                dfs(i);
            }
        }
        for (int i = 0; i < n; i += 2) {
            if (cond[i] == cond[i + 1]) {
                System.out.println("-1 ");
                return;
            }
        }
        count++;
        List<Integer>[] graph1 = new LinkedList[count];
        int[] inE = new int[count]; // кол-во входящих вершин
        for (int i = 0; i < count; i++) {
            graph1[i] = new LinkedList();
        }
        for (int v = 0; v < n; v++) {
            for (int vu : graph[v]) {
                if (cond[v] != cond[vu]) {
                    graph1[cond[v]].add(cond[vu]);
                    inE[cond[vu]]++;
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < count; i++) {
            if (inE[i] == 0) stack.add(i);
        }
        int index = 0;
        int[] topSort = new int[count];
        while (!stack.isEmpty()) {
            int node = stack.pop();
            map.put(node, index);
            topSort[index++] = node;
            for (int i : graph1[node]) {
                inE[i]--;
                if (inE[i] == 0) stack.add(i);
            }
        }
        int[] answer = new int[count];
        int[] pair = new int[count];
        for (int i = 0; i < n; i += 2) {
            pair[cond[i]] = cond[i + 1];
            pair[cond[i + 1]] = cond[i];
        }
        for (int i = 0; i < count; i++) {
            if (answer[i] != 1) {
                int p = pair[topSort[i]];
                answer[map.get(p)] = 1;
            }
        }
        count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i += 2) {
            if (answer[map.get(cond[i])] == 1) {
                count++;
                sb.append(name.get(i)).append('\n');
            }
        }
        System.out.println(count);
        System.out.println(sb);
    }

    private static void dfs(int v) {
        stackV.add(v);
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
            while (stackV.peek() != v) {
                inStack[stackV.peek()] = false;
                cond[stackV.pop()] = count;
            }
            inStack[stackV.peek()] = false;
            cond[stackV.pop()] = count;
        }
    }
}
