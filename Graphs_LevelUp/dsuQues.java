import java.util.*;

public class dsuQues {
    static int[] par, size;

    public static int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    // July 18, 2021
    // Leetcode 695 - https://leetcode.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        par = new int[n * m];
        size = new int[n * m];
        int count = 0, maxSize = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) count++;
                par[i * m + j] = i * m + j;
                size[i * m + j] = 1;
            }
        }
        int[][] dir = { { 0, 1 }, { 1, 0 } };
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    int p1 = findPar(i * m + j);
                    for(int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                            int p2 = findPar(r * m + c);
                            if(p1 != p2) {
                                count--;
                                par[p2] = p1;
                                size[p1] += size[p2];
                            }
                        } 
                    }
                    maxSize = Math.max(maxSize, size[p1]);
                }
            }
        }
        return maxSize;
    }
    
    // Leetcode 1061 - Lexicographically smallest equivalent string
    // Solved here - https://www.codingninjas.com/codestudio/problems/smallest-equivalent-string_1381859
    public static String smallestString(String s1, String s2, String baseStr) {
		par = new int[26];
        for(int i = 0; i < 26; i++) par[i] = i;
        for(int i = 0; i < s1.length(); i++) {
            int p1 = findPar(s1.charAt(i) - 'a');
            int p2 = findPar(s2.charAt(i) - 'a');
            if(p1 != p2) {
                if(p1 < p2) par[p2] = p1;
                else par[p1] = p2;
            }
        }
        String output = "";
        for(int i = 0; i < baseStr.length(); i++) {
            int p = findPar(baseStr.charAt(i) - 'a');
            output += (char) (p + 'a') + "";
        }
        return output;
	}

    // Leetcode 990 - https://leetcode.com/problems/satisfiability-of-equality-equations/
    public boolean equationsPossible(String[] equations) {
        par = new int[26];
        for(int i = 0; i < 26; i++) par[i] = i;
        for(String str: equations) { // set creation
            char ch1 = str.charAt(0), ch2 = str.charAt(3);
            boolean isEqual = (str.charAt(1)  == '=' ? true : false);
            int p1 = findPar(ch1 - 'a');
            int p2 = findPar(ch2 - 'a');
            if(isEqual && p1 != p2) par[p1] = p2;
        }
        for(String str: equations) { // set verification
            char ch1 = str.charAt(0), ch2 = str.charAt(3);
            boolean isEqual = (str.charAt(1)  == '=' ? true : false);
            int p1 = findPar(ch1 - 'a');
            int p2 = findPar(ch2 - 'a');
            if(!isEqual && p1 == p2) return false;
        }
        return true;
    }

    // Leetcode 839 - https://leetcode.com/problems/similar-string-groups/
    public boolean isSimilar(String s1, String s2) {
        int count = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i) && ++count > 2) return false;
        }
        return true;
    }

    public int numSimilarGroups(String[] strs) {
        int N = strs.length;
        int groups = N;
        par = new int[N];
        for(int i = 0; i < N; i++) par[i] = i;
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                if(isSimilar(strs[i], strs[j])) {
                    int p1 = findPar(i);
                    int p2 = findPar(j);
                    if(p1 != p2) {
                        par[p1] = p2;
                        groups--;
                    }
                }
            }
        }
        return groups;
    }

    // Leetcode 305 - Num of Islands 2
    // Solved here - https://www.lintcode.com/problem/434/
    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> ans = new ArrayList<>();
        if (operators == null) return ans;
        int islands = 0;
        par = new int[n * m];
        Arrays.fill(par, -1);
        int[][] dir = { {0 , 1 }, { 1 , 0 }, {-1, 0}, {0, -1}} ;
        for(Point p: operators) {
            int sr = p.x;
            int sc = p.y;
            if(par[sr * m + sc] == -1){
                par[sr * m + sc] = sr * m + sc;
                islands++;
            }
            for(int d = 0; d < dir.length; d++) {
                int r = sr + dir[d][0];
                int c = sc + dir[d][1];
                if(r >= 0 && c >= 0 && r < n && c < m && par[r * m + c] != -1){
                    int p1 = findPar(sr * m + sc);
                    int p2 = findPar(r * m + c);
                    if(p1 != p2) {
                        par[p2] = p1;
                        islands--;
                    }
                }
            }
            ans.add(islands);
            
        }
        return ans;
    }

    // HW
    // Leetcode 684 - https://leetcode.com/problems/redundant-connection/
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        par = new int[n + 1];
        
        int[] res = new int[2];
        for(int i = 1; i <= n; i++) par[i] = i;
        for(int[] e: edges) {
            int p1 = findPar(e[0]);
            int p2 = findPar(e[1]);
            if(p1 == p2) res = e;
            else par[p1] = p2;
        }
        return res;
    }


    // July 22, 2021

    // Leetcode 1168 - optimize water distribution in a village
    
    // There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes. For each house i, we can 
    // either build a well inside it directly with cost wells[i], or pipe in water from another well to it. The costs to lay pipes between houses 
    // are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a 
    // pipe. Connections are bidirectional. Find the minimum total cost to supply water to all houses.

    // Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
    // Output: 3
    // Explanation: The image shows the costs of connecting houses using pipes.
    // The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> allPipes = new ArrayList<>();
        for(int[] a: pipes) allPipes.add(a);
        for(int i = 0; i < wells.length; i++) allPipes.add(new int[]{0, i + 1, wells[i]});
        Collections.sort(allPipes, (a, b) -> {
            return a[2]-b[2];
        });
        par = new int[n + 1];
        int ans = 0;
        for(int i = 0; i <= n; i++) par[i] = i;
        for(int[] e: allPipes) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u), p2 = findPar(v);
            if(p1 != p2) {
                par[p1] = p2;
                ans += w;

            }
        }
        return ans;
    }

    // Hackerearth Mr President
    // https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/
    public static int mrPresident(int[][] edges, int n, int m, int k) {
        par = new int[n];
        int components = n;
        for(int i = 0; i < n; i++) par[i] = i;
        int wt = 0;
        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });
        ArrayList<Integer> roadWts = new ArrayList<>();
        for(int[] e: edges){
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u - 1), p2 = findPar(v - 1);
            if(p1 != p2) {
                par[p1] = p2;
                wt += w;
                components--;
                roadWts.add(w);
            }
        }
        if(components > 1) return -1;
        int superRoads = 0;
        for(int i = roadWts.size() - 1; i >= 0; i--) {
            if(wt <= k) break;
            wt = wt - roadWts.get(i) + 1;
            superRoads++;

        }
        return wt <= k ? superRoads : -1;
    }

    // Leetcode 959 - https://leetcode.com/problems/regions-cut-by-slashes/
    public int regionsBySlashes(String[] grid) {
        int regions = 1; 
        int n = grid.length;
        int m = n + 1;
        par = new int[m * m];
        for(int i = 0; i < m * m; i++) {
            par[i] = i;
            int r = i / m, c = i % m;
            if(r == 0 || c == 0 || r == m - 1 || c == m - 1) par[i] = 0;
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < grid[i].length(); j++){
                char ch = grid[i].charAt(j);
                int startx = i, starty = j, endx = i, endy = j;
                if(ch == '\\'){
                    endx++;
                    endy++;
                } else if(ch == '/') {
                    starty++;
                    endx++;
                }
                else continue;
                int p1 = findPar(startx * m + starty), p2 = findPar(endx * m + endy);
                if(p1 == p2) regions++;
                else par[p1] = p2;
            } 
        }
        return regions;
    }

    // August 5, 2021
    // Leetcode 924 - https://leetcode.com/problems/minimize-malware-spread/
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        par = new int[n];
        int[] poc = new int[n];  // population of country
        for(int i = 0; i < n; i++) {
            par[i] = i;
            poc[i] = 1;
        }
        for(int i = 0; i < n; i++) {
            int p1 = findPar(i);
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                if(graph[i][j] == 1){
                    int p2 = findPar(j);
                    if(p1 != p2) {
                        par[p2] = p1;
                        poc[p1] += poc[p2];
                    }
                }
            }
        }
        Arrays.sort(initial);
        int[] ipc = new int[n]; // infected person in a country
        for(int ip: initial) {
            int c = findPar(ip); // find country of infected person
            ipc[c]++; // count of infected people in the country
        }
        int ans = initial[0];
        int maxPopulated = 0; 
        for(int ip: initial) {
            int c = findPar(ip);
            if(ipc[c] == 1 && poc[c] > maxPopulated) {
                ans = ip;
                maxPopulated = poc[c];
            }
        }
        return ans;
    }

    
}
