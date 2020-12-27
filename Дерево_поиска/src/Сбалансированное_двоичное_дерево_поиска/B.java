package Сбалансированное_двоичное_дерево_поиска;

import java.util.Scanner;

import static java.lang.Math.max;

public class B {

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

    private static Node next(Node node, Node out, long x) {
        if (node == null) {
            return out;
        } else if (x < node.val) {
            out = node;
            return next(node.left, out, x);
        } else {
            return next(node.right, out, x);
        }
    }

    private static Node prev(Node node, Node out, long x) {
        if (node == null) {
            return out;
        } else if (x > node.val) {
            out = node;
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
        //for (long i = 0; i < 10; i++) {
        while (in.hasNext()) {
            c = in.next().toCharArray();
            long x = in.nextLong();
            switch (c[0]) {
                case 'i': {
                    if (exists(parentNode, x) == null) insert(parentNode, x);
                    break;
                }
                case 'd': {
                    if (exists(parentNode, x) != null) delete(parentNode, x);
                    break;
                }
                case 'e': {
                    sb.append(null != exists(parentNode, x)).append('\n');
                    break;
                }
                case 'n': {
                    Node a = next(parentNode, null, x);
                    sb.append(a == parentNode ? "none" : a.val).append('\n');
                    break;
                }
                case 'p': {
                    Node a = prev(parentNode, null, x);
                    sb.append(a == null ? "none" : a.val).append('\n');
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

        private Node(long val, char pos) {
            this.val = val;
            this.pos = pos;
            this.h = 1;
        }

        private void updateH() {
            long H = 1 + max(getLH(), getRH());
            if (this.h != H) {
                this.h = H;
                this.parent.updateH();
            }
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