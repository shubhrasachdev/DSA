import java.util.ArrayList;

import java.util.*;
public class Amex {
    int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
    public int dfs(int[][] grid, int sr, int sc, int er, int ec, int k) {
        if(sr == er && sc == ec) return 1;
        int val = grid[sr][sc];
        grid[sr][sc] = 2;
        int curr = Integer.MAX_VALUE;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] != 2) curr = Math.min(curr, dfs(grid, r, c, er, ec, k));
        }
        grid[sr][sc] = val;
        return curr == Integer.MAX_VALUE ? curr : curr + 1;
    }

    public int solution(int k, int[][] grid) {
        ArrayList<Integer> houses = new ArrayList<>();
        int n = grid.length, m =  grid[0].length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) houses.add(i * m + j);
            }
        }
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                boolean flag = true;
                if(grid[i][j] == 0) {
                    for(int house: houses) {
                        int r = house / m, c = house % m;
                        int cost = dfs(grid, i, j, r, c, k);
                        if(cost == Integer.MAX_VALUE) {
                            flag = false;
                            break;
                        }
                    }
                    if(flag) count++;
                }

            }
        }
        return count;
    }
    
    public static String solution3(String str) {
        String lines[] = str.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < lines.length; i++) {
            String[] words = lines[i].split(",");
            boolean flag = true;
            for(String word: words) {
                if(word.equals("NULL")) {
                    flag = false;
                    break;
                }
            }
            if(flag) sb.append(lines[i]+ '\n');
            
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    public static void main(String[] args) {
        
    }
}
