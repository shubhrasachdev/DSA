import java.util.*;
public class questions {
    class Node{
        int data = 0;
        Node left = null, right = null;
    } 

    class TreeNode{
        int val = 0;
        TreeNode left = null, right = null;
    } 
    
    // Geeks for geeks - min time for tree to burn
    // https://practice.geeksforgeeks.org/problems/burning-tree/
    public static int kDown(Node root, int time, Node blockNode){
        if(root == null || root == blockNode) return -1;
        int leftAns = kDown(root.left, time + 1, blockNode);
        int rightAns = kDown(root.right, time + 1, blockNode);
        return Math.max(time, Math.max(leftAns, rightAns));
    }
    
    public static int burningTree(Node root, int target, int[] ans){
        if(root == null) return -1;
        if(root.data == target){
            ans[0] = Math.max(ans[0], kDown(root, 0, null));
            return 1;
        }
        int ld = burningTree(root.left, target, ans);
        if(ld != -1) {
            ans[0] = Math.max(ans[0], kDown(root, ld, root.left));
            return ld + 1;
        }
        
        int rd = burningTree(root.right, target, ans);
        if(rd != -1) {
            ans[0] = Math.max(ans[0], kDown(root, rd, root.right));
            return rd + 1;
        }
        return -1;
    }

    // June 27, 2021

    // Leetcode: 112, has Path sum?  
    // https://leetcode.com/problems/path-sum/
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && targetSum - root.val == 0) return true;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    
    // Leetcode: 113 
    // https://leetcode.com/problems/path-sum-ii/
    public static void pathSum(TreeNode root, int targetSum, List<Integer> smallAns, List<List<Integer>> ans) {
        if(root == null) return;
        if(root.left == null && root.right == null && targetSum - root.val == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return;
        }
        smallAns.add(root.val);
        pathSum(root.left, targetSum - root.val, smallAns, ans);
        pathSum(root.right, targetSum - root.val, smallAns, ans);
        smallAns.remove(smallAns.size() - 1);
    }
    
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        pathSum(root, targetSum, smallAns, ans);
        return ans;
    }

    // Maximum path sum leaf to leaf
    // https://practice.geeksforgeeks.org/problems/maximum-path-sum/1
    
    // Leaf to leaf pair
    public static class leafToLeafPair{
        int LTLMaxSum = -(int)1e9; // leaf to leaf max sum
        int NTLMaxSum = -(int)1e9; // node to leaf max sum
    }

    public static leafToLeafPair maxLeafSumHelper(TreeNode root){
        if(root == null) return new leafToLeafPair();
        if(root.left == null && root.right == null){
            leafToLeafPair base = new leafToLeafPair();
            base.NTLMaxSum = root.val;
            return base;
        }

        leafToLeafPair leftAns = maxLeafSumHelper(root.left);
        leafToLeafPair rightAns = maxLeafSumHelper(root.right);
        leafToLeafPair ans = new leafToLeafPair();
        ans.LTLMaxSum = Math.max(leftAns.LTLMaxSum, rightAns.LTLMaxSum);
        if(root.left != null && root.right != null){
            ans.LTLMaxSum = Math.max(ans.LTLMaxSum, leftAns.NTLMaxSum + root.val + rightAns.NTLMaxSum);
        }
        ans.NTLMaxSum = Math.max(leftAns.NTLMaxSum, rightAns.NTLMaxSum) + root.val;
        return ans;
    }

    public static int maxLeafSum(TreeNode root){
        leafToLeafPair ans = maxLeafSumHelper(root);
        return ans.LTLMaxSum;
        // for gfg
        // return ans.LTLMaxSum != -(int)1e9 ? ans.LTLMaxSum : Math.max(ans.LTLMaxSum, ans.NTLMaxSum);
    }

    // July 1, 2021
    
    // Given the root of a binary tree, return the maximum path sum of any path.
    // https://leetcode.com/problems/binary-tree-maximum-path-sum/

    // My solution
    public int maxPathSum(TreeNode root, int[] NTNMaxSum) {
        if(root == null) {
            NTNMaxSum[0] = Math.max(-(int)1e9, NTNMaxSum[0]);
            return -(int)1e9;
        }
        if(root.left == null && root.right == null) {
            NTNMaxSum[0] = Math.max(NTNMaxSum[0], root.val);
            return root.val;
        }
        int leftAns = maxPathSum(root.left, NTNMaxSum);
        int rightAns = maxPathSum(root.right, NTNMaxSum);
        int currMaxPath = Math.max(Math.max(leftAns, rightAns) + root.val, root.val);
        NTNMaxSum[0] = Math.max(Math.max(currMaxPath, leftAns + root.val + rightAns), NTNMaxSum[0]);
        return currMaxPath;        
    }
    // public int maxPathSum(TreeNode root) {
    //     int[] tmp = new int[1];
    //     tmp[0] = -(int)1e9;
    //     maxPathSum(root, tmp);
    //     return tmp[0];
        
    // }

    // Class soln
    public class NTNPair {
        int maxPossibleAns = -(int)1e9;
        int NTNMaxSum = -(int)1e9;
    }
    public int getMaxSum(int... arr) {
        int maxEle = arr[0];
        for(int ele: arr) maxEle = Math.max(maxEle, ele);
        return maxEle;
    }

    public NTNPair maxPathSum_(TreeNode root) {
        NTNPair ans = new NTNPair();
        if(root == null) return ans;
        NTNPair left = maxPathSum_(root.left);
        NTNPair right = maxPathSum_(root.right);
        int oneSidedMax = Math.max(left.NTNMaxSum, right.NTNMaxSum) + root.val;
        ans.maxPossibleAns = getMaxSum(left.maxPossibleAns, right.maxPossibleAns, root.val, oneSidedMax, left.NTNMaxSum + root.val + right.NTNMaxSum);
        ans.NTNMaxSum = Math.max(oneSidedMax, root.val);
        return ans;
    }

    public int maxPathSum(TreeNode root) {
        return maxPathSum_(root).maxPossibleAns;
    }

    // leetcode 968
    // https://leetcode.com/problems/binary-tree-cameras/
    // -1 - required, 0 - covered, 1 - has camera
    public int minCameraCover_(TreeNode root, int[] res) {
        if (root == null) return 0;
        int left = minCameraCover_(root.left, res);
        int right = minCameraCover_(root.right, res);
        if (left == -1 || right == -1) {
            res[0]++;
            return 1;
        }
        if(left == 1 || right == 1) return 0;
        return -1; 
    }
    
    public int minCameraCover(TreeNode root) {
        int[] res = new int[1];
        int status = minCameraCover_(root, res);
        if(status == -1) res[0]++;
        return res[0];
    }

    // Leetcode 337 - https://leetcode.com/problems/house-robber-iii/
    // for every node, we will store these values - {profit with robbing this house, profit without robbing this house}
    public int[] houseRobIII_(TreeNode root) {
        if(root == null) return new int[2];
        int[] leftAns = houseRobIII_(root.left);
        int[] rightAns = houseRobIII_(root.right);
        int[] myAns = new int[2];
        myAns[0] = root.val + leftAns[1] + rightAns[1];
        myAns[1] = Math.max(leftAns[0], leftAns[1]) + Math.max(rightAns[0], rightAns[1]);
        return myAns;
    }

    public int houseRobIII(TreeNode root) {
        int[] ans = houseRobIII_(root);
        return Math.max(ans[0], ans[1]);
    }

    // Leetcode 1372 - Longest zig zag path in binary tree
    // https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
    // [left zig zag, right zig zag] or [forward, backward]
    public int[] longestZigZag_(TreeNode root) {
        if(root == null) return new int[]{-1, -1, -1};
        int[] ans = new int[3];
        int[] leftAns = longestZigZag_(root.left);
        int[] rightAns = longestZigZag_(root.right);
        // left or forward move
        ans[0] = leftAns[1] + 1;
        // right or backward move
        ans[1] = rightAns[0] + 1;

        ans[2] = Math.max(Math.max(ans[0], ans[1]), Math.max(leftAns[2], rightAns[2]));
        return ans;
    }
    
    public int longestZigZag(TreeNode root) {
        int[] ans = longestZigZag_(root);
        return ans[2];
    }
}
