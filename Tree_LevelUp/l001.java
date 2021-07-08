import java.util.*;
public class l001 {

    // June 12, 2021 
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
        return root == null ? -(int)1e9 : Math.max(Math.max(maximum(root.left), maximum(root.right)), root.data); 
    }

    public static boolean find(TreeNode root, int data) {
        if(root == null) return false;
        if(root.data == data) return true;
        return find(root.left, data) || find(root.right, data);
    }

    public static ArrayList<TreeNode> nodeToRootPath(TreeNode root, int data){
        if(root == null) return new ArrayList<>();
        if(root.data == data){
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }
        ArrayList<TreeNode> leftPath = nodeToRootPath(root.left, data);
        if(leftPath.size() != 0) {
            leftPath.add(root);
            return leftPath;
        }
        ArrayList<TreeNode> rightPath = nodeToRootPath(root.right, data);
        if(rightPath.size() != 0) rightPath.add(root);
        return rightPath;
    }

    public static boolean nodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans){
        if(root == null) return false;
        boolean res = (root.data == data) || nodeToRootPath(root.left, data, ans) || nodeToRootPath(root.right, data, ans);
        if(res) ans.add(root);
        return res;
    }

    public static void rootToAllLeafPath(TreeNode node, ArrayList<Integer> smallAns, ArrayList<ArrayList<Integer>> ans){
        if(node == null) return;
        smallAns.add(node.data);
        if(node.left == null && node.right == null){
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            ans.add(base);
        }
        rootToAllLeafPath(node.left, smallAns, ans);        
        rootToAllLeafPath(node.right, smallAns, ans);
        smallAns.remove(smallAns.size() - 1);
    }

    public static ArrayList<ArrayList<Integer>> nodeToAllLeafPath(TreeNode root){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<>();
        rootToAllLeafPath(root, smallAns, ans);
        return ans;
    }

    public static void singleChildNodes(TreeNode node, ArrayList<Integer> ans){
        if(node == null || (node.left == null && node.right == null)) return;
        if(node.left == null || node.right == null) ans.add(node.data);
        singleChildNodes(node.left, ans);
        singleChildNodes(node.right, ans);
    }

    public static int countSingleChildNodes(TreeNode node){
        if(node == null || (node.left == null && node.right == null)) return 0;
        int ans = countSingleChildNodes(node.left);
        ans += countSingleChildNodes(node.right);
        if(node.left == null || node.right == null) ans++;
        return ans;
    }

    public static int countSingleChildNodes2(TreeNode node){
        if(node == null) return 0;
        int ans = countSingleChildNodes2(node.left);
        ans += countSingleChildNodes2(node.right);
        if(node.left == null ^ node.right == null) ans++; // use xor operation
        return ans;
    }

    // Print nodes at distance k from a given node
    // Leetcode 863 : https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

    // O(n) space in worst case method
    public static void kDown(TreeNode root, TreeNode blockNode, int k, List<Integer> ans){
        if(root == null || root == blockNode || k  < 0) return;
        if(k == 0) {
            ans.add(root.data);
            return;
        }
        kDown(root.left, blockNode, k - 1, ans);
        kDown(root.right, blockNode, k - 1, ans);
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> path = new ArrayList<>();
        nodeToRootPath(root, target.data, path);
        List<Integer> ans = new ArrayList<>();
        TreeNode blockNode = null;
        for(int i = 0; i < path.size(); i++){
            if(k - i < 0) break;
            kDown(path.get(i), blockNode, k-i, ans);
            blockNode = path.get(i);
        }
        return ans;
    }

    // O(1) space method
    public static int distanceK2(TreeNode root, TreeNode target, int k, ArrayList<Integer> ans) {
        if(root == null) return -1;
        if(root == target){
            kDown(root, null, k, ans);
            return 1;
        }
        int ld = distanceK2(root.left, target, k, ans);
        if(ld != -1){ 
            kDown(root, root.left, k - ld, ans);
            return ld + 1;
        }
        int rd = distanceK2(root.right, target, k, ans);
        if(rd != -1){ 
            kDown(root, root.right, k - rd, ans);
            return rd + 1;
        }
        return -1;
    }

    // Burning tree
    public static void kDown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans) {
        if(root == null || root == blockNode) return;
        if(time == ans.size()) ans.add(new ArrayList<>());
        ans.get(time).add(root.data);
        kDown(root.left, time + 1, blockNode, ans);
        kDown(root.right, time + 1, blockNode, ans);
    }

    public static int burningTree(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans){
        if(root == null) return -1;
        if(root.data == target) {
            kDown(root, 0, null, ans);
            return 1;
        }
        int ld = burningTree(root.left, target, ans);
        if(ld != -1){
            kDown(root, ld, root.left, ans);
            return ld + 1;
        } 
        int rd = burningTree(root.right, target, ans);
        if(rd != -1){
            kDown(root, rd, root.left, ans);
            return rd + 1;
        } 
        return -1;
    }

    public static ArrayList<ArrayList<Integer>> burningTree(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, target, ans);
        return ans;
    }

    // June 13, 2021

    // Burning Tree With Water
    public static void kdown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> water) {
        if (root == null || root == blockNode || water.contains(root.data))
            return;

        if (time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.data);

        kdown(root.left, time + 1, blockNode, ans, water);
        kdown(root.right, time + 1, blockNode, ans, water);
    }

    // -1 : we didnt get the target node
    // -2 : fire will not reach that node, t > 0 :
    // fire will reach with time t.
    public static int burningTreeWithWater(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> water) {
        if (root == null)
            return -1;
        if (root.data == target) {
            if (!water.contains(root.data)) {
                kdown(root, 0, null, ans, water);
                return 1;
            } else
                return -2;
        }

        int ld = burningTreeWithWater(root.left, target, ans, water);
        if (ld > 0) {
            if (!water.contains(root.data)) {
                kdown(root, ld, root.left, ans, water);
                return ld + 1;
            }
            return -2;
        }
        if (ld == -2)
            return -2;

        int rd = burningTreeWithWater(root.right, target, ans, water);
        if (rd > 0) {
            if (!water.contains(root.data)) {
                kdown(root, rd, root.right, ans, water);
                return rd + 1;
            }
            return -2;
        }
        if (rd == -2)
            return -2;

        return -1;
    }

    // Lowest common ancestor
    // public TreeNode lowestCommonAncestor(TreeNode root, int p, int q){
        
    // }


    // June 17, 2021

    // Level order traversal of Binary tree
    public static void levelOrder(TreeNode root) {
        if(root == null) return;
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        queue.addLast(root);
        while(queue.size() != 0){
            ArrayList<Integer> smallAns = new ArrayList<>();
            int size = queue.size();
            while(size-- > 0){
                TreeNode rnode = queue.removeFirst();
                smallAns.add(rnode.data);
                if(rnode.left != null) queue.addLast(rnode.left);
                if(rnode.right != null) queue.addLast(rnode.right);
                
            }
            ans.add(smallAns);
        }
    }

    // Left View of Binary Tree
    public static List<Integer> leftView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while(queue.size() != 0){
            int size = queue.size();
            ans.add(queue.getFirst().data);
            while(size-- > 0){
                TreeNode rnode = queue.removeFirst();
                if(rnode.left != null) queue.addLast(rnode.left);
                if(rnode.right != null) queue.addLast(rnode.right);
                
            }
        }
        return ans;
    }

    // Right View of Binary Tree
    // Leetcode 199: https://leetcode.com/problems/binary-tree-right-side-view/
    public static List<Integer> rightView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while(queue.size() != 0){
            int size = queue.size();
            ans.add(queue.getFirst().data);
            while(size-- > 0){
                TreeNode rnode = queue.removeFirst();
                if(rnode.right != null) queue.addLast(rnode.right);
                if(rnode.left != null) queue.addLast(rnode.left);
                
            }
        }
        return ans;
    }

    // to find width of shadow, gives range of min and max levels
    public static void widthOfShadow(TreeNode root, int[] minMax, int vl){ // or number of vertical levels
        if(root == null) return;
        minMax[0] = Math.min(minMax[0], vl);
        minMax[1] = Math.max(minMax[1], vl);
        if(root.left != null) widthOfShadow(root.left, minMax, vl - 1);
        if(root.right != null) widthOfShadow(root.right, minMax, vl + 1);

    }

    public static int width(TreeNode root) {
        int[] minMax = new int[2];
        widthOfShadow(root, minMax, 0);
        int len = minMax[1] - minMax[0] + 1;
        return len;
    }

    // vertical order traversal
    public static class vPair {
        TreeNode node = null;
        int vl = 0;
        vPair(TreeNode node, int vl){
            this.node = node;
            this.vl = vl;
        }
    }

    public static ArrayList<ArrayList<Integer>> verticalOrder(TreeNode root) {
        LinkedList<vPair> q = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, minMax, 0);
        int len = minMax[1] - minMax[0] + 1;
        q.addLast(new vPair(root, Math.abs(minMax[0])));
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < len; i++) ans.add(new ArrayList<>());
        while(q.size() != 0) {
            int size = q.size();
            while(size-- > 0) {
                vPair rp = q.removeFirst();
                TreeNode rnode = rp.node;
                int vl = rp.vl;
                ans.get(vl).add(rnode.data);
                if(rnode.left != null) q.addLast(new vPair(rnode.left, vl - 1));
                if(rnode.right != null) q.addLast(new vPair(rnode.right, vl + 1));
            }
        }
        return ans;
    }

    // bottom view
    public static ArrayList<Integer> bottomView(TreeNode root) {
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
                ans.set(vl, rnode.data);
                if(rnode.left != null) q.addLast(new vPair(rnode.left, vl - 1));
                if(rnode.right != null) q.addLast(new vPair(rnode.right, vl + 1));
            }
        }
        return ans;
    }

    // top view
    public static ArrayList<Integer> topView(TreeNode root) {
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
                if(ans.get(vl) == null) ans.set(vl, rnode.data);
                if(rnode.left != null) q.addLast(new vPair(rnode.left, vl - 1));
                if(rnode.right != null) q.addLast(new vPair(rnode.right, vl + 1));
            }
        }
        return ans;
    }


    public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        que.addLast(root);
        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) { // diagonal
                TreeNode node = que.removeFirst();
                while (node != null) { // clusters of diagonal
                    smallAns.add(node.data);
                    if (node.left != null)
                        que.addLast(node.left);
                    node = node.right;
                }
            }
            ans.add(smallAns);
        }
        return ans;
    }

    public static ArrayList<ArrayList<Integer>> antiDiagonalOrder(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        que.addLast(root);
        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) { // diagonal
                TreeNode node = que.removeFirst();
                while (node != null) { // clusters of diagonal
                    smallAns.add(node.data);
                    if (node.right != null)
                        que.addLast(node.right);
                    node = node.left;
                }
            }
            ans.add(smallAns);
        }
        return ans;
    }

}