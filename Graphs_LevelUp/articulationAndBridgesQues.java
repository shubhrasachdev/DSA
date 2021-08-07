import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class articulationAndBridgesQues {

    // July 29, 2021
    static int[] low, dis;
    static boolean[] vis;
    static int time = 0, calls = 0;

    // Leetcode 1192 - https://leetcode.com/problems/critical-connections-in-a-network/
    
    public static void dfs(ArrayList<Integer>[] graph, int src, int par, List<List<Integer>> ans) {
        dis[src] = low[src] = time++;
        vis[src] = true;
        for(int v: graph[src]) {
            if(!vis[v]) {
                dfs(graph, v, src, ans);
                if(dis[src] < low[v]) {
                    List<Integer> edge = new ArrayList<>(Arrays.asList(src, v));
                    ans.add(edge);
                }
                low[src] = Math.min(low[src], low[v]); 
            } else if(par != v) low[src] = Math.min(low[src], dis[v]);
        }

    }
    
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for(List<Integer> conn: connections) {
            graph[conn.get(0)].add(conn.get(1));
            graph[conn.get(1)].add(conn.get(0));
        }
        low = new int[n];
        dis = new int[n];
        vis = new boolean[n];
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(!vis[i]) dfs(graph, i, -1, ans);
        }
        return ans;
    }
    
}
