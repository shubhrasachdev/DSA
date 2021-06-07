public class l002StringSet {
    // LPSS-longestPalindromicSubsequence 
    // https://leetcode.com/problems/longest-palindromic-subsequence/
    public static int LPSS(String str, int i, int j, int[][] dp){
        if(i >= j){
            return dp[i][j] = ((i == j) ? 1 : 0);
        }
        if(dp[i][j] != -1) return dp[i][j];
        if(str.charAt(i) == str.charAt(j))
            return dp[i][j] = LPSS(str, i + 1, j - 1, dp) + 2;
        return dp[i][j] = Math.max(LPSS(str, i, j -1, dp), LPSS(str, i + 1, j, dp));
    }

    public static void longestPalindromicSubsequence(){
        String str = "geeksforgeek";
        int n = str.length();
        int[][] dp = new int[n][n];
        System.out.println(LPSS(str, 0, n - 1, dp));


    }

    // LCSS: Longest common subsequence
    public static int LCSS(String s1, String s2, int n, int m, int[][] dp){
        if(n == 0 || m == 0) return dp[n][m] = 0;
        if(s1.charAt(n - 1) == s2.charAt(m - 1)) 
            return dp[n][m] = LCSS(s1, s2, n-1, m-1, dp);
        return dp[n][m] = Math.max(LCSS(s1, s2, n - 1, m, dp), LCSS(s1, s2, n, m - 1, dp));

    }

    public static void longestCommonSubsequence(){}
}
