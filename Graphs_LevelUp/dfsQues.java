import java.util.*;

public class dfsQues {
    
    // Leetcode 200 - https://leetcode.com/problems/number-of-islands/
    public void dfs_numIslands(char[][] grid, int sr, int sc, int[][] dir){
        grid[sr][sc] = '2';
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == '1') dfs_numIslands(grid, r, c, dir);
        }
    }
    
    public int numIslands(char[][] grid) {
        int components = 0;
        int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                if(grid[r][c] == '1') {
                    components++;
                    dfs_numIslands(grid, r, c, dir);
                }
            }
        }

        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                if(grid[r][c] == '2') grid[r][c] = '1';
            }
        }
        return components;
    }

    // leetcode 695 - https://leetcode.com/problems/max-area-of-island/
    public int dfs_maxArea(int[][] grid, int sr, int sc, int[][] dir){
        int area = 0;
        grid[sr][sc] = 0;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) 
                area += dfs_maxArea(grid, r, c, dir);
        }
        return area + 1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int maxSize = 0;
        int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    int s = dfs_maxArea(grid, i, j, dir);
                    maxSize = Math.max(maxSize, s);
                }
            }
        }
        return maxSize;
    }

    // Leetcode 463 - https://leetcode.com/problems/island-perimeter/
    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dir = {{0, 1}, {1, 0}};
        int onesCount = 0, nbrCount = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if (grid[i][j] == 1){
                    onesCount++;
                    for(int d = 0; d < dir.length; d++){
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if(r < n && c < m && grid[r][c] == 1) nbrCount++;
                    }
                }
            }
        }
        return onesCount * 4 - nbrCount * 2;        
    }

    // Leetcode 130 - https://leetcode.com/problems/surrounded-regions/
    public void dfs_surrounded(char[][] board, int i, int j, int[][] dir){
        board[i][j] = '$';
        for(int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 'O') dfs_surrounded(board, r, c, dir); 
        }
    }
    public void solve(char[][] board) {
        int n = board.length, m = board[0].length;
        int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if((i == 0 || i == n - 1 || j == 0 || j == m - 1) && board[i][j] == 'O'){
                    dfs_surrounded(board, i, j, dir);

                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == '$') board[i][j] = 'O';
                else board[i][j] = 'X';
            }
        }
        
    }

    // Journey to the moon
    public static int dfs_journeyToMoon(ArrayList<Integer>[] graph, int src, boolean[] vis){
        int sz = 1;
        vis[src] = true;
        for(int e: graph[src]){
            if(!vis[e]) sz += dfs_journeyToMoon(graph, e, vis);
        }
        return sz;
    }

    public static long journeyToMoon(int n, List<List<Integer>> astronaut){
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for(List<Integer> pair: astronaut) {
            graph[pair.get(0)].add(pair.get(1));
            graph[pair.get(1)].add(pair.get(0));
        }
        ArrayList<Integer> numAstronauts = new ArrayList<>();
        boolean[] vis = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!vis[i]) numAstronauts.add(dfs_journeyToMoon(graph, i, vis));
        }
        long ssf = 0, res = 0;
        for (int ele : numAstronauts) {
            res += ele * ssf;
            ssf += ele;
        }

        return res;
    }



    
}
