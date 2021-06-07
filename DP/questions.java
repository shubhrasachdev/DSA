import java.util.Arrays;

public class questions {

    // May 2, 2021

    // Question 70: https://leetcode.com/problems/climbing-stairs/
    public static int climbStairs_memo(int n, int[] dp){
        if(n <= 1){
            return dp[n] = 1;
        }
        if(dp[n] != 0) return dp[n];
        int steps = climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
        return dp[n] = steps;
        
    }
    
    public static int climbStairs_DP(int N, int[] dp){
        for(int n = 0; n <= N; n++){
            if(n <= 1){
                dp[n] = 1;
                continue;
            }
            int steps = dp[n - 1] + dp[n - 2];
            dp[n] = steps;
        }
        return dp[N];       
    }
    
    public static int climbStairs_Opti(int n){
        int a = 1, b = 1;
        for(int i = 0; i < n; i++){
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }
       
    public static int climbStairs(int n) {
        // int[] dp = new int[n + 1];
        // return climbStairs_DP(n, dp);
        return climbStairs_Opti(n);
    }

    // Question 746: https://leetcode.com/problems/min-cost-climbing-stairs
    public static int minCostClimbingStairs_memo(int[] cost, int[] dp, int n){
        if(n <= 1) return dp[n] = cost[n];
        if(dp[n] != 0) return dp[n];
        int minCostOneStep = minCostClimbingStairs_memo(cost, dp, n - 1);
        int minCostTwoStep = minCostClimbingStairs_memo(cost, dp, n - 2);
        int currCost = Math.min(minCostOneStep, minCostTwoStep) + (n == cost.length ? 0 : cost[n]);
        return dp[n] = currCost;
    }
    
    public static int minCostClimbingStairs_DP(int[] cost, int[] dp, int N){
        for(int n = 0; n <= N; n++){
            if(n <= 1){
                dp[n] = cost[n];
                continue;
            }
            int currCost = Math.min(dp[n - 1], dp[n - 2]) + (n == cost.length ? 0 : cost[n]);
            dp[n] = currCost;
        }
        return dp[N];
    }
    
    public static int minCostClimbingStairs_Opti(int[] cost){
        int a = cost[0], b = cost[1], n = cost.length;
        for(int i = 2; i <= n; i++){
            int minVal = Math.min(a, b) + (i == n ? 0 : cost[i]);
            a = b;
            b = minVal;
        }
        return b;
    } 
    
    public int minCostClimbingStairs(int[] cost) {
        // int n = cost.length;
        // int[] dp = new int[n + 1];
        return minCostClimbingStairs_Opti(cost);
    }

    // Leetcode 62: https://leetcode.com/problems/unique-paths/
    //maze path with down and right movement
    public static int mazePathHV_DP(int SR, int SC, int er, int ec, int[][] dir, int[][] dp){
        for(int sr = er; sr >= 0; sr--){
            for(int sc = ec; sc >= 0; sc--){
                if(sr == er && sc == ec){
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for(int i = 0; i < dir.length; i++){
                    int r = sr + dir[i][0];
                    int c = sc + dir[i][1];
                    if(r >= 0 && c >= 0 && r <= er && c <= ec)
                        count += dp[r][c];
                }
                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }
    
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        int[][] dir = new int[][]{{1,0}, {0,1}};
        return mazePathHV_DP(0, 0, m - 1, n - 1, dir, dp);       
    }
    
    // Leetcode 63: https://leetcode.com/problems/unique-paths-ii
    // Maze path with down and right movements, and an obstacle grid with 1 = obstacle and 0 = free space
    public static int mazePathHV_DP(int SR, int SC, int er, int ec, int[][] dir, int[][] dp, int[][] obstacleGrid){
        for(int sr = er; sr >= 0; sr--){
            for(int sc = ec; sc >= 0; sc--){
                if(sr == er && sc == ec){
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for(int i = 0; i < dir.length; i++){
                    int r = sr + dir[i][0];
                    int c = sc + dir[i][1];
                    if(r >= 0 && c >= 0 && r <= er && c <= ec && obstacleGrid[r][c] == 0) count += dp[r][c];
                }
                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }
        
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        int[][] dir = new int[][]{{1,0}, {0,1}};
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) return 0;
        return mazePathHV_DP(0, 0, m - 1, n - 1, dir, dp, obstacleGrid); 
        
    }

    // Leetcode 64: https://leetcode.com/problems/minimum-path-sum/
    // Find the minimum cost path between top-left and bottom-right cells of a grid, given cost of each cell 
    public int minPathSum_memo(int sr, int sc, int er, int ec, int[][] grid, int[][] dp, int[][] dir){
        if(sr == er && sc == ec){
            return dp[sr][sc] = grid[sr][sc];
        }
        if(dp[sr][sc] != 0) return dp[sr][sc];
        int cost = Integer.MAX_VALUE;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec) {
                int curr = minPathSum_memo(r, c, er, ec, grid, dp, dir);
                cost = Math.min(cost, curr + grid[sr][sc]);                
            }
        }
        return dp[sr][sc] = cost;
        
    }
    
    public int minPathSum_DP(int SR, int SC, int er, int ec, int[][] grid, int[][] dp, int[][] dir){
        for(int sr = er; sr >= 0; sr--){
            for(int sc = ec; sc >= 0; sc--){
                    if(sr == er && sc == ec){
                        dp[sr][sc] = grid[sr][sc];
                        continue;
                    }
                    int cost = Integer.MAX_VALUE;
                    for(int d = 0; d < dir.length; d++){
                        int r = sr + dir[d][0];
                        int c = sc + dir[d][1];
                        if(r >= 0 && c >= 0 && r <= er && c <= ec) cost = Math.min(cost, dp[r][c] + grid[sr][sc]);
                    }
                    dp[sr][sc] = cost;
            }
        }
        return dp[SR][SC];        
    }
    
    public int minPathSum(int[][] grid) {
        int[][] dir = new int[][]{{1, 0}, {0, 1}};
        int er = grid.length - 1;
        int ec = grid[0].length - 1;
        int[][] dp = new int[er + 1][ec + 1];
        return minPathSum_DP(0, 0, er, ec, grid, dp, dir);
        
    }

    // May 6, 2021

    // Friends Pairing (portal)
    public static int friendsPairing_memo(int n, int[] dp){
        if(n == 0 || n == 1){
            return dp[n] = 1;
        }
        if(dp[n] != 0) return dp[n];
        int single = friendsPairing_memo(n - 1, dp);
        int pairUp =  (n - 1) * friendsPairing_memo(n - 2, dp);
        return dp[n] = single + pairUp;
    }

    public static int friendsPairing_DP(int N, int[] dp){
        for(int n = 0; n <= N; n++){       
            if(n == 0 || n == 1){
                dp[n] = 1;
                continue;
            }
            int single = dp[n - 1];
            int pairUp = (n - 1) * dp[n - 2];
            dp[n] = single + pairUp;
        }
        return dp[N];
    }

    public static int friendsPairing_Opti(int n){
        int a = 1, b = 1;
        for(int i = 2; i <= n; i++){
            int res = b + (i - 1) * a;
            a = b;
            b = res;
        }
        return b;
    }

    // Goldmine Problem (portal, geeks for geeks): https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1
    public static int goldMine_memo(int r, int c, int[][] dir, int[][] gold, int[][] dp){
        int n = gold.length - 1, m = gold[0].length - 1;
        if(c == m){
            return dp[r][c] = gold[r][c];
        }
        if(dp[r][c] != -1) return dp[r][c];
        int maxGold = Integer.MIN_VALUE; 
        for(int d = 0; d < dir.length; d++){
            int x = r + dir[d][0];
            int y = c + dir[d][1];
            if(x >= 0 && y >= 0 && x <= n && y <= m){
                int curr = goldMine_memo(x, y, dir, gold, dp);
                maxGold = Math.max(maxGold, curr + gold[r][c]);
            }
        }
        return dp[r][c] = maxGold;
    }

    public static int goldMine_memo(int[][] gold){ // caller for memo function
        int n = gold.length, m = gold[0].length;
        int[][] dp = new int[n][m];
        for(int[] d : dp) Arrays.fill(d, -1);
        int[][] dir = new int[][]{{0,1}, {1,1}, {-1, 1}};
        int maxGold = 0;
        for(int r = 0; r < n; r++){
            maxGold =  Math.max(maxGold, goldMine_memo(r, 0, dir, gold, dp));
        }
        return maxGold;
    }

    public static int goldMine_DP(int[][] gold){
        int n = gold.length - 1, m = gold[0].length - 1;
        int[][] dp = new int[n + 1][m + 1];
        for(int[] d : dp) Arrays.fill(d, -1);
        int[][] dir = new int[][]{{0,1}, {1,1}, {-1, 1}};
        for(int c = m; c >= 0; c--){
            for(int r = n; r >= 0; r--){
                if(c == m){
                    dp[r][c] = gold[r][c];
                    continue;
                }
                int maxGold = Integer.MIN_VALUE; 
                for(int d = 0; d < dir.length; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    if(x >= 0 && y >= 0 && x <= n && y <= m){
                        maxGold = Math.max(maxGold, dp[x][y] + gold[r][c]);
                    }
                }
                dp[r][c] = maxGold;
            }
        }
        int ans = -1;
        for (int i = 0; i <= n; i++) {
            ans = Math.max(ans, dp[i][0]);
        }
        return ans;
    }

    

    

    public static void main(String[] args){
        System.out.println(friendsPairing_Opti(4));
    }
}
