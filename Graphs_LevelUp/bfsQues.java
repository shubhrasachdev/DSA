import java.util.*;
public class bfsQues {
    // Leetcode 994 - https://leetcode.com/problems/rotting-oranges/
    public int orangesRotting(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int freshOranges = 0, time = 0, n = grid.length, m = grid[0].length;
        int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 2) {
                    que.addLast(i * m + j);
                    grid[i][j] = 2; // mark visited
                }
                else if(grid[i][j] == 1) freshOranges++;
            }
        }
        if(freshOranges == 0) return 0;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                        if(--freshOranges == 0) return time + 1;
                        grid[r][c] = 2;
                        que.addLast(r * m + c);
                    }

                }
            }
            time++;
        }
        return -1;   
    }
    
}
