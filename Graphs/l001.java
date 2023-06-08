import java.util.ArrayList;
import java.util.PriorityQueue;

public class l001{
    public static class Edge{
        int v = 0;
        int w = 0;
        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
        public String toString(){
            return "(" + this.v + ", " + this.w + ")";
        }
    }

    public static int N = 7;
    public static ArrayList<Edge>[] graph = new ArrayList[N];
    
    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display(){
        for(int i = 0; i < N; i++){
            System.out.print(i + " -> ");
            for(Edge e: graph[i]){
                System.out.print(e);
            }
            System.out.println();
        }
    }

    public static int findEdge(int u, int v){
        int n = graph[u].size();
        for(int i = 0; i < n; i++){
            Edge e = graph[u].get(i);
            if(e.v == v) return i;
        }
        return -1;
    }

    public static void removeEdge(int u, int v){
        int idx1 = findEdge(u, v);
        int idx2 = findEdge(v, u);
        // if(idx1 == -1 || idx2 == -1) return;       
        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVertex(int u){
        while(graph[u].size() != 0){
            int n = graph[u].size();
            Edge e = graph[u].get(n - 1);
            removeEdge(u, e.v);
        }
    }

    public static boolean hasPath(int src, int dest, boolean[] vis){
        if(src ==  dest) return true;
        boolean res = false;
        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v]) res = res || hasPath(e.v, dest, vis);
        }
        return res;
    }

    public static int allPath(int src, int dest, boolean[] vis, String ans){
        if(src ==  dest) {
            System.out.println(ans + dest);
            return 1;
        }
        int count = 0;
        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v]) count += allPath(e.v, dest, vis, ans + src);
        }
        vis[src] = false;
        return count;
    }

    public static void printPreOrder(int src, boolean[] vis, String ans, int wsf){
        System.out.println(src + "->" + ans + src + "@" + wsf);
        vis[src] = true;
        for(Edge e: graph[src]) {
            if(!vis[e.v]) printPreOrder(e.v, vis, ans + src, wsf + e.w);
        }
        vis[src] = false;
    }

    public static void printPostOrder(int src, boolean[] vis, String ans, int wsf){
        vis[src] = true;
        for(Edge e: graph[src]) {
            if(!vis[e.v]) printPostOrder(e.v, vis, ans + src, wsf + e.w);
        }
        vis[src] = false;
        System.out.println(src + "->" + ans + src + "@" + wsf);
    }

    // MultiSolver (Pepcoding)
    // Smallest weighted path b/w src and dest
    // Largest weighted path b/w src and dest
    // Floor: Max wt path less than a given value
    // ceil: Min wt path greater than a given value
    // kth largest weighted path
    public static class Pair {
        int largestWt = Integer.MIN_VALUE;
        String largestPath = "";
        int smallestWt = Integer.MAX_VALUE;
        String smallestPath = "";

        int ceil = Integer.MAX_VALUE;
        int floor = Integer.MIN_VALUE;

        @Override
        public String toString() {
            return "Pair{" +
                    "largestWt=" + largestWt +
                    ", largestPath='" + largestPath + '\'' +
                    ", smallestWt=" + smallestWt +
                    ", smallestPath='" + smallestPath + '\'' +
                    ", ceil=" + ceil +
                    ", floor=" + floor +
                    '}';
        }
    }

    public static class PQPair {
        int wsf = 0;
        String psf = "";

        public PQPair(int wsf, String psf) {
            this.wsf = wsf;
            this.psf = psf;
        }

        @Override
        public String toString() {
            return "PQPair{" +
                    "wsf=" + wsf +
                    ", psf='" + psf + '\'' +
                    '}';
        }
    }

    static PriorityQueue<PQPair> pq = new PriorityQueue<>((a, b) -> {
        return a.wsf - b.wsf;
    });

    public static void multisolver(int src, int dest, boolean[] vis, Pair p, int wsf, String psf, int givenWeight, int k) {
        if(src == dest) {
            if(wsf > p.largestWt) {
                p.largestWt = wsf;
                p.largestPath = psf + dest;
            }
            if(wsf < p.smallestWt) {
                p.smallestWt = wsf;
                p.smallestPath = psf + dest;
            }
            if(wsf < givenWeight) p.floor = Math.max(p.floor, wsf);
            else p.ceil = Math.min(p.ceil, wsf);
            pq.add(new PQPair(wsf, psf + dest));
            if(pq.size() > k) pq.remove();
            return;
        }
        vis[src] = true;
        for(Edge e: graph[src]) {
            if(!vis[e.v]) multisolver(e.v, dest, vis, p, e.w + wsf, psf + src, givenWeight, k);
        }
        vis[src] = false;
    }

    public static void dfs(int src, boolean[] vis) {
        vis[src] = true;
        for(Edge e: graph[src]) {
            if(!vis[e.v]) dfs(e.v, vis);
        }
    }

    public static int gcc(){
        boolean[] vis = new boolean[N];
        int components = 0;
        for(int i = 0; i < N; i++){
            if(!vis[i]){
                components++;
                dfs(i, vis);
            }
        }
        return components;
    }

    public static boolean isGraphConnected(){
        boolean[] vis = new boolean[N];
        int components = 0;
        for(int i = 0; i < N; i++){
            if(!vis[i]){
                components++;
                dfs(i, vis);
            }
        }
        return components == 1;
    }

    // HW:
    // 1. Implement smallest path and largest path with recursion and return types
    // 2. Implement kth largest path without utilizing a DS like a PQ (max time complexity - O(kn))

//
//    public static void dfs_island(int[][] mat, int[][] dir, int i, int j){
//        mat[i][j] = 0;
//        for(int d = 0; d < 4; d++){
//            int r = i + dir[d][0];
//            int c = j + dir[d][1];
//            if(r >= 0 && c >= 0 && r < mat.length && c < mat[0].length && mat[r][c] == 1){
//                dfs_island(mat, dir, r, c);
//            }
//        }
//    }
//
//    public static int numberIsland(int[][] mat){
//        int n = mat.length;
//        int m = mat[0].length;
//        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//        int count = 0;
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < m; j++){
//                if(mat[i][j] == 1){
//                    dfs_island(mat, dir, i, j);
//                    count++;
//                }
//            }
//        }
//        return count;
//    }

    public static void main(String[] args){
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
        addEdge(0, 1, 10);
        addEdge(0, 3, 40);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        addEdge(3, 4, 2);
        addEdge(4, 5, 3);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);
        addEdge(2, 5, 5);
        boolean[] vis = new boolean[N];
        Pair p = new Pair();
        multisolver(0, 6, vis, p, 0, "", 30, 4);
        System.out.println(p);
        System.out.println(pq.peek());


    }

}