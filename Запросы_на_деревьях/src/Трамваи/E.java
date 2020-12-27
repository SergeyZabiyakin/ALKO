package Трамваи;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E {
    private static Node Pnode = new Node(1);
    private static int goodnode = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Node[] list = new Node[n + 1];
        list[1] = Pnode;

        for (int i = 0; i < n - 1; i++) {
            int l = in.nextInt();
            int r = in.nextInt();
            if (list[l] == null) list[l] = new Node(l);
            if (list[r] == null) list[r] = new Node(r);
            list[l].nodes.add(list[r]);
            list[r].nodes.add(list[l]);
        }

        Pnode.hang(Pnode, 1);

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            Node lnode = list[in.nextInt()];
            Node rnode = list[in.nextInt()];
            Node l = lnode;
            Node r = rnode;
            if (lnode.h < rnode.h) {
                Node midnode = lnode;
                lnode = rnode;
                rnode = midnode;
            }
            int D = lnode.h - rnode.h;
            for (int j = lnode.log.size() - 1; j >= 0; j--) {
                if (D >= (1 << j)) {
                    D -= (1 << j);
                    lnode = lnode.log.get(j);
                }
            }
            if (lnode != rnode) {
                for (int j = lnode.log.size() - 1; j >= 0; j--) {
                    if (lnode.log.size() > j) {
                        Node Lnode = lnode.log.get(j);
                        Node Rnode = rnode.log.get(j);
                        if (Lnode != Rnode) {
                            lnode = Lnode;
                            rnode = Rnode;
                        }
                    }
                }
                lnode = lnode.log.get(0);
            }
            //System.out.println(lnode.num);
            l.update(lnode.h);
            r.update(lnode.h);
        }
        Go(Pnode);
        System.out.println(n - goodnode - 1);
    }


    public static class Node {
        Node parent;
        List<Node> nodes = new ArrayList<>();
        List<Node> log = new ArrayList<>();
        int h;
        int upnode;
        int num;
        Boolean good = false;

        private void update(int upnode) {
            if (this.upnode > upnode) {
                this.upnode = upnode;
            }
        }

        private void hang(Node node, int h) {
            this.parent = node;
            this.h = h;
            this.upnode = h;
            this.log.add(node);
            while (node != Pnode) {
                if (node.log.size() > this.log.size() - 1) {
                    node = node.log.get(this.log.size() - 1);
                    this.log.add(node);
                } else {
                    this.log.add(Pnode);
                    break;
                }
            }
            for (Node i : this.nodes) {
                if (i != this.parent) {
                    i.hang(this, h + 1);
                }
            }
        }

        public Node(int num) {
            this.num = num;
        }
    }

    static int Go(Node node) {
        int up = node.upnode;
        for (Node i : node.nodes) {
            if (i != node.parent) {
                up = Math.min(up, Go(i));
            }
        }
        if (up < node.h && !node.good) {
            goodnode++;
            node.good = true;
        }
        return up;
    }
}