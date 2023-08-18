// march 10, 2021

import java.util.ArrayList;

public class l005GT {
    public static class Node {
        int data = 0;
        ArrayList<Node> children;

        Node(int data) {
            this.data = data;
        }
    }

    public static int size(Node node) {
        if(node == null) return 0;
        int sz = 1;
        for (Node child : node.children) {
            sz += size(child);
        }
        return sz;
    }

    public static int height(Node node) {
        int h = 0;
        for (Node child : node.children) {
            h = Math.max(h, height(child));
        }

        return h + 1;
    }

    public static int maximum(Node node) {
        int max = node.data;
        for(Node child: node.children) max = Math.max(max, maximum(child));
        return max;
    }

    public static int minimum(Node node) {
        int min = node.data;
        for(Node child: node.children) min = Math.min(min, minimum(child));
        return min;
    }

    public static boolean find(Node node, int data) {
        if(node == null) return false;
        if(node.data == data) return true;
        for(Node child: node.children){
            if(find(child, data)) return true;
        }
        return false;
    }

    public static void display(Node node) {
        System.out.print(node.data);
        for(Node child: node.children) System.out.print(" -> " + child.data);
        System.out.println();
        for(Node child: node.children) display(child);
    }

    public static boolean nodeToRootPath(Node node, int data, ArrayList<Node> ans){
        if(node == null) return false;
        boolean res = node.data == data;
        for(Node child: node.children) res = res || nodeToRootPath(child, data, ans);
        if(res) ans.add(node);
        return res;
    }

    public static Node LCANode(Node node, int d1, int d2){
        ArrayList<Node> list1 = new ArrayList<>();
        ArrayList<Node> list2 = new ArrayList<>();
        nodeToRootPath(node, d1, list1);
        nodeToRootPath(node, d2, list2);

        int i = list1.size() - 1;
        int j = list2.size() - 1;
        Node ans = null;
        while(i >= 0 && j >= 0){
            if(list1.get(i) != list2.get(i)) break;
            ans = list1.get(i);
            i--;
            j--;
        }
        return ans;
    }

    public static int NodeToNodeDistance(Node node, int d1, int d2) {
        ArrayList<Node> list1 = new ArrayList<>();
        ArrayList<Node> list2 = new ArrayList<>();

        nodeToRootPath(node, d1, list1);
        nodeToRootPath(node, d2, list2);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        int LCADistance = 0;

        while (i >= 0 && j >= 0) {
            if (list1.get(i) != list2.get(j))
                break;

            LCADistance++;
            i--;
            j--;
        }

        int dis = (list1.size() + list2.size() - 2 * (LCADistance) + 1); // distance in terms of No of Nodes

        // int dis = (list1.size() + list2.size() - 2 * (LCADistance) + 1 - 1);
        // distance in terms of No of Edges

        return dis;
    }

    // Linearize a Generic Tree (preorder) - pepcoding question
    public static Node getTail(Node node) {
        Node curr = node;
        while(curr.children.size() != 0) curr = curr.children.get(0);
        return curr;
    }

    public static void linearize(Node node) {
        for(Node child: node.children) linearize(child);
        for(int i = node.children.size() - 2; i >= 0; i--) {
            Node tail = getTail(node.children.get(i));
            tail.children.add(node.children.get(i + 1));
            node.children.remove(i + 1);
        }
    }

    public static Node linearizeBetter(Node node) {
        if(node.children.size() == 0) return node;
        int n = node.children.size();
        Node gtail = linearizeBetter(node.children.get(n - 1));
        for(int i = n - 2; i >= 0; i--) {
            Node tail = linearizeBetter(node.children.get(i));
            tail.children.add(node.children.get(i + 1));
            node.children.remove(i + 1);
        }
        return gtail;
    }

    // Floor (just smaller than given data) and Ceil (just greater than given data) in GT
    static int floor = -(int)1e9, ceil = (int)1e9;
    public static void floorAndCeil(Node node, int data) {
        if(node.data < data) floor = Math.max(floor, node.data);
        if(node.data > data) ceil = Math.min(ceil, node.data);
        for(Node child: node.children) floorAndCeil(node, data);
    }

    // Kth Largest Element in GT
    public static int maxEleGtBound(Node node, int bound) { // returns max element in tree greater than given bound
        int ans = -(int)1e9;
        if(node.data < bound) ans = Math.max(ans, node.data);
        for(Node child: node.children) ans = Math.max(ans, maxEleGtBound(child, bound));
        return ans;
    }

    public static int kthLargest(Node node, int k) {
        int bound = (int)1e9;
        while(k-- > 0) {
            bound = maxEleGtBound(node, bound);
        }
        return bound;
    }

    public static boolean areSimilar(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size())
            return false;

        int n = n1.children.size();
        for (int i = 0; i < n; i++) {
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(i);

            if (!areSimilar(c1, c2))
                return false;
        }

        return true;
    }

    public static boolean areMirror(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size())
            return false;

        int n = n1.children.size();
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(j);

            if (!areMirror(c1, c2))
                return false;
        }

        return true;
    }

    public static boolean areSimilar_(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size())
            return false;

        int n = n1.children.size();
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(j);

            if (!areSimilar_(c1, c2))
                return false;
        }

        return true;
    }

    public static boolean IsSymmetric(Node node) {
        return areSimilar(node, node);
    }


}