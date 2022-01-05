import java.util.*;

class L3_가장먼노드 {
    ArrayList<Edge>[] graph;
    int[] dist;
    
    class Edge implements Comparable<Edge>{
        int end, weight;
        
        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
        
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    
    public int solution(int n, int[][] edge) {
        init(n, edge);
        solve(n);
        return getAnswer(n);
    }
    
    public int getAnswer(int n) {
        int max = 0;
        int count = 0;
        
        for(int i=2; i<=n; i++) {
            if(dist[i] == max) {
                count++;
            } else if(dist[i] > max) {
                max = dist[i];
                count = 1;
            }
        }    
        return count;
    }
    
    public void solve(int n) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        dist = new int[n+1];  
        boolean[] checked = new boolean[n+1];
        queue.offer(new Edge(1,0));
        Arrays.fill(dist, 987654321);
        dist[1] = 0;
        
        while(!queue.isEmpty()) {
            Edge e = queue.poll();
            int cur = e.end;
            if(checked[cur]) continue;
            checked[cur] = true;
            
            for(Edge edge : graph[cur]) {
                dist[edge.end] = Math.min(dist[edge.end], dist[cur] + edge.weight);
                queue.offer(new Edge(edge.end, dist[edge.end]));
            }
        }
    }
    
    public void init(int n, int[][] edge) {
        graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            graph[a].add(new Edge(b,1));
            graph[b].add(new Edge(a,1));
        }
    }
}