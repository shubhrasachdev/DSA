// March 8, 2021
public class l003 {
    public static class Node{
        int data = 0;
        Node left = null, right = null;
        Node(int data){this.data = data;}
    }

    
    public static Node prev = null;
    public static boolean isBST(Node node){
        if(node == null) return true;
        boolean leftRes = isBST(node.left);
        if(!leftRes) return false;

        if(prev != null && prev.data > node.data) return false;
        prev = node;

        boolean rightRes = isBST(node.right);
        if(!rightRes) return false;
        return true;
    }


    // is given tree a bst?
    public class isBSTSolPair{
        int maxEle = -(int) 1e8;
        int minEle = (int) 1e8;
        boolean isBST = true;

        int height = -1;
        boolean isBal = true;

        int largestBSTSize = 0;
        Node largestBSTNode = null;
        int totalNoOfBST = 0;

    }

    public isBSTSolPair isBST_(Node node){
        if(node == null) return new isBSTSolPair();
        isBSTSolPair left = isBST_(node.left);
        isBSTSolPair right = isBST_(node.right);
        isBSTSolPair res = new isBSTSolPair();
        res.isBST = false;
        if(left.isBST && right.isBST && node.data < right.minEle && node.data > left.maxEle){
            res.isBST = true;
            res.maxEle = Math.max(node.data, right.maxEle);
            res.minEle = Math.min(node.data, left.minEle);
        }
        return res;
    }

    // is given tree balanced?
    // balanced tree: |left.height - right.height| <= 1 for every node
    public static class isBalPair {
        int height = -1;
        boolean balanceTree = true;
    }

    public static isBalPair isBal_(Node node){
        if(node == null) return new isBalPair();
        isBalPair left = isBal_(node.left);
        if (!left.balanceTree) return left;

        isBalPair right = isBal_(node.right);
        if (!right.balanceTree) return right;
        
        isBalPair res = new isBalPair();
        res.balanceTree =  false;
        if(left.balanceTree && right.balanceTree && Math.abs(left.height - right.height) <= 1){
            res.balanceTree =  true;
            res.height = Math.max(left.height, right.height) + 1;
        }
        return res;
    }

    public static boolean isBal(Node node){
        isBalPair res = isBal_(node);
        return res.balanceTree;
    }

    public static isBSTSolPair allSolutions(Node node){
        isBSTSolPair left = allSolutions(node.left);
        isBSTSolPair right = allSolutions(node.right);

        isBSTSolPair ans = new isBSTSolPair();
        ans.isBST = left.isBST && right.isBST && left.maxEle < node.data && node.data < right.minEle;
        ans.isBal = left.isBal && right.isBal && Math.abs(left.height - right.height) <= 1;
        
        ans.maxEle = Math.max(node.data, right.maxEle);
        ans.minEle = Math.min(node.data, left.minEle);

        ans.height = Math.max(left.height, right.height) + 1;

        ans.totalNoOfBST = left.totalNoOfBST + right.totalNoOfBST + (ans.isBST ? 1 : 0);

        if(ans.isBST) {
            ans.largestBSTNode = node;
            ans.largestBSTSize += left.largestBSTSize + right.largestBSTSize + 1;
        }else{
            if(left.largestBSTSize > right.largestBSTSize){
                ans.largestBSTSize = left.largestBSTSize;
                ans.largestBSTNode = left.largestBSTNode;
            }else{
                ans.largestBSTSize = right.largestBSTSize;
                ans.largestBSTNode = right.largestBSTNode;
            }
        }
        return ans;

    }
    
}
