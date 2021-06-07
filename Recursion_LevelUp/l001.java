
public class l001{

    // May 13, 2021

    public static int floodFill(int sr, int sc, int er, int ec, int[][] dir, boolean[][] vis, String[] dirS, String ans){
        if(sr == er && sc == ec){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        int n = vis.length;
        int m = vis[0].length;
        vis[sr][sc] = true;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && r < n && c >= 0 && c < m && !vis[r][c]){
                count += floodFill(r, c, er, ec, dir, vis, dirS, ans + dirS[d]);
            }
        }
        vis[sr][sc] = false;
        return count;
    }

    public static class pathPair{
        int len = 0;
        String psf="";
        int count = 0;
        pathPair(int len, String psf, int count){
            this.len = len;
            this.psf = psf;
            this.count = count;
        }
    }

    public static pathPair floodFill_longestPath(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, boolean[][] vis){
        if(sr == er && sc == ec){
            return new pathPair(0, "", 1);
        }
        int n = vis.length;
        int m = vis[0].length;
        vis[sr][sc] = true;
        pathPair myAns = new pathPair(-(int)1e8, "", 0);
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && r < n && c >= 0 && c < m && !vis[r][c]){
                pathPair recAns = floodFill_longestPath(r, c, er, ec, dir, dirS, vis);
                if(recAns.len + 1 > myAns.len){
                    myAns.len = recAns.len + 1;
                    myAns.psf = dirS[d] + recAns.psf;
                }
                myAns.count += recAns.count;
            }
        }
        vis[sr][sc] = false;
        return myAns;
    }

    public static pathPair floodFill_shortestPath(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, boolean[][] vis){
        if(sr == er && sc == ec){
            return new pathPair(0, "", 1);
        }
        int n = vis.length;
        int m = vis[0].length;
        vis[sr][sc] = true;
        pathPair myAns = new pathPair((int)1e8, "", 0);
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r >= 0 && r < n && c >= 0 && c < m && !vis[r][c]){
                pathPair recAns = floodFill_shortestPath(r, c, er, ec, dir, dirS, vis);
                if(recAns.len + 1 < myAns.len){
                    myAns.len = recAns.len + 1;
                    myAns.psf = dirS[d] + recAns.psf;
                }
                myAns.count += recAns.count;
            }
        }
        vis[sr][sc] = false;
        return myAns;
    }

    public static void main(String[] args){
        int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,-1}, {-1,1}};
        String[] dirS = {"l", "r", "d", "u", "w", "s", "n", "e"};
        int n = 4, m = 4;
        boolean[][] vis = new boolean[n][m];
        int res = floodFill(0, 0, n-1, m-1, dir, vis, dirS, "");

    } 

}