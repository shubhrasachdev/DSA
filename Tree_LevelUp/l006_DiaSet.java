public class l006_DiaSet {
    // June 27, 2021

    class Node{
        int data = 0;
        Node left = null, right = null;
    } 

    class TreeNode{
        int val = 0;
        TreeNode left = null, right = null;
        TreeNode(int val){ this.val = val; }
    } 
    
    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)); 
    }

    // Diameter of a Binary Tree
    // Leetcode 543 - https://leetcode.com/problems/diameter-of-binary-tree/
    // Brute force method
    public static int diameter_01(TreeNode root) {
        int ld = diameter_01(root.left);
        int rd = diameter_01(root.right);
        int lh = height(root.left);
        int rh = height(root.right);
        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    // Method 2 - (dia, height)
    public static int[] diameter_02(TreeNode root) {
        if(root == null) return new int[]{0, -1};
        int[] lp = diameter_02(root.left);
        int[] rp = diameter_02(root.right);
        int[] myAns = new int[2];
        myAns[0] = Math.max(Math.max(lp[0], rp[0]), lp[1] + rp[1] + 2);
        myAns[1] = Math.max(lp[1], rp[1]) + 1;
        return myAns;
    }

    // Method 3 - using static
    public static int diameter_03(TreeNode root, int[] dia) {
        if(root == null) return -1;
        int lh = diameter_03(root.left, dia);
        int rh = diameter_03(root.right, dia);
        dia[0] = Math.max(dia[0], lh + rh + 2);
        return Math.max(lh, rh) + 1;
    }
    
}
