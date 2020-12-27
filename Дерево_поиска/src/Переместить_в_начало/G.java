package Переместить_в_начало;

import java.util.Scanner;

public class G {

    private static Node pNode = new Node(1, 0);
    private static StringBuilder sb = new StringBuilder();


    private static void insert(Node node, long x) {
        node.s++;
        if (x < node.val) {
            if (node.left == null) {
                node.change(0, new Node(x, 0));
                Splay(node.get(0));
            } else {
                insert(node.left, x);
            }
        } else {
            if (node.right == null) {
                node.change(1, new Node(x, 1));
                Splay(node.get(1));
            } else {
                insert(node.right, x);
            }
        }
    }

    private static void find(Node node, long x) {
        if (node.getLS() + 1 == x) {
            Splay(node);
        } else if (x < node.getLS() + 1) {
            find(node.left, x);
        } else {
            find(node.right, x - node.getLS() - 1);
        }
    }

    private static void toArray(Node node) {
        if (node == null) {
        } else if (node.left != null) {
            toArray(node.left);
            sb.append(node.val).append(' ');
            toArray(node.right);
        } else {
            sb.append(node.val).append(' ');
            toArray(node.right);
        }
    }

    private static Node merge(Node node, Node node1) {
        if (node1 != null) {
            pNode = node1;
            find(node1, 1);
            pNode.change(0, node);
            return pNode;
        } else {
            return node;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long m = in.nextLong();
        for (int i = 2; i < n + 1; i++) {
            insert(pNode, i);
        }
        for (long i = 0; i < m; i++) {
            long l = in.nextLong();
            long r = in.nextLong();

            find(pNode, r);
            Node rnode = pNode.right;
            if (rnode != null) rnode.parent = null;
            pNode.right = null;
            pNode.updateS();

            find(pNode, l);
            Node lnode = pNode.left;
            if (lnode != null) lnode.parent = null;
            pNode.left = null;
            pNode.updateS();

            find(pNode, pNode.s);
            Node midNode = pNode;
            midNode.change(1, merge(lnode, rnode));
            pNode = midNode;
        }
        toArray(pNode);
        System.out.println(sb.toString());
    }

    private static void zig(Node node) {
        Node child = node.get(node.pos ^ 1);

        pNode.change(node.pos, child);
        node.change(node.pos ^ 1, pNode);

        pNode = node;
        node.parent = null;
    }

    private static void zig_zag(Node node) {
        Node parent = node.parent;
        Node grandpa = parent.parent;

        parent.change(node.pos, node.get(node.pos ^ 1));
        grandpa.change(parent.pos, node.get(node.pos));

        Node.swap(grandpa, node);

        node.change(parent.pos, parent);
        node.change(parent.pos ^ 1, grandpa);

        Node.swap(node, node); //сука повтор
    }

    private static void zig_zig(Node node) {
        Node parent = node.parent;
        Node grandpa = parent.parent;

        Node.swap(grandpa, node);

        grandpa.change(parent.pos, parent.get(parent.pos ^ 1));
        parent.change(parent.pos ^ 1, grandpa);
        parent.change(parent.pos, node.get(parent.pos ^ 1));
        node.change(parent.pos ^ 1, parent);

        Node.swap(node, node); //сука повтор
    }

    private static void Splay(Node node) {
        while (node != pNode) {
            if (node.parent == pNode) {
                zig(node);
            } else if (node.pos != node.parent.pos) {
                zig_zag(node);
            } else {
                zig_zig(node);
            }
        }
    }

    public static class Node {
        Node parent;
        Node left;
        Node right;
        long val;
        long s;
        int pos;

        private Node(long val, int pos) {
            this.val = val;
            this.pos = pos;
            this.s = 1;
        }

        private void updateS() {
            this.s = getLS() + getRS() + 1;
        }

        private long getLS() {
            return this.left != null ? this.left.s : 0;
        }

        private long getRS() {
            return this.right != null ? this.right.s : 0;
        }

        private Node get(int pos) {
            if (pos == 0) {
                return this.left;
            } else {
                return this.right;
            }
        }

        private static void swap(Node node1, Node node2) {
            if (node1.parent != null) {
                node1.parent.change(node1.pos, node2);
            } else {
                node2.parent = null;
                pNode = node2;
            }
        }

        private void change(int pos, Node node) {
            if (0 == pos) {
                this.left = node;
            } else {
                this.right = node;
            }
            if (node != null) {
                node.parent = this;
                node.pos = pos;
            }
            updateS();
        }
    }

}
