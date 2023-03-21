import java.util.*;
public class AccoliteTest {
    public static int beauty(int n, int m, String s, int[] x, int[] y) {
        int[] indegree = new int[n + 1];
        int ans = 0;
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for(int i = 0; i < x.length; i++) {
            int u = x[i];
            int v = y[i];
            graph[u].add(v);
            indegree[v]++;
        }
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dp = new int[n + 1][26];
        for(int i = 1; i <= n; i++) {
            if(indegree[i] == 0) {
                que.add(i);
                dp[i][s.charAt(i - 1) - 'a']++;
            }
        }

        while(!que.isEmpty()) {
            int u = que.remove();
            for(int i = 0; i < graph[u].size(); i++) {
                int v = graph[u].get(i);
                indegree[v]--;
                for(int j = 0; j < 26; j++) {
                    if(j == s.charAt(u - 1) - 'a') dp[u][j] = Math.max(dp[u][j], dp[v][j] + 1);
                    else dp[u][j] = Math.max(dp[u][j], dp[v][j]);
                }
                if(indegree[v] == 0) que.add(v);
            }
            ans++;
        }
        if(ans != n) return -1;
        int res = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < 26; j++) res = Math.max(res, dp[i][j]);
        }
        return res;
    }
    
}
