import java.util.Arrays;

public class twoPointer {
    // 1. Faith
    // 2. Recursive Tree
    // 3. Recursion Code
    // 4. Convert recursion code to memoization
    // 5. Observation
    // 6. Tabulation
    // 7. Optimization

    // August 8, 2021

    // Fibonacci
    public static void display(int[] arr) {
        for(int ele: arr) System.out.print(ele + " ");
        System.out.println();
    }
    
    public static void display(int[][] arr) {
        for(int[] a : arr) display(a);
        System.out.println();
    }

    public static int fibo_memo(int n, int[] dp) {
        if(n <= 1) return dp[n] = n;
        if(dp[n] != 0) return dp[n];
        int ans = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
        return dp[n] = ans;
    }

    public static int fibo_tabu(int N, int[] dp) {
        for(int n = 0; n <= N; n++) {
            if(n <= 1){
                dp[n] = n;
                continue;

            } 
            int ans = dp[n - 1] + dp[n - 2];
            dp[n] = ans;
        }
        return dp[N];
    }

    public static int fibo_opti(int n) {
        int a = 0, b = 1;
        for(int i = 0; i < n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    public static int mazePathHDV(int sr, int sc, int er, int ec, int[][] dir) {
        if(sr == er && sc == ec) return 1;
        int ans = 0;
        for(int[] d: dir) {
            int r = sr + d[0], c = sc + d[1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec) {
                ans += mazePathHDV(r, c, er, ec, dir);
            }
        }
        return ans;
    }

    public static int mazePathHDV_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
        if(sr == er && sc == ec) return dp[sr][sc] = 1;
        if(dp[sr][sc] != 0) return dp[sr][sc];
        int ans = 0;
        for(int[] d: dir) {
            int r = sr + d[0], c = sc + d[1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec) {
                ans += mazePathHDV_memo(r, c, er, ec, dir, dp);
            }
        }
        return dp[sr][sc] = ans;
    }

    public static int mazePathHDV_tabu(int SR, int SC, int ER, int EC, int[][] dir, int[][] dp) {
        for(int sr = ER; sr >= SR; sr--) {
            for(int sc = EC; sc >= SC; sc--) {
                if(sr == ER && sc == EC) {
                    dp[sr][sc] = 1;
                    continue;
                }
                int ans = 0;
                for(int[] d: dir) {
                    int r = sr + d[0], c = sc + d[1];
                    if(r >= 0 && c >= 0 && r <= ER && c <= EC) {
                        ans += dp[r][c];
                    }
                }
                dp[sr][sc] = ans;
            }
        }
        return dp[SR][SC];
    }

    public static int mazePathHDVJumps_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
        if(sr == er && sc == ec) return dp[sr][sc] = 1;
        if(dp[sr][sc] != 0) return dp[sr][sc];
        int ans = 0;
        for(int[] d: dir) {
            int r = sr + d[0], c = sc + d[1];
            while(r >= 0 && c >= 0 && r <= er && c <= ec) {
                ans += mazePathHDV_memo(r, c, er, ec, dir, dp);
                r += d[0];
                c += d[1];
            }
        }
        return dp[sr][sc] = ans;
    }

    public static int mazePathHDVJumps_tabu(int SR, int SC, int ER, int EC, int[][] dir, int[][] dp) {
        for(int sr = ER; sr >= SR; sr--) {
            for(int sc = EC; sc >= SC; sc--) {
                if(sr == ER && sc == EC) {
                    dp[sr][sc] = 1;
                    continue;
                }
                int ans = 0;
                for(int[] d: dir) {
                    int r = sr + d[0], c = sc + d[1];
                    while(r >= 0 && c >= 0 && r <= ER && c <= EC) {
                        ans += dp[r][c];
                        r += d[0];
                        c += d[1];
                    }
                }
                dp[sr][sc] = ans;
            }
        }
        return dp[SR][SC];
    }

    public static void mazePath_Set() {
        int sr = 0, sc = 0, er = 3, ec = 3;
        int[][] dp = new int[er + 1][ec + 1];
        int[][] dir = {{0,1}, {1,0}, {1,1}};
        System.out.println(mazePathHDV_memo(sr, sc, er, ec, dir, dp));
        display(dp);
    }

    // https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/

    public static int maxGold(int[][] grid, int n, int m, int sr, int sc, int[][] dir){
        if(sc == m - 1) return grid[sr][sc];
        int curr = grid[sr][sc];
        int gold = 0;
        grid[sr][sc] = 0;
        for(int[] d: dir) {
            int r = sr + d[0], c = sc + d[1];
            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] != 0) gold = Math.max(gold, maxGold(grid, n, m, r, c, dir));
        }
        grid[sr][sc] = curr;
        return gold + curr;
    }
    
    public static int maxGold_memo(int[][] grid, int n, int m, int sr, int sc, int[][] dir, int[][] dp){
        if(sc == m - 1) return dp[sr][sc] = grid[sr][sc];
        if(dp[sr][sc] != -1) return dp[sr][sc];
        int gold = -1;
        for(int[] d: dir) {
            int r = sr + d[0], c = sc + d[1];
            if(r >= 0 && c >= 0 && r < n && c < m) gold = Math.max(gold, maxGold_memo(grid, n, m, r, c, dir, dp));
        }
        return dp[sr][sc] = gold + grid[sr][sc];
    }

    public static int maxGold_tabu(int[][] grid, int n, int m, int SR, int SC){
        int[][] dir = {{0,1}, {1,1}, {-1,1}};
        int[][] dp = new int[n][m];
        for(int sc = m - 1; sc >= SC; sc--) {
            for(int sr = n - 1; sr >= SR; sr--) {
                if(sc == m - 1) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }
                int gold = 0;
                for(int[] d: dir) {
                    int r = sr + d[0], c = sc + d[1];
                    if(r >= 0 && c >= 0 && r < n && c < m) gold = Math.max(gold, dp[r][c]);
                }
                dp[sr][sc] = gold + grid[sr][sc];
            }
        }
        int maxGold = 0;
        for(int r  = SR; r < n; r++) maxGold = Math.max(maxGold, dp[r][0]);
        return maxGold;
    }

    public static int maxGold(int n, int m, int M[][]){
        return maxGold_tabu(M, n, m, 0, 0);
    }

    // Leetcode 70 - https://leetcode.com/problems/climbing-stairs/ 
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

    // Leetcode 746 - https://leetcode.com/problems/min-cost-climbing-stairs/
    public int minCost_memo(int n, int[] cost, int[] dp) {
        if(n <= 1) return dp[n] = cost[n];
        if(dp[n] != 0) return dp[n];
        int minCost = Math.min(minCost_memo(n - 1, cost, dp), minCost_memo(n - 2, cost, dp));
        return minCost + (n == cost.length ? 0 : cost[n]);
    }

    public int minCost_tabu(int N, int[] cost, int[] dp) {
        for(int n = 0; n <= N; n++) {
            if(n <= 1) {
                dp[n] = cost[n];
                continue;
            }
            int minCost = Math.min(dp[n - 1], dp[n - 2]);
            return dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
        }
        return dp[N];
    }

    public static int minCost_Opti(int[] cost) {
        int a = cost[0], b = cost[1];
        for(int n = 2; n <= cost.length; n++) {
            int minCost = Math.min(a, b) + (n == cost.length ? 0 : cost[n]);
            a = b;
            b = minCost;
        }
        return b;
    }

    // Board Path with dice (moves will be 1-6)
    public static int boardPath_memo(int sp, int ep, int[] dp) {
        if(sp == ep) return dp[sp] = 1;
        if(dp[sp] != 0) return dp[sp];
        int count = 0;
        for(int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
            count += boardPath_memo(sp + dice, ep, dp);
        }
        return dp[sp] = count;
    }

    public static int boardPath_tabu(int SP, int ep, int[] dp) {
        for(int sp = ep; sp >= SP; sp--) {
            if(sp == ep) {
                dp[sp] = 1;
                continue;
            }
            int count = 0;
            for(int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
                count += dp[sp + dice];
            }
            dp[sp] = count;
        }
        return dp[SP];
    }


    // August 12, 2021 

    // Leetcode 91 - https://leetcode.com/problems/decode-ways/
    public int numDecodings_memo(String s, int n, int[] dp) {
        if(n == 0) return dp[n] = 1;
        if(dp[n] != -1) return dp[n];
        int count = 0;
        if(s.charAt(n - 1) > '0') count += numDecodings_memo(s, n - 1, dp);
        if(n > 1) {
            int num = (s.charAt(n - 2) - '0') * 10 + (s.charAt(n - 1) - '0');
            if(num <= 26 && num >= 10) count += numDecodings_memo(s, n - 2, dp);
        }
        return dp[n] = count;
    }

    public int numDecodings_memo2(String s, int idx, int[] dp) {
        if (idx == s.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1)
            return dp[idx];

        if (s.charAt(idx) == '0') {
            return dp[idx] = 0;
        }

        int count = 0;
        count += numDecodings_memo2(s, idx + 1, dp);

        if (idx < s.length() - 1) {
            int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
            if (num <= 26)
                count += numDecodings_memo2(s, idx + 2, dp);
        }

        return dp[idx] = count;
    }

    public int numDecodings_tabu(String s, int IDX, int[] dp) {
        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            if (s.charAt(idx) == '0') {
                dp[idx] = 0;
                continue;
            }

            int count = 0;
            count += dp[idx + 1];

            if (idx < s.length() - 1) {
                int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
                if (num <= 26)
                    count += dp[idx + 2];
            }

            dp[idx] = count;
        }

        return dp[IDX];
    }

    // Leetcode 639 - https://leetcode.com/problems/decode-ways-ii/
    // Memoization
    public long numDecodingStar(String s, int idx, long[] dp) {
        if(idx == s.length()) return dp[idx] = 1;
        if(dp[idx] != -1) return dp[idx];
        if(s.charAt(idx) == '0') return dp[idx] = 0;
        int mod = (int)1e9 + 7;
        long count = 0;
        char ch1 = s.charAt(idx);
        if(ch1 == '*') {
            count = (count + 9 * numDecodingStar(s, idx + 1, dp)) % mod;
            if(idx < s.length() - 1) {
                if(s.charAt(idx + 1) == '*') count = (count + 15 * numDecodingStar(s, idx + 2, dp)) % mod;
                else
                    if(s.charAt(idx + 1) >= '0' && s.charAt(idx + 1) <= '6') count = (count + 2 * numDecodingStar(s, idx + 2, dp)) % mod;
                    else count = (count + 1 * numDecodingStar(s, idx + 2, dp)) % mod;   
            }
        } else {
            count = (count + numDecodingStar(s, idx + 1, dp)) % mod;
            if(idx < s.length() - 1) {
                if(s.charAt(idx + 1) != '*') {
                    int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
                    if(num <= 26) count = (count + 1 * numDecodingStar(s, idx + 2, dp)) % mod;
                }
                else 
                    if(ch1 == '1') count = (count + 9 * numDecodingStar(s, idx + 2, dp)) % mod;
                    else if (ch1 == '2') count = (count + 6 * numDecodingStar(s, idx + 2, dp)) % mod;
            }
        }
        return dp[idx] = count % mod;
    }

    // tabulation
    public long numDecodingStar_tabu(String s, long[] dp) {
        for(int idx = s.length(); idx >= 0; idx--){
            if(idx == s.length()) {
                dp[idx] = 1;
                continue;
            }
            
            if(s.charAt(idx) == '0') {
                dp[idx] = 0;
                continue;
            }
            
            int mod = (int)1e9 + 7;
            long count = 0;
            char ch1 = s.charAt(idx);
            
            if(ch1 == '*') {
                count = (count + 9 * dp[idx + 1]) % mod;
                if(idx < s.length() - 1) {
                    if(s.charAt(idx + 1) == '*') count = (count + 15 * dp[idx + 2]) % mod;
                    else
                        if(s.charAt(idx + 1) >= '0' && s.charAt(idx + 1) <= '6') count = (count + 2 * dp[idx + 2]) % mod;
                        else count = (count + 1 * dp[idx + 2]) % mod;   
                }
            } else {
                count = (count + numDecodingStar(s, idx + 1, dp)) % mod;
                if(idx < s.length() - 1) {
                    if(s.charAt(idx + 1) != '*') {
                        int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
                        if(num <= 26) count = (count + 1 * dp[idx + 2]) % mod;
                    }
                    else 
                        if(ch1 == '1') count = (count + 9 * dp[idx + 2]) % mod;
                        else if (ch1 == '2') count = (count + 6 * dp[idx + 2]) % mod;
                }
            }
            dp[idx] = count % mod;
        }
        return dp[0];
    }

    // Optimized - two pointer method
    public int numDecodingStar_Opti(String s) {
        return 0;
    }

    // Friends Pairing GFG (all 3 methods)

    // Divide N in K groups incrementally GFG (K paritioning question from foundation)
    public static int divideInkGroup(int n, int k, int[][] dp) {
         if(k == 1 || n == k) return dp[n][k] = 1;
         if(dp[n][k] != -1) return dp[n][k];
        int selfSet = divideInkGroup(n - 1, k - 1, dp);
        int anotherSet = divideInkGroup(n - 1, k, dp) * k;
        return dp[n][k] = selfSet + anotherSet;
    }

    public static void divideInkGroup() {
        int n = 5, k = 3;
        int[][] dp = new int[n + 1][k + 1];
        for(int[] d: dp) Arrays.fill(d, -1);
        System.out.println(divideInkGroup(n, k, dp));
    }

    public static int divideInkGroup_tabu(int N, int K, int[][] dp){
        for(int n = 1; n <= N; n++) {
            for(int k = 1; k <= K; k++) {
                if(k > n) break;
                if(k == 1 || n == k) {
                    dp[n][k] = 1;
                    break;
                }
                int selfSet = dp[n - 1][k - 1];
                int anotherSet = dp[n - 1][k] * k;
                dp[n][k] = selfSet + anotherSet;
            }
        }
        return dp[N][K];
    }





    


}