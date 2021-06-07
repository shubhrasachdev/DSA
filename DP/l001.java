import java.util.LinkedList;

public class l001{
    // May 1, 2021
    public static void print(int[] arr){
        for(int ele: arr) System.out.print(ele + " ");
        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] a: arr) print(a);
        System.out.println();
    }
    public static int fibo_memo(int n, int[] dp){
        if(n <= 1){
            return dp[n] = n;
        }
        if(dp[n] != 0) return dp[n];
        int ans = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
        return dp[n] = ans;
    }

    public static int fibo_DP(int N, int[] dp){
        for(int n = 0; n <= N; n++){
            if(n <= 1){
                dp[n] = n;
                continue;
            }
            int ans = dp[n - 1] + dp[n - 2];
            dp[n] = ans;
       }
       return dp[N];
    }

    public static int fibo_Opti(int n){
        int a = 0, b = 1;
        for(int i = 0; i < n; i++){
            System.out.print(a + " ");
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    public static void fibo(){
        int n = 6;
        int[] dp = new int[n + 1];
        // fibo_DP(n, dp);
        // print(dp);
        System.out.println(fibo_Opti(n));
    }

    // May 2, 2021

    // A dice with plays 1,2,3,4,5,6. Number of ways to reach 'n'
    public static int boardPath_memo(int n, int[] dp){
        if(n == 0){
            return dp[n] = 1;
        }
        if(dp[n] != 0) return dp[n];
        int count = 0;
        for(int dice = 1; dice <= 6 && n - dice >= 0; dice++){
            count += boardPath_memo(n - dice, dp);
        }
        return dp[n] = count;
    }

    public static int boardPath_DP(int N, int[] dp){
        for(int n = 0; n <= N; n++){
            if(n == 0){
                dp[n] = 1;
                continue;
            }
            int count = 0;
            for(int dice = 1; dice <= 6 && n - dice >= 0; dice++){
                count += dp[n - dice];
            }
            dp[n] = count;
        }
        return dp[N];
    }

    public static int boardPath_Opti(int n){
        LinkedList<Integer> ll = new LinkedList<>();
        ll.addLast(1);
        ll.addLast(1);
        for(int i = 2; i <= n; i++){
            if(i <= 6){
                ll.addLast(ll.getLast() * 2);
                continue;
            }
            ll.addLast(ll.getLast() * 2 - ll.removeFirst());
        }
        return ll.getLast();
    }

    public static void boardPath(){
        int n = 10;
        // int[] dp = new int[n + 1];
        System.out.println(boardPath_Opti(n));
    }

    // Get number of maze paths from source to destination
    public static int mazePath_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp){
        if(sr == er && sc == ec){
            return dp[sr][sc] = 1;
        }
        if(dp[sr][sc] != 0) return dp[sr][sc];
        int count = 0;
        for(int i = 0; i < dir.length; i++){
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec)
                count += mazePath_memo(r, c, er, ec, dir, dp);
        }
        return dp[sr][sc] = count;
    }

    public static int mazePath_DP(int SR, int SC, int er, int ec, int[][] dir, int[][] dp){
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

    public static int mazePathJumps_memo(int sr, int sc, int er, int ec, int[][] dir, int[][] dp){
        if(sr == er && sc == ec){
            return dp[sr][sc] = 1;
        }
        if(dp[sr][sc] != 0) return dp[sr][sc];
        int count = 0;
        for(int i = 0; i < dir.length; i++){
            for(int rad = 1; rad <= Math.max(er, ec); rad++){
                int r = sr + rad * dir[i][0];
                int c = sc + rad * dir[i][1];
                if(r >= 0 && c >= 0 && r <= er && c <= ec)
                    count += mazePathJumps_memo(r, c, er, ec, dir, dp);
                else break;
            }
           
        }
        return dp[sr][sc] = count;
    }

    public static int mazePathJumps_DP(int SR, int SC, int er, int ec, int[][] dir, int[][] dp) {
        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                        int r = sr + rad * dir[d][0];
                        int c = sc + rad * dir[d][1];
                        if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                            count += dp[r][c];// mazePathJump_HDV(r, c, er, ec, dir, dp);
                        } else
                            break;
                    }
                }

                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }

    public static void mazePathHDV(){
        int sr = 0, sc = 0, er = 2, ec = 2;
        int[][] dp = new int[er + 1][ec + 1];
        int[][] dir = new int[][]{{1,0}, {1,1}, {0,1}};
        System.out.println(mazePath_DP(sr, sc, er, ec, dir, dp));
    }

    public static void main(String[] args){
        //fibo();
        // boardPath();
        mazePathHDV();
    }
}