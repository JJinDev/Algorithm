import java.util.*;

class L2_배달 {
    ArrayList<Edge>[] graph;
    int[] dist;
    
    public class Edge implements Comparable<Edge> {
        int end;
        int weight;
        
        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
        
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        init(N, road);
        solve(N);
        return getAnswer(N, K);
    }
    
    public int getAnswer(int N, int K) {
        int answer = 0;
        
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public void solve(int N) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        dist = new int[N+1];
        boolean[] checked = new boolean[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        queue.offer(new Edge(1, 0));
        dist[1] = 0;
        
        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            int cur = e.end;
            
            if (checked[cur]) continue;
            checked[cur] = true;
            
            for (Edge edge : graph[cur]) {
                dist[edge.end] = Math.min(dist[edge.end], dist[cur] + edge.weight);
                queue.offer(new Edge(edge.end, dist[edge.end]));
            }
        }
        
    }
    
    public void init(int N, int[][] road) {
        graph = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
    }
}