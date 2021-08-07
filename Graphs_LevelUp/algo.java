import java.util.*;
public class algo {
    public static class Edge{
        int v = 0, w = 0;
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w){
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
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

    static int[] par, size;

    public static int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(u));
    }

    public static void union(int p1, int p2) {
        if(size[p1] <= size[p2]){
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    // O(V + E) - with path compression, O(V + ElogV) without path compression(findpar will be log n then)
    public static void unionFind(int[][] edges, ArrayList<Edge>[] graph, int N) {
        par = new int[N];
        size = new int[N];
        for(int i = 0; i < N; i++) {
            par[i] = i;
            size[i] = 1;
        }
        for(int[] e: edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u), p2 = findPar(v);
            if(p1 != p2) {
                union(p1, p2);
                addEdge(graph, u, v, w);
            }
        }
    }

    // Total time complexity = O(ElogE + V + ElogV) - sparse
    // Total time complexity = O(ElogV) - dense (E = V ^ 2)
    public static void kruskalAlgo(int[][] edges, int N) {
        Arrays.sort(edges, (a, b) -> { // O(E log E)
            return a[2] - b[2];
        });
        ArrayList<Edge>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
        unionFind(edges, graph, N);
        display(graph);
    }


    // July 29, 2021
    // Articulation and Bridges
    static int[] low, disc;
    static boolean[] articulation, vis;
    static int time = 0, rootCalls = 0;


    public static void dfs(ArrayList<Edge>[] graph, int src, int par) {
        disc[src] = low[src] = time++;
        vis[src] = true;
        for(Edge e: graph[src]) {
            if(!vis[e.v]) {
                // actual root will have par = -1
                if(par == -1) rootCalls++;
                dfs(graph, e.v, src);
                if(disc[src] <= low[e.v]) articulation[src] = true;
                if(disc[src] < low[e.v]) System.out.println("Articulation Edge: " + src + "-->" + e.v);
                low[src] = Math.min(low[src], low[e.v]);
            } else if(e.v != par) low[src] = Math.min(low[src], disc[e.v]);

        }
    }

    public static void articulationPointsAndBridges(ArrayList<Edge>[] graph) {
        int N = graph.length;
        low = new int[N];
        disc = new int[N];
        articulation = new boolean[N];
        vis = new boolean[N];
        for(int i = 0; i < N; i++) { // for multiple components
            if(!vis[i]) dfs(graph, i, -1);
        }
    }

    // July 31, 2021

    // Dijkstra Algorithm
    public static class Pair {
        int vtx, par, w, wsf;
        
        // dijkstra 1
        Pair(int vtx, int par, int w, int wsf) {
            this.vtx = vtx;
            this.par = par;
            this.w = w;
            this.wsf = wsf;
        }

        // dijkstra 2
        Pair(int vtx, int wsf) {
            this.vtx = vtx;
            this.wsf = wsf;
        }
    }

    public static void dijkstra_01(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            ngraph[i] = new ArrayList<>();
        boolean[] vis = new boolean[N];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });
        int[] dis = new  int[N];
        int[] par = new  int[N];
        pq.add(new Pair(src, -1, 0, 0));
        while (pq.size() != 0) {
            Pair p = pq.remove();
            if (vis[p.vtx]) continue;
            if (p.par != -1) addEdge(ngraph, p.vtx, p.par, p.w);
            vis[p.vtx] = true;
            dis[p.vtx] = p.wsf;
            par[p.vtx] = p.par;
            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v])
                    pq.add(new Pair(e.v, p.vtx, e.w, p.wsf + e.w));
            }
        }
    }

    public static void dijkstra_02(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        int[] dis = new int[N];
        int[] par = new int[N];
        Arrays.fill(dis, (int) 1e9);
        Arrays.fill(par, -1);
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });
        pq.add(new Pair(src, 0));
        dis[src] = 0;
        par[src] = -1;
        while (pq.size() != 0) {
            Pair p = pq.remove();
            if (p.wsf > dis[p.vtx]) continue;
            for (Edge e : graph[p.vtx]) {
                if (p.wsf + e.w < dis[e.v]) {
                    dis[e.v] = p.wsf + e.w;
                    par[e.v] = p.vtx;
                    pq.add(new Pair(e.v, p.wsf + e.w));
                }
            }
        }

    }

    public static class primsPair {
        int vtx, w;
        primsPair(int vtx, int w) {
            this.vtx = vtx;
            this.w = w;
        }
    }

    public static void prims(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        PriorityQueue<primsPair> pq = new PriorityQueue<>((a, b) -> {
            return a.w - b.w;
        });
        int[] dis = new int[N];
        Arrays.fill(dis, (int) 1e9);
        dis[src] = 0;
        pq.add(new primsPair(src, 0));
        while (pq.size() != 0) {
            primsPair p = pq.remove();
            if (vis[p.vtx]) continue;
            vis[p.vtx] = true;
            for (Edge e : graph[p.vtx]) {
                if (e.w < dis[e.v]) {
                    dis[e.v] = e.w;
                    pq.add(new primsPair(e.v, e.w));
                }
            }
        }
    }


    // Aug 1, 2021
    public static void bellmanFord_01(int N, int[][] edges, int src) {
        int[] prev = new int[N];
        Arrays.fill(prev, (int)1e9);
        prev[src] = 0;
        boolean negCycle = false;
        for(int i = 1; i <= N; i++) {
            boolean anyUpdate = false;
            int[] curr = new int[N];
            for(int j = 0; j < N; j++) curr[j] = prev[j];
            for(int[] e: edges) {
                int u = e[0], v = e[1], w = e[2];
                if(prev[u] != (int)1e9 && prev[u] + w < curr[v]) {
                    curr[v] = prev[u] + w;
                    anyUpdate = true;
                    if(i == N) {
                        negCycle = true;
                        break;
                    }
                }
            }

            if(!anyUpdate) break;
            prev = curr;
        }
        System.out.println("Negative Cycle: " + negCycle);
    }

    // August 5, 2021
    public static void bellmanFord_02(int N, int[][] edges, int src) {
        int[] curr = new int[N];
        Arrays.fill(curr, (int)1e9);
        curr[src] = 0;
        boolean negCycle = false;
        for(int i = 1; i <= N; i++) {
            boolean anyUpdate = false;
            for(int[] e: edges) {
                int u = e[0], v = e[1], w = e[2];
                if(curr[u] != (int)1e9 && curr[u] + w < curr[v]) {
                    curr[v] = curr[u] + w;
                    anyUpdate = true;
                    if(i == N) {
                        negCycle = true;
                        break;
                    }
                }
            }
            if(!anyUpdate) break;
        }
        System.out.println("Negative Cycle: " + negCycle);
    }

    // O(V^3)
    public static void floydWarshall(int[][] edges, int n) {
        int[][] mat = new int[n][n];
        for(int[] m: mat) Arrays.fill(m, (int)1e9);
        for(int[] e: edges) mat[e[0]][e[1]] = e[2];
        for(int i = 0; i < n; i++) mat[i][i] = 0;

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
            }
        }

    }

    // August 7, 2021
    // Kosaraju Algo
    public static void dfs_topo(int src, ArrayList<Edge>[] graph, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        for(Edge e: graph[src]) {
            if(!vis[e.v]) dfs_topo(e.v, graph, vis, ans);
        }
        ans.add(src);
    }

    public static void dfs_SCC_Compo(int src, ArrayList<Edge>[] graph, boolean[] vis, ArrayList<Integer> components) {
        vis[src] = true;
        components.add(src);
        for(Edge e: graph[src]) {
            if(!vis[e.v]) dfs_SCC_Compo(e.v, graph, vis, components);
        }
    }

    public static void kosaraju(int N, ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[N];
        ArrayList<Integer> order = new ArrayList<>();
        // 1. Topological order
        for(int i = 0; i < N; i++) 
            if(!vis[i]) dfs_topo(i, graph, vis, order);
        
        // 2. Complementing graph
        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for(int i = 0; i < N; i++) ngraph[i] = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(Edge e: graph[i]) ngraph[e.v].add(new Edge(i, e.w));
        }

        // 3. DFS on topological order to find components
        Arrays.fill(vis, false);
        ArrayList<Integer> components = new ArrayList<>();
        for(int i = order.size() - 1; i >= 0; i--){ 
            int vtx = order.get(i);
            if(!vis[vtx]) {
                dfs_SCC_Compo(vtx, ngraph, vis, components);
                System.out.println(components);
                components.clear();
            }
        }        
    }

}
