import java.util.*;

public class questions {

    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1#
    
    // Recursion solution
    public int dfs(int sr, int sc, int er, int ec, boolean[][] blocked, int[][] dir){
        int mod = (int)1e9 + 7;
        if(sr == er && sc == ec){
            return 1;
        };
        int count = 0;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 1 && c >= 1 && r <= er && c <= ec && !blocked[r][c])
                count = (count + dfs(r, c, er, ec, blocked, dir) % mod) % mod; // using formula (a+b)%c = (a%c + b%c)%c
        }
        return count;
        
    }
    public int FindWays(int n, int m, int[][] blocked_cells)
    {
        boolean[][] blocked = new boolean[n + 1][m + 1];
        for(int i = 0; i < blocked_cells.length; i++){
            int row = blocked_cells[i][0];
            int col = blocked_cells[i][1];
            blocked[row][col] = true;
        }
        if(blocked[1][1] || blocked[n][m]) return 0;
        int[][] dir = {{0,1},{1,0}};
        return dfs(1, 1, n, m, blocked, dir);
    }

    // Memoization Solution
    public static int dfs(int sr, int sc, int er, int ec, int[][] dir, boolean[][] blocked, int[][] dp){
        int mod = (int)1e9 + 7;
        if(sr == er && sc == ec) return dp[sr][sc] = 1;
        if(dp[sr][sc] != -1) return dp[sr][sc];
        int count = 0;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 1 && c >= 1 && r <= er && c <= ec && !blocked[r][c])
                count = (count + dfs(r, c, er, ec, dir, blocked, dp) % mod) % mod;
        }
        return dp[sr][sc] = count;
    }
    
    public int FindWays_memo(int n, int m, int[][] blocked_cells)
    {
        boolean[][] blocked = new boolean[n + 1][m + 1];
        for(int i = 0; i < blocked_cells.length; i++){
            int row = blocked_cells[i][0];
            int col = blocked_cells[i][1];
            blocked[row][col] = true;
        }
        
        int[][] dp = new int[n + 1][m + 1];
        for(int []d : dp) Arrays.fill(d, -1);
        if(blocked[1][1] || blocked[n][m]) return 0;
        int[][] dir = {{0,1},{1,0}};
        return dfs(1, 1, n, m, dir, blocked, dp);
    }

    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
    public static void findPath(int sr, int sc, int er, int ec, int[][] blocked, int[][] dir, String[] dirS, String ans, ArrayList<String> res){
        if(sr == er && sc == ec){
            res.add(ans);
            return;
        }
        blocked[sr][sc] = 0;
        
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec && blocked[r][c] == 1)
                findPath(r,c,er,ec,blocked,dir,dirS,ans+dirS[d],res);
        }
        blocked[sr][sc] = 1;
    }
    
    public static ArrayList<String> findPath(int[][] blocked, int n) {
        String[] dirS = {"D", "L", "R", "U"};
        int[][] dir = {{1,0}, {0, -1}, {0, 1}, {-1, 0}};
        ArrayList<String> res = new ArrayList<>();
        if(blocked[0][0] == 0 || blocked[n-1][n-1] == 0) return res;
        findPath(0, 0, n-1, n-1, blocked, dir, dirS, "", res);
        return res;
        
    }

    // Homework - Leetcode 1219
    // https://leetcode.com/problems/path-with-maximum-gold/
    public static int getMaximumGold(int sr, int sc, int er, int ec, int[][] dir, int[][] grid){
        int currGold = grid[sr][sc];
        int maxGold = grid[sr][sc];
        grid[sr][sc] = 0;
        for(int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec && grid[r][c] != 0) {
                int recGold = getMaximumGold(r, c, er, ec, dir, grid);
                maxGold = Math.max(maxGold, recGold + currGold);
            }
        }
        grid[sr][sc] = currGold;       
        return maxGold;
        
    }
    public static int getMaximumGold(int[][] grid) {
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int n = grid.length, m = grid[0].length;
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++){
                if(grid[i][j] != 0){
                    int curr = getMaximumGold(i, j, n - 1, m - 1, dir, grid);
                    res = Math.max(res, curr);
                }
            }
        }
        return res;
    }


    // May 16, 2021
    // Leetcode 39 - https://leetcode.com/problems/combination-sum/
    // combination with infinite 
    public int combinationSum(int[] arr, int tar, int idx, List<Integer> smallAns, List<List<Integer>> res){
        if(tar == 0){
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }
        int count = 0;
        
        for(int i = idx; i < arr.length; i++){
            if(tar - arr[i] >= 0){
                smallAns.add(arr[i]);
                count += combinationSum(arr, tar-arr[i], i, smallAns, res);
                smallAns.remove(smallAns.size() - 1);
            }
        }
        return count;
        
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(candidates, target, 0, smallAns, res);
        return res;
    }

    // Leetcode 40 - https://leetcode.com/problems/combination-sum-ii/
    public static int combinationSum2(int[] arr, int target, int idx, List<Integer> ans, List<List<Integer>> res){
        if(target == 0){
            List<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return 1;
        }
        int count = 0, prev = -1;
        for(int i = idx; i < arr.length; i++){
            if(target - arr[i] >= 0 && prev != arr[i]){
                ans.add(arr[i]);
                count += combinationSum2(arr, target - arr[i], i + 1, ans, res);
                ans.remove(ans.size() - 1);
                prev = arr[i];
            }
        }
        return count;
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2(candidates, target, 0, ans, res);
        return res;
    }

    // Leetcode 46 - https://leetcode.com/problems/permutations/
    public int permute(int[] arr, int curr, List<Integer> smallAns, List<List<Integer>> res){
        if(curr == arr.length){
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != -11){ // as per constraints
                int temp = arr[i];
                arr[i] = -11;
                smallAns.add(temp);
                count += permute(arr, curr + 1, smallAns, res);
                smallAns.remove(smallAns.size() - 1);
                arr[i] = temp;
            }
        }
        return count;
    }
    
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        permute(nums, 0, smallAns, res);
        return res;
    }

    // Leetcode 47 - https://leetcode.com/problems/permutations-ii/
    public int permuteUnique(int[] arr, int curr, List<Integer> smallAns, List<List<Integer>> res){
        if(curr == arr.length){
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }
        int count = 0, prev = -12;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != -11 && prev != arr[i]){ // as per constraints
                int val = arr[i];
                arr[i] = -11;
                smallAns.add(val);
                count += permuteUnique(arr, curr + 1, smallAns, res);
                smallAns.remove(smallAns.size() - 1);
                arr[i] = val;
                prev = arr[i];
            }
        }
        return count;
        
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        permuteUnique(nums, 0, smallAns, res);
        return res;
        
    }

    // Leetcode 77 - https://leetcode.com/problems/combinations/
    public static int combine(int idx, int n, int k, List<Integer> smallAns, List<List<Integer>> res){
        if(k == 0){
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }
        int count = 0;
        for(int i = idx; i <= n; i++){
            smallAns.add(i);
            count += combine(i + 1, n, k - 1, smallAns, res);
            smallAns.remove(smallAns.size() - 1);
        }
        return count;
    }
    
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        combine(1, n, k, smallAns, res);
        return res;
        
    }

    // Leetcode 216 - https://leetcode.com/problems/combination-sum-iii/
    public static int combinationSum3(int n, int k, int idx, List<Integer> ans, List<List<Integer>> res){
        if(k == 0){
            if(n == 0){
                List<Integer> base = new ArrayList<>(ans);
                res.add(base);
                return 1;
            }
            return 0;
        }
        int count = 0;
        for(int i = idx; i <= 9; i++){
            if(n - i >= 0){
                ans.add(i);
                count += combinationSum3(n - i, k - 1, i + 1, ans, res);    
                ans.remove(ans.size() - 1);
            }
        }
        return count;
    }  
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> ans = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        combinationSum3(n, k, 1, ans, res);
        return res;
    }

    // Leetcode 784 - https://leetcode.com/problems/letter-case-permutation/
    public static int letterCasePermutation(String s, int idx, String ans, List<String> res){
        if(idx == s.length()){
            res.add(ans);
            return 1;
        }
        
        int count = 0;
        char ch = s.charAt(idx);
        if(Character.isAlphabetic(ch)){
            char toggleCh;
            if(ch >= 'a' && ch <= 'z') toggleCh = (char)(ch - 'a' + 'A');
            else toggleCh = (char)(ch + 'a' - 'A');
            count += letterCasePermutation(s, idx + 1, ans + toggleCh, res);
        }
        count += letterCasePermutation(s, idx + 1, ans + ch, res);
        return count;
        
    }
    
    public List<String> letterCasePermutation(String s) {
        String ans = new String();
        List<String> res = new ArrayList<>();
        letterCasePermutation(s, 0, ans, res);
        return res;
        
    }



    // May 20, 2021
    // Leetcode Question 51: https://leetcode.com/problems/n-queens/
    static boolean[] row;
    static boolean[] col;
    static boolean[] diag;
    static boolean[] adiag;
    
    public static int nqueens(boolean[][] boxes, int tqn, int idx, List<List<String>> res){
        int count = 0, n = boxes.length, m = boxes[0].length;
        if(tqn == 0){
            List<String> smallAns = new ArrayList<>();
            for(int r = 0; r < n; r++){
                StringBuilder sb = new StringBuilder("");
                for(int c = 0; c < m; c++)
                    sb.append(boxes[r][c] ? "Q" : ".");
                smallAns.add(sb.toString());
            }
            res.add(smallAns); 
            return 1;
        }
        
        for(int i = idx; i < n * m; i++){
            int r = i / m;
            int c = i % m;
            if(!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + (m - 1)]){
                row[r] = col[c] = diag[r + c] = adiag[r - c + (m - 1)] = true;
                boxes[r][c] = true;
                count += nqueens(boxes, tqn - 1, i + 1, res);
                boxes[r][c] = false;
                row[r] = col[c] = diag[r + c] = adiag[r - c + (m - 1)] = false;
            }
        }
        return count;
    }
    
    public List<List<String>> solveNQueens(int n) {
        int m = n, q = n;
        row = new boolean[n];
        col = new boolean[m];
        diag = new boolean[n + m - 1];
        adiag = new boolean[n + m - 1];
        boolean[][] boxes = new boolean[n][m];        
        List<List<String>> res = new ArrayList<>();
        System.out.println(nqueens(boxes, q, 0, res));
        return res;
    }

    // May 22, 2021
    // Optimized solution
    public static int nqueen_Combination04(boolean[][] boxes, int floor, int tqn, List<List<String>> res){
        int count = 0, n = boxes.length, m = boxes[0].length;
        if(tqn == 0){
            List<String> smallAns = new ArrayList<>();
            for(int r = 0; r < n; r++){
                StringBuilder sb = new StringBuilder("");
                for(int c = 0; c < m; c++)
                    sb.append(boxes[r][c] ? "Q" : ".");
                smallAns.add(sb.toString());
            }
            res.add(smallAns); 
            return 1;
        }
        for(int room = 0; room < m; room++){
            int r = floor;
            int c = room;
            if(!col[c] && !diag[r + c] && !adiag[r - c + (m - 1)]){
                col[c] = diag[r + c] = adiag[r - c + (m - 1)] = true;
                boxes[r][c] = true;
                count += nqueen_Combination04(boxes, floor + 1, tqn - 1, res);
                boxes[r][c] = false;
                col[c] = diag[r + c] = adiag[r - c + (m - 1)] = false;
            }
        }
        return count;
    }
    
    public List<List<String>> solveNQueensOpti(int n) {
        int m = n, q = n;
        // row = new boolean[n];
        col = new boolean[m];
        diag = new boolean[n + m - 1];
        adiag = new boolean[n + m - 1];
        boolean[][] boxes = new boolean[n][m];        
        List<List<String>> res = new ArrayList<>();
        System.out.println(nqueen_Combination04(boxes, 0, q, res));
        return res;
    }

    // Leetcode 52 : https://leetcode.com/problems/n-queens-ii/
    public static int totalNqueens(int n, int m, int tqn, int idx){ 
        if(tqn == 0){ 
            return 1;
        }
        int count = 0;
        for(int i = idx; i < n * m; i++){
            int r = i / m;
            int c = i % m;
            if(!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + (m - 1)]){
                row[r] = col[c] = diag[r + c] = adiag[r - c + (m - 1)] = true;
                count += totalNqueens(n, m, tqn - 1, i + 1);
                row[r] = col[c] = diag[r + c] = adiag[r - c + (m - 1)] = false;
            }
        }
        return count;
    }
    public static int totalNQueens(int n) {
        int m = n, q = n;
        row = new boolean[n];
        col = new boolean[m];
        diag = new boolean[n + m - 1];
        adiag = new boolean[n + m - 1];
        return totalNqueens(n, m, q, 0);
    }   
}
