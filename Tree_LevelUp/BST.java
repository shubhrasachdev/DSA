import java.util.ArrayList;

public class BST {
    
    // June 13, 2021
    public class TreeNode {
        int data;
        TreeNode left = null;
        TreeNode right = null;
        
        TreeNode(int data) {
            this.data = data;
        }
    }

    public static int size(TreeNode root){
        return root == null ? 0 : (1 + size(root.left) + size(root.right));
    }

    public static int height(TreeNode root){
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;

    }

    public static int maximum(TreeNode root){
        TreeNode curr = root;
        while(curr.right != null) curr = curr.right;
        return curr.data; 
    }

    public static int minimum(TreeNode root){
        TreeNode curr = root;
        while(curr.left != null) curr = curr.left;
        return curr.data; 
    }

    public static boolean find(TreeNode root, int data) {
        TreeNode curr = root;
        while(curr != null){
            if(curr.data == data) return true;
            if(curr.data < data) curr = curr.right;
            else curr = curr.left;
        }   
        return false;
    }

    public static ArrayList<TreeNode> rootToNodePath(TreeNode root, int data){
        ArrayList<TreeNode> ans = new ArrayList<>();
        TreeNode curr = root;
        boolean flag = false;
        while(curr != null) {
            ans.add(curr);
            if(curr.data == data) {
                flag = true;
                break;
            } else if(curr.data < data) curr = curr.right;
            else curr = curr.left;
        } 
        if(!flag) ans.clear();
        return ans;
    }

    // Leetcode 235
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q){
        TreeNode curr = root;
        while(curr != null){
            if(curr.data < p && curr.data < q) curr = curr.right;
            else if(curr.data > p && curr.data > q) curr = curr.left;
            else return curr;
        }
        return null;
    }

    // June 24, 2021
    // Leetcode 701 - https://leetcode.com/problems/insert-into-a-binary-search-tree/
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if(root.data < val) root.right = insertIntoBST(root.right, val);
        else root.left = insertIntoBST(root.left, val);
        return root;
    }

    public static int getMaximum(TreeNode root) {
        while(root.right != null) root = root.right;
        return root.data;
    }

    // Leetcode 450 - https://leetcode.com/problems/delete-node-in-a-bst/
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.data == key) {
            if(root.left == null || root.right == null) return root.left != null ? root.left : root.right; 
            int maxData = getMaximum(root.left);
            root.data = maxData;
            root.left = deleteNode(root.left, maxData);
        }
        else if(root.data < key) root.right = deleteNode(root.right, key);
        else root.left = deleteNode(root.left, key);
        return root;
    }
    
}
