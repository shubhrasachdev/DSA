// march 9, 2021

import java.util.ArrayList;
public class l004BST {
    public static class Node{
        int data = 0;
        Node left = null, right = null;
        Node(int data){this.data = data;}
    }

    public static Node constructTree(int[] arr, int si, int ei){
        if(si > ei) return null;
        int mid = (si + ei)/2;
        Node node = new Node(arr[mid]);
        node.left = constructTree(arr, si, mid - 1);
        node.right = constructTree(arr, mid + 1, ei);
        return node;
    }

    public static Node constructTree(int[] arr){
        return constructTree(arr, 0, arr.length - 1);
    }

    public static int size(Node node){
        if(node == null) return 0;
        return size(node.left) + size(node.right) + 1;
    }

    public static int sum(Node node) {
        if(node == null) return 0;
        return node.data + sum(node.left) + sum(node.right);
    }

    public static int height(Node node){
        if(node == null) return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static int maximum(Node node){
        Node curr = node;
        while(curr.right != null){
            curr = curr.right;
        }
        return curr.data;
    }

    public static int maximumRec(Node node){
        if(node.right == null) return node.data;
        return maximumRec(node.right);
    }

    public static int minimum(Node node){
        Node curr = node;
        while(curr.left != null){
            curr = curr.left;
        }
        return curr.data;
    }

    public static int minimumRec(Node node){
        if(node.left == null) return node.data;
        return minimumRec(node.left);
    }

    public static boolean find(Node root, int data){
        Node curr = root;
        while(curr != null){
            if(curr.data == data) return true;
            else if(curr.data < data) curr = curr.right;
            else curr = curr.left;
        }
        return false;
    }

    public static Node addData(Node node, int data){
        if(node == null) return new Node(data);
        if(node.data < data) node.right = addData(node.right, data);
        else node.left = addData(node.left, data);
        return node;
    }

    public static Node addDataItr(Node node, int data){
        if(node == null) return new Node(data);
        Node curr = node;
        Node dNode = new Node(data);
        while(true){
            if(curr.data == data) break;
            else if(curr.data < data){
                if(curr.right != null) curr = curr.right;
                else{
                    curr.right = dNode;
                    break;
                }
            }
            else{
                if(curr.left != null) curr = curr.left;
                else{ 
                    curr.left = dNode;
                    break;
                }
            }
        }
        return node;
    }

    public static boolean rootToNodePath(Node root, int data, ArrayList<Node> ans){
        if(root == null) return false;
        boolean res = (root.data == data) || rootToNodePath(root.left, data, ans) || rootToNodePath(root.right, data, ans);
        if(res) ans.add(root);
        return res;
    }

    public static Node LCA(Node node, int p, int q){
        ArrayList<Node> l1 = new ArrayList<>();
        ArrayList<Node> l2 = new ArrayList<>();
        rootToNodePath(node, p, l1);
        rootToNodePath(node, q, l2);
        int i = l1.size() - 1, j = l2.size() - 1;
        Node lca = null;
        while(i >= 0 && j >= 0){
            if(l1.get(i) != l2.get(j)) break;
            lca = l1.get(i);
            i--;
            j--;
        }
        return lca;
    }

    // march 10, 2021
    public static Node removeNode(Node node, int data) {
        if (node == null) return null;
        if (node.data < data) node.right = removeNode(node.right, data);
        else if (node.data > data) node.left = removeNode(node.left, data);
        else {
            if (node.left == null || node.right == null)
                return node.left != null ? node.left : node.right;

            int minData = minimum(node.right);
            node.data = minData;
            node.right = removeNode(node.right, minData);
        }
        return node;
    }


    public static void inorder(Node node, ArrayList<Integer> list){
        if(node == null) return;
        inorder(node.left, list);
        list.add(node.data);
        inorder(node.left, list);
    }

    public static void targetSum(Node node, int target){
        ArrayList<Integer> list = new ArrayList<>();
        inorder(node, list);
        int i = 0, j = list.size() - 1;
        while(i < j){
            int sum = list.get(i) + list.get(j);
            if(sum == target){
                System.out.println(list.get(i) + " " + list.get(j));
                i++;
                j--;
            }
            else if(sum < target) i++;
            else j--;
        }
    }

    

    
}
