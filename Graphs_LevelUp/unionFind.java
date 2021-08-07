import java.util.*;
public class unionFind {
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

    static int[] par, size;

    public static int findPar(int u) {
        if(par[u] == u) return u;
        return par[u] = findPar(par[u]); // path compression
        // return par[u] == u ? u : par[u] = find(par[u]); // single line code for findPar()
    }

    public static void union(int p1, int p2) {
        if(size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    // edges = {{u1,v1,w1}, {u2,v2,w2}, {u3,v3,w3} ...}
    public static void unionFind(int[][] edges) {
        int N = edges.length;
        ArrayList<Edge>[] graph = new ArrayList[N];
        par = new int[N];
        size = new int[N];
        for(int i = 0; i < N; i++) {
            par[i] = size[i] = i;
            graph[i] = new ArrayList<>();
        }
        for(int[] e: edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u);
            int p2 = findPar(v);
            if(p1 != p2) {
                union(p1, p2);
                addEdge(graph, u, v, w);
            }
        }
    }
    
}
