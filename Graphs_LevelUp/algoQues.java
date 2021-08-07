import java.util.*;
public class algoQues {

    // Leetcode 743 - https://leetcode.com/problems/network-delay-time/
    // Dijkstra Algorithm
    public int networkDelayTime(int[][] times, int N, int k) {
        // {v, w}
        ArrayList<int[]>[] graph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        for(int[] time: times) {
            int u = time[0], v = time[1], w = time[2];
            graph[u].add(new int[]{v, w});
        }
        
        int[] dist = new  int[N + 1];
        Arrays.fill(dist, (int)1e9);
        
         // vtx, wsf
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        pq.add(new int[]{k, 0});
        dist[k] = 0;
        
        while(pq.size() != 0) {
            int[] rp = pq.remove(); // remove pair
            int vtx = rp[0], wsf = rp[1];
            if(wsf > dist[vtx]) continue;
            for(int[] edge: graph[vtx]) {
                int v = edge[0], w = edge[1];
                if(wsf + w < dist[v]) {
                    dist[v] = wsf + w;
                    pq.add(new int[]{v, wsf + w});
                }
            }
            
        }
        int max = 0;
        for(int i = 1; i <= N; i++) {
            if(dist[i] == (int)1e9) return -1;
            max = Math.max(max, dist[i]);
        }
        return max;
        
    }

    // Leetcode 787 - https://leetcode.com/problems/cheapest-flights-within-k-stops/submissions/
    // Time: k(V + E)
    public int findCheapestPrice(int N, int[][] flights, int src, int dst, int k) {
        int[] prev = new int[N];
        Arrays.fill(prev, (int)1e9);
        prev[src] = 0;
        k++;
        for(int i = 0; i < k; i++) {
            boolean anyUpdate = false;
            int[] curr = new int[N];
            for(int j = 0; j < N; j++) curr[j] = prev[j];
            for(int[] e: flights) {
                int u = e[0], v = e[1], w = e[2];
                if(prev[u] != (int)1e9 && prev[u] + w < curr[v]) {
                    curr[v] = prev[u] + w;
                    anyUpdate = true;
                }
            }
            if(!anyUpdate) break;
            prev = curr;
        }
        return prev[dst] == (int)1e9 ? -1 : prev[dst];
    }

    // Leetcode 1334: https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] mat = new int[n][n];
        for(int[] m: mat) Arrays.fill(m, (int)1e9);
        for(int[] e: edges) {
            int u = e[0], v = e[1], w = e[2];
            mat[u][v] = mat[v][u] = w;
            
        }
        for(int i = 0; i < n; i++) mat[i][i] = 0;

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
            }
        }
        int res = 0, cities = (int)1e9;
        for(int i = 0; i < n; i++) {
            int cityCount = 0;
            for(int j = 0; j < n; j++){
                if(i != j && mat[i][j] != (int)1e9 && mat[i][j] <= distanceThreshold) cityCount++;
            } 
            if(cityCount <= cities) {
                res = i;
                cities = cityCount;
            }
        }
        return res;
    }

    

    
    
}
