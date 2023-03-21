import java.util.*;
public class l002_StringSet {

    // Aug 14, 2021

    // Longest Palindromic Subsequence
    // Leetcode 516 - https://leetcode.com/problems/longest-palindromic-subsequence/
    
    // Memoization
    public int lpss(String s, int si, int ei, int[][] dp) {
        if(si >= ei) {
            return dp[si][ei] = (si == ei) ? 1 : 0;
        }
        if(dp[si][ei] != -1) return dp[si][ei];
        if(s.charAt(si) != s.charAt(ei))
            dp[si][ei] = Math.max(lpss(s, si + 1, ei, dp), lpss(s, si, ei - 1, dp));
        else dp[si][ei] = lpss(s, si + 1, ei - 1, dp) + 2;
        return dp[si][ei];
    }

    // Tabulation
    public int lpss_tabu(String s, int[][] dp) { // gap strategy
        int n = s.length();
        for(int gap = 0; gap < n; gap++) {
            for(int si = 0, ei = gap; ei < n; si++, ei++) {
                if(si >= ei) {
                    dp[si][ei] = (si == ei) ? 1 : 0;
                }
                else if(s.charAt(si) != s.charAt(ei)) dp[si][ei] = Math.max(dp[si + 1][ei], dp[si][ei - 1]);
                else dp[si][ei] = dp[si + 1][ei - 1] + 2;
            }
        }
        return dp[0][n - 1];
        
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int[] d: dp) Arrays.fill(d, -1);
        return lpss(s, 0, n - 1, dp);
    }

    // Longest Common Subsequence
    // Leetcode 1143 - https://leetcode.com/problems/longest-common-subsequence/

    // HW 
    // Longest Palindromic Substring
    // Leetcode 5 - https://leetcode.com/problems/longest-palindromic-substring/
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int si = 0, len = 0;
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                if(gap == 0) dp[i][j] = true;
                else if(gap == 1 && s.charAt(i) == s.charAt(j)) dp[i][j] = true;
                else dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1]; 
                if(dp[i][j] && j - i + 1 > len) {
                    len = j - i + 1;
                    si = i;
                }
            }
        }
        return s.substring(si, si + len);
    }


    // Longest Common Substring
    // https://practice.geeksforgeeks.org/problems/longest-common-substring1452/1



    // August 19, 2021
    // leetcode 1458
    public static int maximum(int... arr) {
        int max = arr[0];
        for(int ele: arr) max = Math.max(max, ele);
        return max;
    }
    
    public int maxDotProduct(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if(n == 0 || m == 0) {
            return dp[n][m] = -(int)1e8;
        }
        if(dp[n][m] != -(int)1e9) return dp[n][m];
        
        int val = nums1[n - 1] * nums2[m - 1];
        int acceptTwoNums = maxDotProduct(nums1, nums2, n - 1, m - 1, dp) + val;
        int a = maxDotProduct(nums1, nums2, n, m - 1, dp);
        int b = maxDotProduct(nums1, nums2, n - 1, m, dp);
        return dp[n][m] = maximum(acceptTwoNums, val, a, b);
    }
    
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        for(int[] d: dp) Arrays.fill(d, -(int)1e9);
        return maxDotProduct(nums1, nums2, n, m, dp);
    }

    // Leetcode 1035 - https://leetcode.com/problems/uncrossed-lines/
    public int maxUncrossedLines(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if(n == 0 || m == 0) {
            return dp[n][m] = 0;
        }
        if(dp[n][m] != -1) return dp[n][m];

        if(nums1[n - 1] == nums2[m - 1]) return dp[n][m] = maxUncrossedLines(nums1, nums2, n - 1, m - 1, dp) + 1;

        return dp[n][m] = Math.max(maxUncrossedLines(nums1, nums2, n - 1, m, dp), maxUncrossedLines(nums1, nums2, n, m - 1, dp));
    }
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        for(int[] d: dp) Arrays.fill(d, -1);
        return maxUncrossedLines(nums1, nums2, n, m, dp);
        
    }

    // Leetcode 72 - https://leetcode.com/problems/edit-distance/
    public int minDistance(String word1, String word2, int n, int m, int[][] dp) {
        if(n == 0) return dp[n][m] = m; // insert
        if(m == 0) return dp[n][m] = n; // delete
        
        if(dp[n][m] != -1) return dp[n][m];

        if(word1.charAt(n - 1) == word2.charAt(m - 1)) return dp[n][m] = minDistance(word1, word2, n - 1, m - 1, dp);
        int insert = minDistance(word1, word2, n, m - 1, dp);
        int delete = minDistance(word1, word2, n - 1, m, dp);
        int update = minDistance(word1, word2, n - 1, m - 1, dp);
        return dp[n][m] = Math.min(update, Math.min(insert, delete));
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for(int[] d: dp) Arrays.fill(d, -1);
        return minDistance(word1, word2, n, m, dp) + 1;
    }

    // 583
    public static int lcss(String word1, String word2, int n, int m, int[][] dp) {
        return 0;
    }
    public int minDistance583(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        return n + m - 2 * lcss(word1, word2, n, m, dp);
        
    }
    // gfg min num of deletions and insertions - similar to above

    // Leetcode 115 - https://leetcode.com/problems/distinct-subsequences/
    public int numDistinct_memo(String s, String t, int n, int m, int[][] dp) {
        if(m == 0 || n < m) return dp[n][m] = (m == 0 ? 1 : 0); 
        if(dp[n][m] != -1) return dp[n][m]; 
        int a = numDistinct_memo(s, t, n - 1, m - 1, dp);
        int b = numDistinct_memo(s, t, n - 1, m, dp);
        if(s.charAt(n - 1) == t.charAt(m - 1)) return dp[n][m] = a + b;
        return dp[n][m] = b;
        
    }

    public int numDistinct_DP(String s, String t, int N, int M, int[][] dp) {
        for(int n = 0; n <= N; n++) {
            for(int m = 0; m <= M; m++) {
                if(m == 0 || n < m) {
                    dp[n][m] = (m == 0 ? 1 : 0);
                    continue;
                } 
                int a = dp[n - 1][m - 1];
                int b = dp[n - 1][m];
                if(s.charAt(n - 1) == t.charAt(m - 1)) dp[n][m] = a + b;
                else dp[n][m] = b;
            }
        }
        return dp[N][M];
    }

    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for(int[] d: dp) Arrays.fill(d, -1);
        // return numDistinct_memo(s, t, n, m, dp);
        return numDistinct_DP(s, t, n, m, dp);
    }

    // 940 - direct tabulation

}
