import java.util.*;
public class bfsQues {
    // Leetcode 994 - https://leetcode.com/problems/rotting-oranges/
    public int orangesRotting(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int freshOranges = 0, time = 0, n = grid.length, m = grid[0].length;
        int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 2) {
                    que.addLast(i * m + j);
                    grid[i][j] = 2; // mark visited
                }
                else if(grid[i][j] == 1) freshOranges++;
            }
        }
        if(freshOranges == 0) return 0;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                        if(--freshOranges == 0) return time + 1;
                        grid[r][c] = 2;
                        que.addLast(r * m + c);
                    }

                }
            }
            time++;
        }
        return -1;   
    }

    // Leetcode 1091 - https://leetcode.com/problems/shortest-path-in-binary-matrix/
    public int shortestPathBinaryMatrix(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int n = grid.length, m = grid[0].length, steps = 1;
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        if(grid[0][0] != 0) return -1;
        que.addLast(0); // Inserting (0, 0)
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;
                if(sr == n - 1 && sc == m - 1) return steps;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0) {
                        grid[r][c] = 1;
                        que.addLast(r * m + c);
                    }
                }
            }
            steps++;
        }
        return -1; 
    }

    // Leetcode 542: https://leetcode.com/problems/01-matrix/

    // Approach 1
    // With visited array (extra space)
    public int[][] updateMatrix(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int n = grid.length, m = grid[0].length;
        int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        boolean[][] vis = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 0) {
                    que.addLast(i * m + j);
                    vis[i][j] = true; // mark visited
                }
            }
        }
        int level = 1;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                        vis[r][c] = true;
                        grid[r][c] = level;
                        que.addLast(r * m + c);
                    }

                }
            }
            level++;
        }
        return grid;
    }

    // Without visited array (no extra space)
    public int[][] updateMatrix2(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int n = grid.length, m = grid[0].length;
        int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 0) que.addLast(i * m + j);
            }
        }
        int level = 1;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                        grid[r][c] = -level;
                        que.addLast(r * m + c);
                    }
                }
            }
            level++;
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) grid[i][j] = -grid[i][j];
        }
        return grid;
    }

    // https://leetcode.com/problems/possible-bipartition/
    // HW

    // https://www.lintcode.com/problem/663/
    public void wallsAndGates(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int n = grid.length, m = grid[0].length;
        int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 0) que.addLast(i * m + j);
            }
        }
        int level = 1;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 2147483647) {
                        grid[r][c] = level;
                        que.addLast(r * m + c);
                    }
                }
            }
            level++;
        }
    }

    // July 15, 2021
    // Leetcode 207 - https://leetcode.com/problems/course-schedule/
    // N is num of courses
    public boolean canFinish(int N, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] arr: prerequisites){
            graph[arr[0]].add(arr[1]);
        }
        int[] indegree = new int[N];
        // O(E)
        for(int i = 0; i < N; i++) {
            for(int e: graph[i]) indegree[e]++;
        }
        // O(V)
        for(int i = 0; i < N; i++) {
            if(indegree[i] == 0) que.addLast(i);
        }
        // O(E + v)
        while(que.size() != 0) {
            int rvtx = que.removeFirst();
            ans.add(rvtx);
            for(int e: graph[rvtx]) {
                if(--indegree[e] == 0) que.addLast(e);
            }
        }
        
        return ans.size() == N;
        
    }

    // Leetcode 210 - https://leetcode.com/problems/course-schedule-ii/
    public int[] findOrder(int N, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        LinkedList<Integer> que = new LinkedList<>();
        int[] ans = new int[N];
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] arr: prerequisites){
            graph[arr[1]].add(arr[0]); // because we need answer in the reverse order acc to ques output.
        }
        int[] indegree = new int[N];
        // O(E)
        for(int i = 0; i < N; i++) {
            for(int e: graph[i]) indegree[e]++;
        }
        // O(V)
        for(int i = 0; i < N; i++) {
            if(indegree[i] == 0) que.addLast(i);
        }
        // O(E + v)
        int idx = 0;
        while(que.size() != 0) {
            int rvtx = que.removeFirst();
            ans[idx++] = rvtx;
            for(int e: graph[rvtx]) {
                if(--indegree[e] == 0) que.addLast(e);
            }
        }
        if(idx != N) ans = new int[0];
        return ans;
    }

    // July 17, 2021
    // Leetcode 329 - https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] indegree = new int[n][m];
        int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    // check my own indegree instead of neighbour's
                    if(r >= 0 && c >= 0 && r < n && c < m && matrix[i][j] > matrix[r][c]) indegree[i][j]++;
                }
                if(indegree[i][j] == 0) que.addLast(i*m + j);
            }
        }
        int path = 0;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int rvtx = que.removeFirst();
                int sr = rvtx / m, sc = rvtx % m;
                for(int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < n && c < m && matrix[r][c] > matrix[sr][sc]) {
                        if(--indegree[r][c] == 0) que.addLast(r*m + c);
                    }
                }
            }
            path++;
        }
        return path;
    }

    // July 25, 2021

    // Leetcode 815 - https://leetcode.com/problems/bus-routes/submissions/
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target) return 0;
        HashMap<Integer, ArrayList<Integer>> m = new HashMap<>(); // bus stand to bus mapping stand: [buses with stand in their routes]
        int N = routes.length;
        for(int bus = 0; bus < N; bus++){
            for(int stand: routes[bus]) {
                m.putIfAbsent(stand, new ArrayList<>());
                m.get(stand).add(bus);
            }
        }
        HashSet<Integer> standsVisited = new HashSet<>();
        boolean[] busVisited = new boolean[N];
        LinkedList<Integer> que = new LinkedList<>();
        que.add(source);
        int interchanges = 0;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int stand = que.removeFirst();
                for(int bus: m.get(stand)){
                    if(busVisited[bus]) continue;
                    for(int busStand: routes[bus]) {
                        if(!standsVisited.contains(busStand)){
                            if(busStand == target) return interchanges + 1;
                            standsVisited.add(busStand);
                            que.addLast(busStand);
                        }
                    }
                    busVisited[bus] = true;
                }
            }
            interchanges++;
        }
        return -1;
    }

    // August 5, 2021
    
    // Leetcode 1376 - https://leetcode.com/problems/time-needed-to-inform-all-employees/
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < manager.length; i++) {
            if(manager[i] != -1) graph[manager[i]].add(i);
        }
        
        LinkedList<int[]> que = new LinkedList<>();
        que.add(new int[]{headID, 0});
        int totalTime = 0;
        
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int[] emp = que.remove();
                int u = emp[0], w = emp[1];                
                totalTime = Math.max(totalTime, w);
                for(int v: graph[u]) que.add(new int[]{v, w + informTime[u]});
                
            } 
        }
        return totalTime;
    }

    // August 7, 2021
    // https://www.lintcode.com/problem/787/
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int n = maze.length, m = maze[0].length;
        int sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
        LinkedList<Integer> que = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        int[][] dir = {{0,1},{1,0}, {0,-1}, {-1,0}};
        que.add(sr * m + sc);
        vis[sr][sc] = true;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int idx = que.removeFirst();
                int i = idx / m, j = idx % m;
                for(int[] d: dir){
                    int r = i, c = j;
                    while(r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                        r += d[0];
                        c += d[1];
                    }
                    r -= d[0];
                    c -= d[1];
                    if(vis[r][c]) continue;
                    vis[r][c] = true;
                    que.add(r * m + c);
                    if(er == r && ec == c) return true;
                    
                }
            }
        }
        return false;
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        class Pair {
            int dist, r, c;
            public Pair(int r, int c, int dist) {
                this.r = r;
                this.c = c;
                this.dist = dist;
            }
        }
        
        int n = maze.length, m = maze[0].length;
        int sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return a.dist - b.dist;
        });
        
        int[][] dist = new int[n][m];
        for(int[] d: dist) Arrays.fill(d, (int)1e9);
        
        int[][] dir = {{0,1},{1,0}, {0,-1}, {-1,0}};
        
        pq.add(new Pair(sr, sc, 0));
        dist[sr][sc] = 0;
        
        while(pq.size() != 0) {
            Pair p = pq.remove();
            for(int[] d: dir){
                int r = p.r, c = p.c, l = p.dist;
                while(r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                    r += d[0];
                    c += d[1];
                    l++;
                }
                r -= d[0];
                c -= d[1];
                l--;
                if(l >= dist[r][c]) continue;
                pq.add(new Pair(r, c, l));
                dist[r][c] = l;                 
            }
        }
        return dist[er][ec] == (int)1e9 ? -1 : dist[er][ec];
    }



    
}
