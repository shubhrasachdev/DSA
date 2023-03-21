public class Self {
    class TreeNode {
        int val;
        TreeNode left, right;
    }

    // Leetcode 1373: https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
    int maxSum = 0;
    class isBSTPair {
        boolean isBST = true;
        int maxEle = Integer.MIN_VALUE;
        int minEle = Integer.MAX_VALUE;
        int sum = 0;
    }
    
    private isBSTPair maxSumBST_(TreeNode root) {
        if(root == null) return new isBSTPair();
        isBSTPair left = maxSumBST_(root.left);
        isBSTPair right = maxSumBST_(root.right);
        isBSTPair res = new isBSTPair();
        res.isBST = false;
        if(left.isBST && right.isBST && left.maxEle < root.val && right.minEle > root.val) {
            res.isBST = true;
            res.maxEle = Math.max(right.maxEle, root.val);
            res.minEle = Math.min(left.minEle, root.val);
            res.sum = left.sum + right.sum + root.val;
            maxSum = Math.max(maxSum, res.sum);
        }
        // System.out.println("NODE: " + root.val);
        // System.out.println("isBST: " + res.isBST + "; sum: " + res.sum);
        // System.out.println("maxEle: " + res.maxEle + "; minEle: " + res.minEle);
        // System.out.println("----------------------------------------------------------");
        return res;
    }
    
    public int maxSumBST(TreeNode root) {
        maxSumBST_(root);
        return maxSum;
        
    }
    
}
