package Простое_двоичное_дерево_поиска;

import java.util.Scanner;

public class A {

    private static final Node parentRoot = new Node(Long.MAX_VALUE, 'l');


    public static void insert(Node node, long x) {
        if (node == null) {
            parentRoot.change('l', new Node(x, 'l'));
        } else if (x == node.val) {
        } else if (x < node.val) {
            if (node.left == null) {
                node.change('l', new Node(x, 'l'));
            } else {
                insert(node.left, x);
            }
        } else {
            if (node.right == null) {
                node.change('r', new Node(x, 'r'));
            } else {
                insert(node.right, x);
            }
        }
    }

    public static void delete(Node node, long x) {
        node = exists(node, x);
        if (node == null) {
        } else {
            if (node.left == null) {
                node.parent.change(node.pos, node.right);
            } else {
                Node nodey = node.left;
                while (nodey.right != null) {
                    nodey = nodey.right;
                }
                nodey.parent.change(nodey.pos, nodey.left);
                node.val = nodey.val;
            }
        }
    }

    public static Node exists(Node node, long x) {
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

    public static Node next(Node node, Node out, long x) {
        if (node == null) {
            return out;
        } else if (x < node.val) {
            out = node;
            return next(node.left, out, x);
        } else {
            return next(node.right, out, x);
        }
    }

    public static Node prev(Node node, Node out, long x) {
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
        while (in.hasNext()) {
            c = in.next().toCharArray();
            switch (c[0]) {
                case 'i': {
                    insert(parentRoot.left, in.nextLong());
                    break;
                }
                case 'd': {
                    delete(parentRoot.left, in.nextLong());
                    break;
                }
                case 'e': {
                    sb.append(null != exists(parentRoot.left, in.nextLong())).append('\n');
                    break;
                }
                case 'n': {
                    Node a = next(parentRoot.left, null, in.nextLong());
                    sb.append(a == null ? "none" : a.val).append('\n');
                    break;
                }
                case 'p': {
                    Node a = prev(parentRoot.left, null, in.nextLong());
                    sb.append(a == null ? "none" : a.val).append('\n');
                    break;
                }
            }
        }
        System.out.println(sb.toString());
    }

    public static class Node {
        Node parent;
        Node left;
        Node right;
        long val;
        char pos;

        public Node(long val, char pos) {
            this.val = val;
            this.pos = pos;
        }

        public void change(char pos, Node node) {
            if ('l' == pos) {
                this.left = node;
            } else {
                this.right = node;
            }
            if (node != null) {
                node.parent = this;
                node.pos = pos;
            }
        }
    }

}