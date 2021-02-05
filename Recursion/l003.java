import java.util.*;

public class l003 {

    // private static Scanner sc;

    public static int floodFill(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS, String ans){
        if(sr == er && sc == ec){
            System.out.println(ans);
            return 1;
        }

        vis[sr][sc] = true;
        int n = vis.length;
        int m = vis[0].length;
        int count = 0;
        for(int d = 0; d < dir.length; d++){
            int r = dir[d][0] + sr;
            int c = dir[d][1] + sc;
            // check if r and c are within boundary
            if(r >= 0 && c >=0 && r < n && c < m && !vis[r][c]){
                count += floodFill(r, c, er, ec, vis, dir, dirS, ans + dirS[d]);
            }
        }
        vis[sr][sc] = false;
        return count;
    }

    public static int floodFillJumps(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS, String ans){
        if(sr == er && sc == ec){
            System.out.println(ans);
            return 1;
        }

        vis[sr][sc] = true;
        int n = vis.length;
        int m = vis[0].length;
        int count = 0;
        for(int rad = 1; rad <= Math.max(n, m); rad++){
            for(int d = 0; d < dir.length; d++){
                int r = rad * dir[d][0] + sr;
                int c = rad * dir[d][1] + sc;
                // check if r and c are within boundary
                if(r >= 0 && c >=0 && r < n && c < m && !vis[r][c]){
                    count += floodFillJumps(r, c, er, ec, vis, dir, dirS, ans + dirS[d]);
                }
            }
        }
        vis[sr][sc] = false;
        return count;
    }

    
    // Geeks for Geeks: Rat in a Maze Problem 1
    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
    public static ArrayList<String> maze(int sr, int sc, int er, int ec, int[][] vis, int[][] dir, String[] dirS) {
        if(sr == er && sc == ec){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();
        if(vis[sr][sc] == 0) return myAns;
        vis[sr][sc] = 0;
        for(int i = 0; i < dir.length; i++){
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec && vis[r][c] == 1){
                ArrayList<String> smallAns = maze(r, c, er, ec, vis, dir, dirS);
                for(String s: smallAns) myAns.add(dirS[i] + s);
            }
        }
        vis[sr][sc] = 1;
        return myAns;
    }
        
    public static ArrayList<String> findPath(int[][] m, int n) {
        String [] dirS = {"D", "L", "R", "U"};
        int[][] dir = {{1,0}, {0,-1}, {0,1}, {-1,0}}; 
        ArrayList<String> res = maze(0, 0, n - 1, n - 1, m, dir, dirS);
        Collections.sort(res);
        return res;
    }

    // Knights tour on a chess board of size n - Pepcoding
    public static void displayBoard(int[][] chess) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int knightsTour(int sr, int sc, int n, int moves, int[][] chess, int[][] dir) {
        chess[sr][sc] = moves;
        if(moves == n * n){
            displayBoard(chess);
            chess[sr][sc] = 0;
            return 1;
        }
        int count = 0;
        for(int i = 0; i < dir.length; i++){
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if(r >= 0 && c >= 0 && r < n && c < n && chess[r][c] == 0) 
                count += knightsTour(r, c, n, moves + 1, chess, dir);
        }
        chess[sr][sc] = 0;
        return count;
    }

    // Main for Knights Tour
    /*public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int sr = scn.nextInt();
        int sc = scn.nextInt();
        int[][] dir = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        int[][] chess = new int[n][n];
        knightsTour(sr, sc, n, 1, chess, dir);
    }*/

    
    // Target sum problem
    // return count of ways to add / subtract elements of an array to find a target sum S
    public static int findTargetSumWays(int[] nums, int idx, int S) {
        if(idx == nums.length){
            return S == 0 ? 1 : 0;
        }
        int count = 0;
        count += findTargetSumWays(nums, idx + 1, S - nums[idx]);
        count += findTargetSumWays(nums, idx + 1, S + nums[idx]);
        return count;
    }
    

    public static void main(String[] args){
        // sc = new Scanner(System.in);
        // int n = sc.nextInt();
        String[] dirS = {"L", "D", "R", "U"};
        int[][] dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int n = 3, m = 3;
        boolean[][] vis = new boolean[n][m];
        System.out.println(floodFill(0, 0, n - 1, m - 1, vis, dir, dirS, ""));  
    }
}
