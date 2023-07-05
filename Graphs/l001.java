import java.util.*;

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


    // Like Leetcode 200: number of islands
    // 1 = Land, 0 = water
    public static void dfs_island(int[][] mat, int[][] dir, int i, int j){
        mat[i][j] = 0;
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r >= 0 && c >= 0 && r < mat.length && c < mat[0].length && mat[r][c] == 1){
                dfs_island(mat, dir, r, c);
            }
        }
    }

    public static int numberIsland(int[][] mat){
        int n = mat.length;
        int m = mat[0].length;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 1){
                    dfs_island(mat, dir, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void hamiltonianPaths_dfs(int src, int osrc, boolean[] vis, int numEdges, String psf) {
        if(numEdges == N - 1) { // all vertices have been visited
            String ans = psf + src;
            int idx = findEdge(osrc, src);
            ans += idx == -1 ? '.' : '*';
            System.out.println(ans);
            return;
        }
        vis[src] = true;
        for(Edge e: graph[src]) {
            if(!vis[e.v]) hamiltonianPaths_dfs(e.v, osrc, vis, numEdges + 1, psf + src);
        }
        vis[src] = false;
    }

    public static void hamiltonianPaths(int src) {
        hamiltonianPaths_dfs(src, src, new boolean[N], 0, "");
    }

    // Journey To Moon: https://www.hackerrank.com/challenges/journey-to-the-moon
    public static int dfs_journeyToMoon(ArrayList<Integer>[] graph, int src, boolean[] vis){
        int sz = 1;
        vis[src] = true;
        for(int e: graph[src]){
            if(!vis[e]) sz += dfs_journeyToMoon(graph, e, vis);
        }
        return sz;
    }

    public static long journeyToMoon(int n, List<List<Integer>> astronaut){
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for(List<Integer> pair: astronaut) {
            graph[pair.get(0)].add(pair.get(1));
            graph[pair.get(1)].add(pair.get(0));
        }
        ArrayList<Integer> numAstronauts = new ArrayList<>();
        boolean[] vis = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!vis[i]) numAstronauts.add(dfs_journeyToMoon(graph, i, vis));
        }
        long ssf = 0, res = 0;
        for (int ele : numAstronauts) {
            res += ele * ssf;
            ssf += ele;
        }

        return res;
    }

    public static void BFS(int src, boolean[] vis) {
        int level = 0, cycleCount = 0;
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        while(!que.isEmpty()) {
            int size = que.size();
            System.out.print(level + " - > ");
            while(size-- > 0) {
                int rvtx = que.removeFirst();
                if(vis[rvtx]) {
                    cycleCount++;
                    continue;
                }
                System.out.print(rvtx + " ");
                vis[rvtx] = true;
                for (Edge e: graph[rvtx]) {
                    if(!vis[e.v]) que.addLast(e.v);
                }
            }
            System.out.println();
            level++;
        }
        System.out.println("cycle count: " + cycleCount);
    }

    public static void BFS_02(int src, boolean[] vis) {
        int level = 0;
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        vis[src] = true;
        while(!que.isEmpty()) {
            int size = que.size();
            System.out.print(level + " - > ");
            while(size-- > 0) {
                int rvtx = que.removeFirst();
                System.out.print(rvtx + " ");
                for (Edge e: graph[rvtx]) {
                    if(!vis[e.v]) {
                        que.addLast(e.v);
                        vis[e.v] = true;
                    }
                }
            }
            System.out.println();
            level++;
        }
    }

    public static boolean isTree() {
        // No cycle and 1 gcc
        return false;
    }

    public static boolean isForest() {
        // No cycle and > 1 gcc
        return false;
    }

    // Leetcode 785 - https://leetcode.com/problems/is-graph-bipartite
    public boolean isBipartite(int[][] graph, int src, int[] vis) {
        int color = 0;
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        while(!que.isEmpty()) {
            int size = que.size();
            while(size-- > 0) {
                int rvtx = que.removeFirst();
                if(vis[rvtx] != -1) {
                    if(vis[rvtx] != color) return false; // conflict
                    continue;
                }
                vis[rvtx] = color;
                for(int vtx: graph[rvtx]) {
                    if(vis[vtx] == -1) que.addLast(vtx);
                }
            }
            color = (color + 1) % 2;
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n]; // -1 - unvisited, 0- red, 1 - white
        Arrays.fill(vis, -1);
        boolean res = true;
        for(int i = 0; i < n; i++) {
            if(vis[i] == -1) res = res && isBipartite(graph, i, vis);
        }
        return res;
    }

    // Spread of Infection
    // 1. You are given a graph, representing people and their connectivity.
    // 2. You are also given a src person (who got infected) and time t.
    // 3. You are required to find how many people will get infected in time t, if the infection spreads to neighbors of infected person in 1 unit of time.
    public static int spreadOfInfection(ArrayList<Edge>[] graph, int src, int time) {
        int count = 1;
        LinkedList<Integer> que = new LinkedList<>();
        boolean[] vis = new boolean[graph.length];
        que.addLast(src);
        vis[src] = true;
        int currTime = 1;
        while(!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                int p = que.removeFirst();
                System.out.println("Removed " + p);
                for(Edge e: graph[p]) {

                    if(!vis[e.v]) {
                        System.out.println("Tring to add " + e.v);
                        que.addLast(e.v);
                        vis[e.v] = true;
                        count++;
                    }
                }
            }
            System.out.println("At Time  " + currTime + ": " + count);
            currTime++;
            if (time == currTime) {
                System.out.println("Breaking");
                break;
            }
        }
        return count;
    }


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
        display();
//        addEdge(0, 6, 16);
//        addEdge(2, 5, 5);
//        boolean[] vis = new boolean[N];
//        Pair p = new Pair();
//        multisolver(0, 6, vis, p, 0, "", 30, 4);
//        System.out.println(p);
//        System.out.println(pq.peek());
//        BFS(0, vis);
        System.out.println(spreadOfInfection(graph, 6, 3));

    }

}