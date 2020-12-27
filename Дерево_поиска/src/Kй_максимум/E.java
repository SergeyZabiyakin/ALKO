package Kй_максимум;

import java.util.Scanner;

import static java.lang.Math.max;

public class E {

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

    private static void delete(Node node, long x) {
        if (x < node.val) {
            delete(node.left, x);
            rL(node);
            rR(node);
        } else if (x > node.val) {
            delete(node.right, x);
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

    private static long prev(Node node, long x) {
        if (x == node.getLS() + 1) {
            return node.val;
        } else if (x > node.getLS()) {
            return prev(node.right, x - node.getLS() - 1);
        } else {
            return prev(node.left, x);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        char[] c;
        parentNode.parent = parentNode;
        long n = in.nextLong();
        for (long i = 0; i < n; i++) {
            c = in.next().toCharArray();
            long x = in.nextLong();
            switch (c[0]) {
                case '+':
                case '1': {
                    insert(parentNode, x);
                    break;
                }
                case '0': {
                    sb.append(prev(parentNode.left, parentNode.s  - x)).append('\n');
                    break;
                }
                case '-': {
                    delete(parentNode, x);
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