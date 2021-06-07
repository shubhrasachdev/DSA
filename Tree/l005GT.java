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

}