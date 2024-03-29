import java.util.*;
public class l002DirectedGraph {

    public static class Edge {
        int v = 0, w = 0;
        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
    
    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w){
        graph[u].add(new Edge(v, w));
    }

    // O(2E)
    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for(int i = 0; i < N; i++){
            System.out.print(i + " --> ");
            for(Edge e: graph[i]){
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static void dfs_topologicalOrder(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        for(Edge e: graph[src]) {
            if(!vis[e.v]) {
                dfs_topologicalOrder(graph, e.v, vis, ans);
            }
        }
        ans.add(src);
    }

    public static ArrayList<Integer> topologicalOrder(ArrayList<Edge>[] graph) {
        int N = graph.length;
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[N];
        for(int i = 0; i < N; i++) {
            if(!vis[i]) dfs_topologicalOrder(graph, i, vis, ans);
        }
        return ans;
    }

    // O(E + v)
    public static void khansAlgo(ArrayList<Edge>[] graph) {
        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int N = graph.length;
        int[] indegree = new int[N];
        // O(E)
        for(int i = 0; i < N; i++) {
            for(Edge e: graph[i]) indegree[e.v]++;
        }
        // O(V)
        for(int i = 0; i < N; i++) {
            if(indegree[i] == 0) que.addLast(i);
        }
        // O(E + v)
        while(que.size() != 0) {
            int rvtx = que.removeFirst();
            ans.add(rvtx);
            for(Edge e: graph[rvtx]) {
                if(--indegree[e.v] == 0) que.addLast(e.v);
            }
        }
    }

    // July 17, 2021
    // -1 - unvisited, 0 - current path, 1 - backtrack
    public static boolean dfs_topo_isCycle(ArrayList<Edge>[] graph, int src, int[] vis, ArrayList<Integer> ans) {
        vis[src] = 0;
        boolean res = false;
        for(Edge e: graph[src]) {
            if(vis[e.v] == -1) res = res || dfs_topo_isCycle(graph, e.v, vis, ans);
            else if(vis[e.v] == 0) return true;
        }
        vis[src] = 1;
        ans.add(src);
        return res;
    }

    public static ArrayList<Integer> dfs_topo_cycle(ArrayList<Edge>[] graph) {
        int N = graph.length;
        int[] vis = new int[N];
        ArrayList<Integer> ans = new ArrayList<>();
        Arrays.fill(vis, -1);
        boolean cycle = false;
        for(int i = 0; i < N; i++) {
            if(vis[i] == -1) cycle = cycle || dfs_topo_isCycle(graph, i, vis, ans);
        }
        if(cycle) ans.clear();
        return ans;
    }

    public static void constructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);
        display(graph);
    }
    
}
