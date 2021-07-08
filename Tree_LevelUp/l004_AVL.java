public class l004_AVL {
    public static class TreeNode {
        int val = 0;
        int bal = 0;
        int height = 0;
        TreeNode left = null, right = null;
        TreeNode(int val) { this.val = val; }
    } 

    // June 24, 2021
    // ================================================================================================================
    
    // O(1)
    public static void updateHtBal(TreeNode node) {
        int lh = node.left != null ? node.left.height : -1;
        int rh = node.right != null ? node.right.height : -1;
        node.height = Math.max(lh, rh) + 1;
        node.bal = lh - rh;
    }

    // O(1)
    public static TreeNode rightRotation(TreeNode A) {
        TreeNode B = A.left;
        TreeNode rightOfB = B.right;
        B.right = A;
        A.left = rightOfB;
        updateHtBal(A);
        updateHtBal(B);
        return B;
    }

    // O(1)
    public static TreeNode leftRotation(TreeNode A) {
        TreeNode B = A.right;
        TreeNode leftOfB = B.left;
        B.left = A;
        A.right = leftOfB;
        updateHtBal(A);
        updateHtBal(B);
        return B;
    }

    // O(1)
    public static TreeNode getRotation(TreeNode root) {
        updateHtBal(root);
        if(root.bal == 2) { // ll, lr
            if(root.left.bal == 1) { // ll
                return rightRotation(root);
            } else { // lr
                root.left = leftRotation(root.left);
                return rightRotation(root);
            
            }
        } else if(root.bal == -2) { // rr, rl
            if(root.right.bal == -1) { // rr
                return leftRotation(root);
            } else { // rl
                root.right = rightRotation(root.right);
                return leftRotation(root);

            }
        }
        return root;
    }

    
    // ================================================================================================================
    
    // O(log n)
    public static int getMaximum(TreeNode root) {
        while(root.right != null) root = root.right;
        return root.val;
    }
    
    // Add node to AVL Tree - O(log n)
    public static TreeNode add(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if(root.val < val) root.right = add(root.right, val);
        else root.left = add(root.left, val);
        root = getRotation(root); // balancing step
        return root;
    }

    // Remove node from AVL Tree - O(log n)
    public static TreeNode remove(TreeNode root, int val) {
        if(root == null) return null;
        if(root.val == val) {
            if(root.left == null || root.right == null) return root.left != null ? root.left : root.right; 
            int maxData = getMaximum(root.left);
            root.val = maxData;
            root.left = remove(root.left, maxData);
        }
        else if(root.val < val) root.right = remove(root.right, val);
        else root.left = remove(root.left, val);
        root = getRotation(root); // balancing step
        return root;
    }

    // O(n)
    public static void display(TreeNode node) {
        if(node == null) return;
        StringBuilder sb = new StringBuilder();
        sb.append(node.left == null ? "." : node.left.val);
        sb.append("->" + node.val + "<-");
        sb.append(node.right == null ? "." : node.right.val);
        System.out.println(sb.toString());
        display(node.left);
        display(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = null;
        for(int i = 1; i <= 10; i++){
            root = add(root, i*10);
        }
        display(root);
    }

    // leetcode 1382

    
}