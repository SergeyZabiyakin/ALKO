package Неявный_ключ;

import java.util.Scanner;

import static java.lang.Math.max;

public class F {

    private static Node parentNode = new Node(Long.MAX_VALUE, 'l');
    private static StringBuilder sb = new StringBuilder();
    private static long size = 0;

    private static void insert(Node node, long x, long val) {
        if (x < node.getLS() + 1) {
            if (node.left == null) {
                node.change('l', new Node(val, 'l'));
            } else {
                insert(node.left, x, val);
                lL(node);
                lR(node);
            }
        } else {
            if (node.right == null) {
                node.change('r', new Node(val, 'r'));
            } else {
                insert(node.right, x - node.getLS() - 1, val);
                rL(node);
                rR(node);
            }
        }
    }

    private static void delete(Node node, long x) {
        if (x < node.getLS() + 1) {
            delete(node.left, x);
            rL(node);
            rR(node);
        } else if (x > node.getLS() + 1) {
            delete(node.right, x - node.getLS() - 1);
            lL(node);
            lR(node);
        } else if (node.left == null) {
            node.parent.change(node.pos, node.right);
        } else {
            Node nodeY = node.left;
            while (nodeY.right != null) {
                nodeY = nodeY.right;
            }
            Node parentY = nodeY.parent;
            nodeY.parent.change(nodeY.pos, nodeY.left);
            node.val = nodeY.val;
            while (parentY != node.parent) {
                lL(parentY);
                lR(parentY);
                parentY = parentY.parent;
            }
        }
    }

    private static void toArray(Node node) {
        if (node == null) {
        } else if (node.left != null) {
            toArray(node.left);
            size++;
            sb.append(node.val).append(' ');
            toArray(node.right);
        } else {
            size++;
            sb.append(node.val).append(' ');
            toArray(node.right);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] c;
        long n = in.nextLong();
        long m = in.nextLong();
        parentNode.parent = parentNode;
        parentNode.change('l', new Node(in.nextLong(), 'l'));
        for (int i = 1; i < n; i++) {
            insert(parentNode.left, i, in.nextLong());
        }
        for (long i = 0; i < m; i++) {
            c = in.next().toCharArray();
            long x = in.nextLong();
            switch (c[0]) {
                case 'a': {
                    long val = in.nextLong();
                    if (parentNode.left == null) {
                        parentNode.change('l', new Node(val, 'l'));
                    } else {
                        insert(parentNode.left, x, val);
                    }
                    break;
                }
                case 'd': {
                    delete(parentNode.left, x);
                    break;
                }
            }
        }
        toArray(parentNode.left);
        System.out.println(size);
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
            this.s = 1;
        }

        private void updateH() {
            long H = 1 + max(getLH(), getRH());
            long Sum = 1 + getLS() + getRS();
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