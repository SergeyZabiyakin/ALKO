package И_снова_сумма;

import java.util.Scanner;

import static java.lang.Math.log;
import static java.lang.Math.max;

public class D {
    private static long mod = (long) Math.pow(10, 9);
    private static Node parentNode = new Node(Long.MAX_VALUE, 'l');


    private static void insert(Node node, long x) {
        if (x < node.val) {
            if (node.left == null) {
                node.change('l', new Node(x, 'l'));
            } else {
                insert(node.left, x);
                lL(node);
                lR(node);
            }
        } else {
            if (node.right == null) {
                node.change('r', new Node(x, 'r'));
            } else {
                insert(node.right, x);
                rL(node);
                rR(node);
            }
        }
    }

    private static Node exists(Node node, long x) {
        if (node == null) {
            return null;
        } else if (node.val == x) {
            return node;
        } else if (x < node.val) {
            return exists(node.left, x);
        } else {
            return exists(node.right, x);
        }
    }

    private static long next(Node node, long out, long x) {
        if (node == null) {
            return out;
        } else if (x < node.val) {
            out += (node.getRS() + node.val) ;
            return next(node.left, out, x);
        } else {
            return next(node.right, out, x);
        }
    }

    private static long prev(Node node, long out, long x) {
        if (node == null) {
            return out;
        } else if (x > node.val) {
            out += (node.getLS() + node.val) ;
            return prev(node.right, out, x);
        } else {
            return prev(node.left, out, x);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        char[] c;
        parentNode.parent = parentNode;
        long n = in.nextLong();
        long out = 0;
        for (long i = 0; i < n; i++) {
            c = in.next().toCharArray();
            switch (c[0]) {
                case '+': {
                    long x = (in.nextLong() + out) % mod;
                    if (exists(parentNode, x) == null) insert(parentNode, x);
                    out = 0;
                    break;
                }
                case '?': {
                    long l = in.nextLong();
                    long r = in.nextLong();
                    if (parentNode.left != null) {
                        out = parentNode.left.s - prev(parentNode.left, 0, l) - next(parentNode.left, 0, r);
                    } else {
                        out = 0;
                    }
                    sb.append(out).append('\n');
                    break;
                }
            }
        }
        System.out.println(sb.toString());
    }

    private static void lL(Node node) {
        if (node != parentNode && node.getLH() - node.getRH() > 1 && node.left.getLH() >= node.left.getRH()) {
            Node left = node.left;
            node.change('l', left.right);
            node.parent.change(node.pos, left);
            left.change('r', node);
        }
    }

    private static void lR(Node node) {
        if (node != parentNode && node.getLH() - node.getRH() > 1 && node.left.getLH() < node.left.getRH()) {
            Node left = node.left;
            Node z = left.right;
            node.parent.change(node.pos, z);
            node.change('l', z.right);
            left.change('r', z.left);
            z.change('l', left);
            z.change('r', node);
        }
    }

    private static void rR(Node node) {
        if (node != parentNode && node.getRH() - node.getLH() > 1 && node.right.getRH() >= node.right.getLH()) {
            Node right = node.right;
            node.change('r', right.left);
            node.parent.change(node.pos, right);
            right.change('l', node);
        }
    }

    private static void rL(Node node) {
        if (node != parentNode && node.getRH() - node.getLH() > 1 && node.right.getRH() < node.right.getLH()) {
            Node right = node.right;
            Node z = right.left;
            node.parent.change(node.pos, z);
            node.change('r', z.left);
            right.change('l', z.right);
            z.change('l', node);
            z.change('r', right);
        }
    }

    public static class Node {
        Node parent;
        Node left;
        Node right;
        long val;
        char pos;
        long h;
        long s;

        private Node(long val, char pos) {
            this.val = val;
            this.pos = pos;
            this.h = 1;
            this.s = val;
        }

        private void updateH() {
            long H = 1 + max(getLH(), getRH());
            long Sum = this.val + getLS() + getRS();
            if (this.h != H || this.s != Sum) {
                this.s = Sum;
                this.h = H;
                this.parent.updateH();
            }
        }

        private long getLS() {
            return this.left != null ? this.left.s : 0;
        }

        private long getRS() {
            return this.right != null ? this.right.s : 0;
        }

        private long getLH() {
            return this.left != null ? this.left.h : 0;
        }

        private long getRH() {
            return this.right != null ? this.right.h : 0;
        }

        private void change(char pos, Node node) {
            if ('l' == pos) {
                this.left = node;
            } else {
                this.right = node;
            }
            if (node != null) {
                node.parent = this;
                node.pos = pos;
            }
            updateH();
        }
    }

}