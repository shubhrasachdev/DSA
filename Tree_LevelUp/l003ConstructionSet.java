// June 20, 2021

import java.util.*;
public class l003ConstructionSet {
    public static class TreeNode{
        int data = 0;
        TreeNode left = null, right = null;
        TreeNode(int data){ this.data = data; }
    }

    
    // Construction Set ======================================================================

    // construct BST from inorder traversal
    public static TreeNode constructFromInOrder(int[] arr, int low, int high){
        if(low > high) return null;
        int mid = (low + high)/2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = constructFromInOrder(arr, low, mid - 1);
        root.right = constructFromInOrder(arr, mid + 1, high);
        return root;
    }
    public static TreeNode constructFromInOrder(int[] arr) {
        return constructFromInOrder(arr, 0, arr.length - 1);
    }

    // Construct BST from preorder traversal
    static int idx;
    public static TreeNode constructFromPreOrder(int[] arr, int lo, int hi){
        if(idx >= arr.length || lo > arr[idx] || hi < arr[idx]) return null;
        TreeNode root = new TreeNode(arr[idx++]);
        root.left = constructFromPreOrder(arr, lo, root.data);
        root.right = constructFromPreOrder(arr, root.data, hi);
        return root;
    }

    public static TreeNode constructFromPreOrder(int[] arr){
        idx = 0;
        return constructFromPreOrder(arr, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    // Construct BST from postorder traversal
    public static TreeNode constructFromPostOrder(int[] arr, int lo, int hi){
        if(idx < 0 || lo > arr[idx] || hi < arr[idx]) return null;
        TreeNode root = new TreeNode(arr[idx--]);
        root.right = constructFromPostOrder(arr, root.data, hi);
        root.left = constructFromPostOrder(arr, lo, root.data);
        return root;
    }
    public static TreeNode constructFromPostOrder(int[] arr){
        idx = arr.length - 1;
        return constructFromPostOrder(arr, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // construct BST from Level order traversal
    public static class bstLPair{
        TreeNode par = null;
        int lo = 0, hi = 0;
        bstLPair(TreeNode par, int lr, int rr){
            this.par = par;
            this.lo = lr;
            this.hi = rr;
        }

    }

    public static TreeNode constructBSTFromLevelOrder(int[] arr){
        if(arr.length == 0) return null;
        LinkedList<bstLPair> q = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        int idx = 1;
        q.addLast(new bstLPair(root, Integer.MIN_VALUE, root.data));
        q.addLast(new bstLPair(root, root.data, Integer.MAX_VALUE));
        while(q.size() != 0){
            bstLPair rp = q.removeFirst();
            int lo = rp.lo, hi = rp.hi;
            if(arr[idx] < lo || arr[idx] > hi) continue;
            TreeNode node = new TreeNode(arr[idx++]);
            if(node.data < rp.par.data) rp.par.left = node;
            else rp.par.right = node;

            q.addLast(new bstLPair(node, rp.lo, node.data));
            q.addLast(new bstLPair(node, node.data, rp.hi));
        }
        return root;
    }

    // construct binary tree from preorder and inorder
    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder, int isi, int iei, int psi, int pei) {
        if(isi > iei || psi > pei) return null;
        TreeNode root = new TreeNode(preorder[psi]);
        int idx = isi;
        while(inorder[idx] != preorder[psi]) idx++;
        int tel = idx - isi;
        root.left = buildTreePreIn(preorder, inorder, isi, idx - 1, psi + 1, psi + tel);
        root.right = buildTreePreIn(preorder, inorder, idx + 1, iei, psi + tel + 1, pei);
        return root;
    }

    // construct binary tree from postorder and inorder
    public static TreeNode buildTreePostIn(int[] postorder, int[] inorder, int isi, int iei, int psi, int pei) {
        if(isi > iei || psi > pei) return null;
        TreeNode root = new TreeNode(postorder[pei]);
        int idx = isi;
        while(inorder[idx] != postorder[pei]) idx++;
        int tel = idx - isi;
        root.left = buildTreePostIn(postorder, inorder, isi, idx - 1, psi, psi + tel - 1);
        root.right = buildTreePostIn(postorder, inorder, idx + 1, iei, psi + tel, pei - 1);
        return root;
    }

    // ==========================================================================================================================

    // To find predecessor, successor, floor and ceil(same in any traversal) of a given node during 
    // inorder traversal
    public static class allSolPair{
        TreeNode pred = null;
        TreeNode succ = null;
        TreeNode prev = null;
        int ceil = Integer.MAX_VALUE;
        int floor = Integer.MIN_VALUE;
    }

    public static void allSolution(TreeNode node, int data, allSolPair pair){
        if(node == null) return;
        allSolution(node.left, data, pair);
        if(node.data < data) pair.floor = Math.max(pair.floor, node.data);
        else if(node.data > data) pair.ceil = Math.min(pair.ceil, node.data);
        if(node.data == data && pair.pred == null) pair.pred = pair.prev;
        if(pair.prev != null && pair.prev.data == data && pair.succ == null) pair.succ = node;
        pair.prev = node;
        
        allSolution(node.right, data, pair);
    }

    // To find predecessor and successor in a BST
    public static TreeNode getLeftMost(TreeNode node) {
        if(node == null) return node;
        while(node.left != null) node = node.left;
        return node;
    }

    public static TreeNode getRightMost(TreeNode node) {
        if(node == null) return node;
        while(node.right != null) node = node.right;
        return node;
    }

    public static void successorPredecessorBST(TreeNode node, int data) {
        TreeNode curr = node, pred = null, succ = null;
        while(curr != null) {
            if(curr.data == data){
                TreeNode leftMost = getLeftMost(node.right);
                succ = leftMost != null ? leftMost : succ;
                TreeNode rightMost = getRightMost(node.left);
                pred = rightMost != null ? rightMost : pred;
                break;
            } else if (data < curr.data) {
                succ = curr;
                curr = curr.left;
            } else {
                pred = curr;
                curr = curr.right;
            }
        }

    }

    // HW
    // T : n, S : log n
    public static int kthLargestEle(TreeNode root, int k) {
        return 0;
    }


    // July 3, 2021 
    // construct binary tree from preorder and postorder
    // Leetcode 889: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
    public static TreeNode buildTreePrePost(int[] pre, int[] post, int psi, int pei, int posi, int poei){
        if(psi > pei) return null;
        TreeNode root = new TreeNode(pre[psi]);
        if(psi == pei) return root;
        int idx = posi;
        while(post[idx] != pre[psi + 1]) idx++;
        int tnel = idx - posi + 1;
        root.left = buildTreePrePost(pre, post, psi + 1, psi + tnel, posi, idx);
        root.right = buildTreePrePost(pre, post, psi + tnel + 1, pei, idx + 1, poei - 1);
        return root;
    }

    public static TreeNode buildTreePrePost(int[] pre, int[] post){
        int n = pre.length;
        return buildTreePrePost(pre, post, 0, n - 1, 0, n - 1);
    }

    // construct binary tree from inorder and level-order 
    // https://practice.geeksforgeeks.org/problems/construct-tree-from-inorder-and-levelorder/1
    public static TreeNode buildTreeInLevel(int[] level, int[] in, int isi, int iei) {
        if(isi > iei) return null;
        TreeNode root = new TreeNode(level[0]);
        if(isi == iei) return root;
        int idx = isi;
        HashSet<Integer> s = new HashSet<>();
        while(level[0] != in[idx]) s.add(in[idx++]);
        int n = s.size(), lidx = 0, ridx = 0;
        int[] leftLevel = new int[n];
        int[] rightLevel = new int[level.length - n];
        for(int i = 1; i < level.length; i++){
            if(s.contains(level[i])) leftLevel[lidx++] = level[i];
            else rightLevel[ridx++] = level[i];
        }
        root.left = buildTreeInLevel(leftLevel, in, isi, idx - 1);
        root.right = buildTreeInLevel(rightLevel, in, idx + 1, iei);
        return root;
    }


    
}
