import java.util.*;
public class l002 {
    public static class TreeNode {
        int data = 0;
        TreeNode left = null, right = null;
    }
    public static class vPair {
        TreeNode node = null;
        int vl = 0;
        vPair(TreeNode node, int vl){
            this.node = node;
            this.vl = vl;
        }
    }

    public static void widthOfShadow(TreeNode root, int[] minMax, int vl){ // or number of vertical levels
        if(root == null) return;
        minMax[0] = Math.min(minMax[0], vl);
        minMax[1] = Math.max(minMax[1], vl);
        if(root.left != null) widthOfShadow(root.left, minMax, vl - 1);
        if(root.right != null) widthOfShadow(root.right, minMax, vl + 1);

    }

    // June 19, 2021

    // sum of each vertical level in a Binary tree
    public static ArrayList<Integer> verticalOrderSum(TreeNode root) {
        LinkedList<vPair> q = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, minMax, 0);
        int len = minMax[1] - minMax[0] + 1;
        q.addLast(new vPair(root, Math.abs(minMax[0])));
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < len; i++) ans.add(null);
        while(q.size() != 0) {
            int size = q.size();
            while(size-- > 0) {
                vPair rp = q.removeFirst();
                TreeNode rnode = rp.node;
                int vl = rp.vl;
                ans.set(vl, ans.get(vl) + rnode.data);
                if(rnode.left != null) q.addLast(new vPair(rnode.left, vl - 1));
                if(rnode.right != null) q.addLast(new vPair(rnode.right, vl + 1));
            }
        }
        return ans;
    }

    // sum of each diagonal level in a Binary Tree
    public static ArrayList<Integer> diagonalOrderSum(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        que.addLast(root);
        while (que.size() != 0) {
            int size = que.size();
            int sum = 0;
            while (size-- > 0) { // diagonal
                TreeNode node = que.removeFirst();
                while (node != null) { // clusters of diagonal
                    sum += node.data;
                    if (node.left != null)
                        que.addLast(node.left);
                    node = node.right;
                }
            }
            ans.add(sum);
        }
        return ans;
    }

    // Sums using shadow method ============================================================================
    public static class DLL {
        int data = 0;
        DLL prev = null;
        DLL next = null;

        DLL(int data) {
            this.data = data;
        }
    }
    
    // Vertical ------------------------------------------------------------------------
    // https://practice.geeksforgeeks.org/problems/vertical-sum
    public static void verticalOrderSumShadow(TreeNode root, DLL node) {
        if(root == null) return; 
        node.data += root.data;
        if(root.left != null) {
            if(node.prev == null) {
                node.prev = new DLL(0);
                node.prev.next = node;
            }
            verticalOrderSumShadow(root.left, node.prev);
        } 
        if(root.right != null) {
            if(node.next == null) {
                node.next = new DLL(0);
                node.next.prev = node;
            }
            verticalOrderSumShadow(root.right, node.next);
        }
    }

    public static ArrayList<Integer> verticalOrderSumShadow(TreeNode root){
        ArrayList<Integer> ans = new ArrayList<>();
        DLL head = new DLL(0);
        verticalOrderSumShadow(root, head);     
        DLL curr = head;
        while(curr.prev != null) curr = curr.prev; // get to head again
        while(curr != null) {
            ans.add(curr.data);
            curr = curr.next;
        }  
        return ans;
    }

    // Diagonal -------------------------------------------------------------------
    // https://practice.geeksforgeeks.org/problems/diagonal-sum-in-binary-tree
    public static void diagonalOrderSumShadow(TreeNode root, DLL node) {
        if(root == null) return;
        node.data += root.data;
        if(root.left != null) {
            if(node.prev == null){
                node.prev = new DLL(0);
                node.prev.next = node;
            }
            diagonalOrderSumShadow(root.left, node.prev);
        }
        if(root.right != null) {
            diagonalOrderSumShadow(root.right, node);
        }
    }

    public static ArrayList<Integer> diagonalOrderSumShadow(TreeNode root){
        ArrayList<Integer> ans = new ArrayList<>();
        DLL head = new DLL(0);
        DLL curr = head;
        diagonalOrderSumShadow(root, curr);     
        curr = head;
        while(curr != null) {
            ans.add(curr.data);
            curr = curr.prev;
        }  
        return ans;
    }

    // Leetcode 987: vertical traversal, but for each level, smaller element comes first
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        PriorityQueue<vPair> child = new PriorityQueue<>((a, b) -> {
            if(a.vl != b.vl) return a.vl - b.vl;
            return a.node.data - b.node.data;
        });
        PriorityQueue<vPair> parent = new PriorityQueue<>((a, b) -> {
            if(a.vl != b.vl) return a.vl - b.vl;
            return a.node.data - b.node.data;
        });
        List<List<Integer>> ans = new ArrayList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, minMax, 0);
        int len = minMax[1] - minMax[0] + 1;
        parent.add(new vPair(root, Math.abs(minMax[0])));
        for(int i = 0; i < len; i++) ans.add(new ArrayList<>());
        while(parent.size() != 0) {
            int size = parent.size();
            while(size-- > 0) {
                vPair rp = parent.remove();
                TreeNode rnode = rp.node;
                int vl = rp.vl;
                ans.get(vl).add(rnode.data);
                if(rnode.left != null) child.add(new vPair(rnode.left, vl - 1));
                if(rnode.right != null) child.add(new vPair(rnode.right, vl + 1));
            }
            PriorityQueue<vPair> temp = parent;
            parent = child;
            child = temp;
        }
        return ans;
    }
    
   
    
    
}
