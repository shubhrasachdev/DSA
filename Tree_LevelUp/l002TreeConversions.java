public class l002TreeConversions {
    public static class TreeNode {
        int data = 0;
        TreeNode left = null, right = null;
        TreeNode(int data){
            this.data = data;
        }
    }

    public static class DLLNode{
        int data = 0;
        DLLNode prev = null, next = null;
        DLLNode(int data){
            this.data = data;
        }
    }
    // convert Binary tree to :
    
    // DLL

    // My method
    public static TreeNode[] bToDLLRec(TreeNode root) {
        TreeNode newHead = root, newTail = root;
        if(root.left != null){
            TreeNode[] prev = bToDLLRec(root.left);
            prev[1].right = root;
            root.left = prev[1];
            newHead = prev[0];
        }
        if(root.right != null) {
            TreeNode[] next = bToDLLRec(root.right);
            root.right = next[0];
            next[0].left = root;
            newTail = next[1];
        }
        return new TreeNode[]{newHead, newTail};
    }
    public static TreeNode bToDLL(TreeNode root){ return bToDLLRec(root)[0]; }


    // CDLL
    // My method
    public static TreeNode[] bTreeToClistRec(TreeNode root) {
        TreeNode newHead = root, newTail = root;
        if(root.left != null){
            TreeNode[] prev = bTreeToClistRec(root.left);
            prev[1].right = root;
            root.left = prev[1];
            newHead = prev[0];
        }
        if(root.right != null) {
            TreeNode[] next = bTreeToClistRec(root.right);
            root.right = next[0];
            next[0].left = root;
            newTail = next[1];
        }
        return new TreeNode[]{newHead, newTail};
    }
    TreeNode bTreeToClist(TreeNode root){ 
        TreeNode[] clist = bTreeToClistRec(root);
        clist[1].right = clist[0];
        clist[0].left = clist[1];
        return clist[0];
    }

    // BST (Time - O(nlogn) , Space - O(logn), in-place) given that binary tree is balanced tree
    // Steps
    // 1. Convert binary tree to DLL
    // 2. Sort DLL
    // 3. Convert sorted DLL to BST
    
    public static TreeNode mergeDLL(TreeNode h1, TreeNode h2) {
        if(h1 == null || h2 == null) return h1 != null ? h1 : h2;
        TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy;
        TreeNode c1 = h1, c2 = h2;
        while(c1 != null && c2 != null) {
            if(c1.data <= c2.data) {
                prev.right = c1;
                c1.left = prev;
                c1 = c1.right; 
            } else {
                prev.right = c2;
                c2.left = prev;
                c2 = c2.right;
            }
            prev = prev.right;
        }
        while(c1 != null) {
            prev.right = c1;
            c1.left = prev;
            c1 = c1.right;
            prev = prev.right;
        }
        while(c2 != null) {
            prev.right = c2;
            c2.left = prev;
            c2 = c2.right;
            prev = prev.right;
        }
        TreeNode head = dummy.right;
        dummy.right = head.left = null;
        return head;
    } 

    public static TreeNode getMidNode(TreeNode node){
        if(node == null || node.right == null) return node;
        TreeNode slow = node, fast = node;
        while(fast.right != null && fast.right.right != null) {
            slow = slow.right;
            fast = fast.right.right;
        }
        return slow;
    }

    public static TreeNode sortDLL(TreeNode head) {
        if(head == null || head.right == null) return head;
        TreeNode midNode = getMidNode(head);
        TreeNode nHead = midNode.right;
        nHead.left = midNode.right = null;
        return mergeDLL(sortDLL(head), sortDLL(nHead));
    }
    
    public static TreeNode sortedDLLToBST(TreeNode head) {
        TreeNode root = getMidNode(head);
        TreeNode leftDLLHead = head;
        TreeNode rightDLLHead = root.right;
        root.left.right = root.right.left = null;
        root.left = root.right = null;
        root.left = sortedDLLToBST(leftDLLHead);
        root.right = sortedDLLToBST(rightDLLHead);
        return root;
    }

    public static TreeNode btreeToBST(TreeNode root){       // Time             Space
                                                            //++++++++++++++++++++++++++
        TreeNode head = bToDLL(root);                       // O(n)             O(logn)
        head = sortDLL(head);                               // O(nlogn)         O(logn)
        return sortedDLLToBST(head);                        // O(nlogn)         O(logn)
    }
}
