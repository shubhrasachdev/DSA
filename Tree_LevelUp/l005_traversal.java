import java.util.ArrayList;


public class l005_traversal {
    public static class TreeNode {
        int val = 0;
        int bal = 0;
        int height = 0;
        TreeNode left = null, right = null;
        TreeNode(int val) { this.val = val; }
    } 

    public static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while(node.right != null && node.right != curr) node = node.right;
        return node;   
    }

    public static ArrayList<Integer> morrisInOrderTraversal(TreeNode root) {
        TreeNode curr = root;
        ArrayList<Integer> ans = new ArrayList<>();
        while(curr != null) {
            TreeNode left = curr.left;
            if(left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if(rightMost.right == null) { // thread create
                    rightMost.right = curr;
                    curr = curr.left;
                } else { // thread destroy
                    rightMost.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

    public static ArrayList<Integer> morrisPreOrderTraversal(TreeNode root) {
        TreeNode curr = root;
        ArrayList<Integer> ans = new ArrayList<>();
        while(curr != null) {
            TreeNode left = curr.left;
            if(left != null) {
                TreeNode rightMost = getRightMostNode(left, curr);
                if(rightMost.right == null) {
                    rightMost.right = curr;
                    ans.add(curr.val);
                    curr = curr.left;
                } else {
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
            else {
                ans.add(curr.val);
                curr = curr.right;
            } 
        }
        return ans;
    }

    // Validate if given tree is a BST
    // Leetcode 98: https://leetcode.com/problems/validate-binary-search-tree/

    // Method 1: Recursive using prev pointer
    public static boolean isValidBST(TreeNode root, TreeNode[] prev) {
        if(root == null) return true;
        if(!isValidBST(root.left, prev)) return false;

        if(prev[0] != null && prev[0].val >= root.val) return false;
        prev[0] = root;

        if(!isValidBST(root.right, prev)) return false;
        
        return true;
    }

    // Method 2: Using inorder morris traversal
    public static boolean morrisInOrderTraversal_isValidBST(TreeNode root) {
        TreeNode curr = root;
        int prev = Integer.MIN_VALUE;
        while(curr != null) {
            TreeNode left = curr.left;
            if(left == null) {
                if(prev >= curr.val) return false;
                prev = curr.val;
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if(rightMost.right == curr) {
                    rightMost.right = null;
                    if(prev >= curr.val) return false;
                    prev = curr.val;
                    curr = curr.right;
                } else {
                    rightMost.right = curr;
                    curr = curr.left;
                }
            }
        }
        return true;
    }

    public static int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;
        while(curr != null) {
            TreeNode left = curr.left;
            if(left == null) {
                if(--k == 0) return curr.val;
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if(rightMost.right == curr) {
                    rightMost.right = null;
                    if(--k == 0) return curr.val;
                    curr = curr.right;
                } else {
                    rightMost.right = curr;
                    curr = curr.left;
                }
            }
        }
        return -1;
    }

    // kth largest element in BST
    // https://practice.geeksforgeeks.org/problems/kth-largest-element-in-bst/1
    public static TreeNode getLeftMostNode(TreeNode node, TreeNode curr) {
        while(node.left != null && node.left != curr) node = node.left;
        return node;   
    }

    public static int kthLargest(TreeNode root, int k) {
        TreeNode curr = root;
        while(curr != null) {
            TreeNode right = curr.right;
            if(right == null) {
                if(--k == 0) return curr.val;
                curr = curr.left;
            } else {
                TreeNode leftMost = getLeftMostNode(right, curr);
                if(leftMost.left == curr){
                    leftMost.left = null;
                    if(--k == 0) return curr.val;
                    curr = curr.left;
                } else {
                    leftMost.left = curr;
                    curr = curr.right;
                }
            }            
        }
        return -1;
    }

    // June 27, 2021
    // BST to DLL using Morris traversal
    public static TreeNode bstToDLL_inorderMorris(TreeNode root){
        TreeNode dummy = new TreeNode(-1);
        TreeNode curr = root, prev = dummy;
        while(curr != null){
            TreeNode left = curr.left;
            if(left == null){
                prev.right = curr;
                curr.left = prev;
                prev = curr;
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if(rightMost.right == null) {
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    rightMost.right = null;
                    prev.right = curr;
                    curr.left = prev;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }
        TreeNode dllHead = dummy.right;
        dummy.right = null;
        dllHead.left = null;
        return dllHead;
    }

    // BST Iterator for inorder traversal using Morris
    // Leetcode 173 - https://leetcode.com/problems/binary-search-tree-iterator/
    class BSTIterator {
        TreeNode curr = null;
        public BSTIterator(TreeNode root) {
            this.curr = root;

        }

        private TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
            while(node.right != null && node.right != curr) node = node.right;
            return node;   
        }
        
        public int next() {
            int rv = -1;
            while(this.curr != null) {
                TreeNode left = this.curr.left;
                if(left == null){
                    rv = this.curr.val;
                    curr = curr.right;
                    break;
                } else {
                    TreeNode rightMost = getRightMostNode(left, this.curr);
                    if(rightMost.right == null){
                        rightMost.right = this.curr;
                        this.curr = this.curr.left;
                    } else {
                        rv = this.curr.val;
                        rightMost.right = null;
                        this.curr = this.curr.right;
                        break;
                    }
                }
            }
            return rv;            
        }
        
        public boolean hasNext() {
            return this.curr != null;
        }
    }
}
