package Кузнечик_собирает_монеты;

import java.io.*;
import java.util.*;


public class A {
    static int n;
    static int k;
    static long[] array;
    static long[] array1;

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner in = new Scanner(new File("input.txt"));
            n = in.nextInt();
            k = in.nextInt();
            array = new long[n];
            array1 = new long[n];
            for (int i = 1; i < n - 1; i++) {
                array[i] = in.nextInt();
            }
            array[n - 1] = 0;
            array[0] = 0;
            in.close();
            Stack<Pair> stackA = new Stack<>(), stackB = new Stack<>();
            stackA.add(new Pair(0, 0));
            long maxi = 0;
            array1[0] = 0;
            for (int i = 1; i < n; i++) {
                array1[i] = maxi + array[i];
                if (stackA.size() + stackB.size() == k) {
                    if (stackB.size() > 0) {
                        stackB.pop();
                        stackA.add(new Pair(array1[i], Math.max(array1[i], stackA.peek().getRight())));
                        if (stackB.size() == 0) {
                            maxi = stackA.peek().getRight();
                        } else {
                            maxi = Math.max(stackA.peek().getRight(), stackB.peek().getRight());
                        }
                    } else {
                        Pair pair = stackA.pop();
                        pair.setRight(pair.getLeft());
                        stackB.add(pair);
                        while (stackA.size() > 0) {
                            Pair pair1 = stackA.pop();
                            pair1.setRight(Math.max(pair1.getLeft(), stackB.peek().getRight()));
                            stackB.add(pair1);
                        }
                        stackB.pop();
                        stackA.add(new Pair(array1[i], array1[i]));
                        if (stackB.size() == 0) {
                            maxi = stackA.peek().getRight();
                        } else {
                            maxi = Math.max(stackA.peek().getRight(), stackB.peek().getRight());
                        }
                    }
                } else {
                    stackA.add(new Pair(array1[i], Math.max(array1[i], stackA.peek().getRight())));
                    maxi = stackA.peek().getRight();
                }
            }
            sb.append(array1[n - 1]);
            sb.append('\n');
            int up = 0;
            List<Integer> upper = new LinkedList<>();
            upper.add(n);
            for (int i = n - 2; i > -1; i--) {
                if (array1[i] == array1[n - 1]) {
                    up++;
                    upper.add(0, i + 1);
                    array1[n - 1] -= array[i];
                }
            }
            sb.append(up);
            sb.append('\n');
            for (int i : upper) {
                sb.append(i);
                sb.append(' ');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter out = new FileWriter(new File("output.txt"));
            out.write(sb.toString());
            out.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static class Pair {

        private long left;
        private long right;

        public Pair(long left, long right) {
            this.left = left;
            this.right = right;
        }

        public long getLeft() {
            return left;
        }

        public long getRight() {
            return right;
        }

        public void setRight(long right) {
            this.right = right;
        }
    }
}