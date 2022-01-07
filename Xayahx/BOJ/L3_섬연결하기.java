import java.util.*;

class L3_섬연결하기 {
    PriorityQueue<Edge> queue;
    int[] parent;
    
    class Edge implements Comparable<Edge> {
        int v1,v2,w;
        
        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }
        
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
    
    public int solution(int n, int[][] costs) {
        init(n, costs);
        return solve();
    }
    
    public int solve() {
        int sum = 0;
        while(!queue.isEmpty()) {
            Edge e = queue.poll();
            if(isSameParent(e.v1, e.v2)) continue;
            sum += e.w;
            union(e.v1, e.v2);
        }
        return sum;
    }
    
    public int find(int x) {
        if(x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) parent[y] = x;
    }
    
    public boolean isSameParent(int x, int y) {
        return find(x) == find(y);
    }
    
    public void init(int n, int[][] costs) {
        parent = new int[n+1];
        for(int i=1; i<=n; i++) {
            parent[i] = i;
        }
        
        queue = new PriorityQueue<>();
        for(int i=0; i<costs.length; i++) {
            queue.add(new Edge(costs[i][0], costs[i][1], costs[i][2]));
        }
    }
    
}
