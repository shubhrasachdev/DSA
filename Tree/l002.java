// march 3 2021

import java.util.*;
public class l002 {
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;
        Node(int data){this.data = data;}
    }

    // stores path from node to root in arraylist ans
    public static boolean rootToNodePath(Node root, int data, ArrayList<Node> ans){
        if(root == null) return false;
        boolean res = (root.data == data) || rootToNodePath(root.left, data, ans) || rootToNodePath(root.right, data, ans);
        if(res) ans.add(root);
        return res;
    }

    // stores nodes at k depth from a given node in an arraylist ans
    // without blocking
    public static void printAtKDepth(Node root, int k, ArrayList<Integer> ans){
        if(root == null || k < 0) return;
        if(k == 0){
            ans.add(root.data);
            return;
        }
        printAtKDepth(root.left, k - 1, ans);
        printAtKDepth(root.right, k - 1, ans);
    }

    // stores nodes at k depth from a given node in an arraylist nodeList
    // with blocking
    public static void printAtKDepth(ArrayList<Integer> nodeList, Node root, Node blocked, int k){
        if(k < 0 || root == null || root == blocked) return;
        if(k == 0){
            nodeList.add(root.data);
            return;
        }
        printAtKDepth(nodeList, root.left, blocked, k - 1);
        printAtKDepth(nodeList, root.right, blocked, k - 1);
    }
    
    // stores all nodes in all directions k distance away from given node
    public ArrayList<Integer> printKNodesFar(Node root, Node target, int k) {
        ArrayList<Node> path = new ArrayList<Node>();
        rootToNodePath(root, target.data, path);
        ArrayList<Integer> res = new ArrayList<Integer>();
        Node blocked = null;
        for(int i = 0; i < path.size(); i++){
            printAtKDepth(res, path.get(i), blocked, k - i);
            blocked = path.get(i);
        }
        return res;
    }

    // print nodes that are single child of their parent
    public static void printSingleChildNodes(Node node, Node parent){
        if(node == null) return;
        if(parent != null && (parent.left == null || parent.right == null)){
            System.out.println(node.data);
        }
        printSingleChildNodes(node.left, node);
        printSingleChildNodes(node.right, node);      
    }

    // remove leaf nodes from a tree - return type node
    public static Node removeLeaves(Node node){
        if(node == null) return null;
        if(node.left == null && node.right == null) return null;
        node.left = removeLeaves(node.left);
        node.right = removeLeaves(node.right);
        return node;
    }

    // march 8, 2021
    // remove leaf nodes from a tree - return type void
    public static void removeLeavesHelper(Node node, Node par){
        if(node == null) return;
        if(node.left == null && node.right == null){
            if(par.left == node) par.left = null;
            else par.right = null;
            return;
        }
        removeLeavesHelper(node.left, node);
        removeLeavesHelper(node.right, node);
    }

    public static Node removeLeaves2(Node node){
        if(node.left == null && node.right == null) return null;
        removeLeavesHelper(node, null);
        return node;
    }
}
