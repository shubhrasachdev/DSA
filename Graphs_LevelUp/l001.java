import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
public class l001{
    public static class Edge {
        int v = 0, w = 0;
        Edge(int v, int w){
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

    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
        ArrayList<Edge> list = graph[u];
        for(int i = 0; i < list.size(); i++){
            Edge e = list.get(i);
            if(e.v == v) return i;
        }
        return -1;
    }

    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        int idx = findEdge(graph, u, v);
        graph[u].remove(idx);

        idx = findEdge(graph, v, u);
        graph[v].remove(idx);
    }

    // hasPath - backtracking not needed
    // O(E) where E is total no of edges in that particular graph or component(if there are disconnected components)
    public static boolean dfs_findPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis){
        if(src == dest) return true;
        vis[src] = true;
        boolean res = false;
        for(Edge e: graph[src]){
            if(!vis[e.v]) res = res || dfs_findPath(graph, e.v, dest, vis);
        }
        return res;
    }

    // all paths - backtracking needed
    public static int printAllPaths(ArrayList<Edge>[] graph, int src, int dest, String psf, int wsf, boolean[] vis){
        if(src == dest) {
            System.out.println(psf + dest + "@" + wsf);
            return 1;
        }
        vis[src] = true;
        int count = 0;
        for(Edge e: graph[src]){
            if(!vis[e.v]) count += printAllPaths(graph, e.v, dest, psf + src, wsf + e.w, vis);
        }
        vis[src] = false;
        return count;
    }

    // GCC
    // O(E + V)
    public static int getConnectedComponents(ArrayList<Edge>[] graph, boolean[] vis){
        int components = 0;
        for(int i = 0; i < graph.length; i++) {
            components++;
            dfs_GCC(graph, i, vis);
        }
        return components;
    }

    public static void dfs_GCC(ArrayList<Edge>[] graph, int src, boolean[] vis){
        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v]) dfs_GCC(graph, e.v, vis);
        }
    }

    // July 8, 2021

    // O(E)
    public static boolean BFS_ForCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        boolean cycle = false;
        que.add(src);
        vis[src] = true;
        while(que.size() != 0) {
            int size = que.size();
            System.out.print("Min number of edges: " + level + " -> ");
            while(size-- > 0) {
                int rvtx = que.removeFirst();
                if(vis[rvtx]){
                    cycle = true;
                    continue;
                }
                System.out.print(rvtx + ", ");
                vis[rvtx] = true;
                for(Edge e: graph[rvtx]) {
                    if(!vis[e.v]) que.addLast(e.v);
                }

            }
            System.out.println();
            level++;
        }
        return cycle;
    }

    // O(V)
    public static void BFS_WithoutCycle(ArrayList<Edge>[] graph, int src, boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        que.add(src);
        vis[src] = true;
        while(que.size() != 0) {
            int size = que.size();
            System.out.print("Min number of edges: " + level + " -> ");
            while(size-- > 0) {
                int rvtx = que.removeFirst();
                System.out.print(rvtx + ", ");
                for(Edge e: graph[rvtx]) {
                    if(!vis[e.v]) {
                        vis[e.v] = true;
                        que.addLast(e.v);
                    }
                }

            }
            System.out.println();
            level++;
        }
    }

    // July 10, 2021
    public static boolean isBipartite(ArrayList<Edge>[] graph, int src, int[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int color = 0; // 0 - red, 1 - green
        que.add(src);
        boolean isBipartite = true;
        boolean isCycle = false;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int rvtx = que.removeFirst();
                if(vis[rvtx] != -1) {
                    isCycle = true;
                    if(vis[rvtx] != color) {
                        isBipartite = false;
                        break;
                    }
                    continue;
                }
                vis[rvtx] = color;
                for(Edge e: graph[rvtx]) {
                    if(vis[e.v] == -1) que.addLast(e.v);
                }
            }
            color = (color + 1) % 2;
            if(isBipartite) break;
        }
        if(isCycle) {
            if(isBipartite) System.out.println("Graph is bipartite and has even length cycle");
            else System.out.println("Graph is non bipartite and has odd length cycle");
        } else System.out.println("Graph is bipartite and has no cycle");
        return isBipartite;
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph) {
        int N = graph.length;
        int[] vis = new int[N];
        Arrays.fill(vis, -1);
        boolean res = true;
        for(int i = 0; i < N; i++) {
            if(vis[i] == -1) res = res && isBipartite(graph, i, vis);
        }
        return res;
    }

    public static void main(String[] args){
        constructGraph();
    }
}