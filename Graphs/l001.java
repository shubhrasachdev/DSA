import java.util.ArrayList;
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

    public static void printPreOrder(int src, boolean[] vis){

    }

    public static void printPostOrder(int src, boolean[] vis){
        
    }

    
    
    
    
    public static void gcc(){
        boolean[] vis = new boolean[N];
        int components = 0;
        for(int i = 0; i < N; i++){
            if(!vis[i]){
                components++;
                dfs(i, vis);
            }
        }
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

    public static void main(String[] args){
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        addEdge(3, 4, 10);
        addEdge(4, 5, 10);
        addEdge(4, 6, 10);
        addEdge(5, 6, 10);
        display();
    }

}